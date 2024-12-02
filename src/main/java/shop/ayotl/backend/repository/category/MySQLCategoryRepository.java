package shop.ayotl.backend.repository.category;

import org.springframework.stereotype.Repository;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.category.CategoryDtoConverter;
import shop.ayotl.backend.dto.category.CategoryDto;

import java.util.List;

@Repository
public class MySQLCategoryRepository implements CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryDtoConverter converter;

    public MySQLCategoryRepository(JpaCategoryRepository jpaCategoryRepository, CategoryDtoConverter converter) {
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.converter = converter;
    }

    @Override
    public List<CategoryDto> findAll() {
        return jpaCategoryRepository.findAll()
                .stream()
                .map(converter::modelToDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        final var found = jpaCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria no encontrada", ""));

        return converter.modelToDto(found);
    }

    @Override
    public CategoryDto findByName(String name) {
        final var found = jpaCategoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Categoria no encontrada", ""));

        return converter.modelToDto(found);
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        final var toSave = converter.dtoToModel(dto);

        try {
            final var saved = jpaCategoryRepository.save(toSave);

            return converter.modelToDto(saved);
        }
        catch (Exception e) {
            throw new PersistenceException("Error al guardar la categoría", e.getMessage());
        }
    }

    @Override
    public CategoryDto deleteById(Long id) {
        final var found = findById(id);

        try {
            jpaCategoryRepository.deleteById(id);

            return found;
        }
        catch (Exception e) {
            throw new PersistenceException("Error al eliminar la categoría", e.getMessage());
        }
    }
}
