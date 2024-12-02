package shop.ayotl.backend.service.category;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import shop.ayotl.backend.config.exception.DuplicatedDataException;
import shop.ayotl.backend.config.exception.InvalidParamsException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.category.CategoryDtoConverter;
import shop.ayotl.backend.dto.category.CategoryCreateRequest;
import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.category.CategoryUpdateRequest;
import shop.ayotl.backend.repository.category.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryDtoConverter converter;

    public CategoryServiceImpl(CategoryRepository repository, CategoryDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<CategoryDto> findAll() {
        return repository.findAll();
    }

    @Override
    public CategoryDto findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CategoryDto create(CategoryCreateRequest request) {
        try {
            repository.findByName(request.getName());

            throw new DuplicatedDataException("Ya existe una categoría con el nombre: " + request.getName(), "");
        }
        catch (NotFoundException e) {}

        final var toCreate = converter.createRequestToDto(request);

        return repository.save(toCreate);
    }

    @Override
    public CategoryDto update(CategoryUpdateRequest request) {
        if (request.getId() == null) {
            throw new InvalidParamsException("El id de categoría es requerido", "");
        }

        try {
            repository.findByName(request.getName());

            throw new DuplicatedDataException("Ya existe una categoría con el nombre: " + request.getName(), "");
        }
        catch (NotFoundException e) {}

        final var found = repository.findById(request.getId());

        if (! StringUtils.hasText(request.getName())) {
            request.setName(found.getName());
        }

        if (! StringUtils.hasText(request.getDescription())) {
            request.setDescription(found.getDescription());
        }

        final var toUpdate = converter.updateRequestToDto(request);

        return repository.save(toUpdate);
    }

    @Override
    public CategoryDto deleteById(Long id) {
        return repository.deleteById(id);
    }
}
