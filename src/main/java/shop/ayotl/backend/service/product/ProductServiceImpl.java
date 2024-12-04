package shop.ayotl.backend.service.product;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import shop.ayotl.backend.config.exception.*;
import shop.ayotl.backend.config.storage.ProductImgConfig;
import shop.ayotl.backend.converter.product.ProductDtoConverter;
import shop.ayotl.backend.dto.product.*;
import shop.ayotl.backend.repository.category.CategoryRepository;
import shop.ayotl.backend.repository.product.ProductRepository;
import shop.ayotl.backend.service.file.FileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductImgConfig productImgConfig;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;
    private final ProductDtoConverter converter;

    public ProductServiceImpl(
            ProductRepository repository,
            ProductImgConfig productImgConfig,
            FileService fileService,
            CategoryRepository categoryRepository,
            ProductDtoConverter converter
    ) {
        this.repository = repository;
        this.productImgConfig = productImgConfig;
        this.fileService = fileService;
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    @Override
    public List<ProductOutputDto> findAll(ProductFilter filter) {
        final var list = new ArrayList<ProductOutputDto>();

        for (final var productDto : repository.findAllWithFilters(filter.getCategoryId(), filter.getName())) {
            final var imageDataUrl = productImageDataUrl(productDto);
            final var productOutputDto = converter.dtoToOutputDto(productDto, imageDataUrl);
            list.add(productOutputDto);
        }

        return list;
    }

    private String productImageDataUrl(ProductDto product) {
        final var imagePath = product.getImagePath();

        if (! StringUtils.hasText(imagePath)) {
            return null;
        }

        final var imageFile = new File(imagePath);

        try {
            return fileService.fileToDataUrl(imageFile, product.getImageMimeType());
        } catch (java.io.IOException e) {
            throw new IOException(
                    "Error al recuperar la imagen del producto: " + product.getName(),
                    e.getMessage()
            );
        }
    }

    @Override
    public ProductOutputDto findById(Long id) {
        final var found = repository.findById(id);
        final var imageDataUrl = productImageDataUrl(found);

        return converter.dtoToOutputDto(found, imageDataUrl);
    }

    @Override
    public ProductOutputDto create(ProductCreateRequest request) {
        String imagePath = null;

        if (! fileService.fileIsNull(request.getImageFile())) {
            imagePath = saveFile(request.getImageFile());
        }

        final var category = categoryRepository.findById(request.getCategoryId());
        final var toCreate = converter.createRequestToDto(request, category);
        toCreate.setImagePath(imagePath);

        if (imagePath != null) {
            toCreate.setImageMimeType(request.getImageFile().getContentType());
        }

        final var imageDataUrl = productImageDataUrl(toCreate);

        final var created = repository.save(toCreate);

        return converter.dtoToOutputDto(created, imageDataUrl);
    }

    private String saveFile(MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final String path = productImgConfig.getPath();

        try {
            return fileService.saveFile(file, fileName, path);
        } catch (java.io.IOException e) {
            throw new PersistenceException("Error al guardar la imagen: " + fileName, e.getMessage());
        }
    }

    @Override
    public ProductOutputDto update(ProductUpdateRequest request) {
        if (request.getId() == null) {
            throw new InvalidParamsException("El id de producto es requerido", "");
        }

        final var found = repository.findById(request.getId());

        if (request.getCategoryId() == null) {
            request.setCategoryId(found.getCategory().getId());
        }

        final var category = categoryRepository.findById(request.getCategoryId());
        final var toUpdate = converter.updateRequestToDo(request, category);

        if(! StringUtils.hasText(toUpdate.getName())){
            toUpdate.setName(found.getName());
        }

        if (! StringUtils.hasText(toUpdate.getDescription())){
            toUpdate.setDescription(found.getDescription());
        }

        if (toUpdate.getPrice() == null){
            toUpdate.setPrice(found.getPrice());
        }

        if (toUpdate.getStock() == null){
            toUpdate.setStock(found.getStock());
        }

        if (fileService.fileIsNull(request.getImageFile())) {
            toUpdate.setImagePath(found.getImagePath());
            toUpdate.setImageMimeType(found.getImageMimeType());
        }
        else {
            final var imagePath = saveFile(request.getImageFile());
            toUpdate.setImagePath(imagePath);
            toUpdate.setImageMimeType(request.getImageFile().getContentType());
        }

        toUpdate.setCreateAt(found.getCreateAt());
        toUpdate.setUpdateAt(found.getUpdateAt());

        final var updated = repository.save(toUpdate);
        final var imageDataUrl = productImageDataUrl(updated);

        if (StringUtils.hasText(found.getImagePath()) && ! fileService.fileIsNull(request.getImageFile())) {
            try {
                fileService.deleteFile(found.getImagePath());
            }
            catch (java.io.IOException e) {}
        }

        return converter.dtoToOutputDto(updated, imageDataUrl);
    }

    @Override
    public ProductOutputDto deleteById(Long id) {
        final var found = repository.findById(id);
        repository.deleteById(id);

        final var imageDataUrl = productImageDataUrl(found);

        try {
            fileService.deleteFile(found.getImagePath());
        }
        catch (java.io.IOException e) {}

        return converter.dtoToOutputDto(found, imageDataUrl);
    }
}
