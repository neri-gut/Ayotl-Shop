package shop.ayotl.backend.service.product;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import shop.ayotl.backend.config.exception.DuplicatedDataException;
import shop.ayotl.backend.config.exception.InvalidParamsException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.product.ProductDtoConverter;
import shop.ayotl.backend.dto.product.ProductCreateRequest;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.dto.product.ProductUpdateRequest;
import shop.ayotl.backend.repository.product.ProductRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductDtoConverter converter;

    public ProductServiceImpl(ProductRepository repository, ProductDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ProductDto create(ProductCreateRequest request) {
        try {
            repository.findByName(request.getName());
            throw new DuplicatedDataException("Ya existe este producto con el nombre: " + request.getName(), "");
        }catch (NotFoundException e){}
        final var toCreate = converter.createRequestToDto(request);
        return repository.save(toCreate);
    }

    @Override
    public ProductDto update(ProductUpdateRequest request) {

            if(request.getId() == null){
                throw new InvalidParamsException("El id de categoria es requerido", "");
            }
            try {
                repository.findByName(request.getName());
                throw new DuplicatedDataException("Ya existe un producto con el nombre: " + request.getName(), "");
            }catch (NotFoundException e){}

            final var found = repository.findById(request.getId());

            if(!StringUtils.hasText(request.getName())){
                request.setName(found.getName());
            }

            if (!StringUtils.hasText(request.getName())){
                request.setDescription(found.getDescription());
            }

            if (ObjectUtils.isEmpty(request.getPrice())){
                request.setPrice(found.getPrice());
            }

            if (ObjectUtils.isEmpty(request.getCategory())){
                request.setCategory(found.getCategory());
            }

            if (ObjectUtils.isEmpty(request.getStock())){
                request.setStock(found.getStock());
            }

            if (!StringUtils.hasText(request.getImagePath())){
                request.setImagePath(found.getImagePath());
            }

            if (ObjectUtils.isEmpty(request.getCreateAt())){
                request.setCreateAt(found.getCreateAt());
            }

            if (ObjectUtils.isEmpty(request.getUpdateAt())){
                request.setUpdateAt(found.getUpdateAt());
            }

            final var toUpdate = converter.updateRequestToDo(request);

        return repository.save(toUpdate);
    }

    @Override
    public ProductDto deleteById(Long id) {
        return repository.deleteById(id);
    }
}
