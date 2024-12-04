package shop.ayotl.backend.repository.product;

import org.springframework.stereotype.Repository;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.category.CategoryDtoConverter;
import shop.ayotl.backend.converter.product.ProductDtoConverter;
import shop.ayotl.backend.dto.product.ProductDto;

import java.util.List;

@Repository
public class MySQLProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final ProductDtoConverter converter;
    private final CategoryDtoConverter categoryConverter;

    public MySQLProductRepository(
            JpaProductRepository jpaProductRepository,
            ProductDtoConverter converter,
            CategoryDtoConverter categoryConverter
    ) {
        this.jpaProductRepository = jpaProductRepository;
        this.converter = converter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<ProductDto> findAllWithFilters(Long categoryId, String name) {
        final var results = jpaProductRepository.findAllWithFilters(categoryId, name);

        return results
                .stream()
                .map(product -> {
                    final var category = categoryConverter.modelToDto(product.getCategory());

                    return converter.modelToDto(product, category);
                })
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        final var found= jpaProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado", ""));
        final var category = categoryConverter.modelToDto(found.getCategory());

        return converter.modelToDto(found, category);
    }

    @Override
    public ProductDto save(ProductDto productDto) throws PersistenceException {
        final var category = categoryConverter.dtoToModel(productDto.getCategory());
        final var toSave = converter.dtoToModel(productDto, category);

        try {
            final var saved = jpaProductRepository.save(toSave);
            final var categoryDto = categoryConverter.modelToDto(saved.getCategory());

            return converter.modelToDto(saved, categoryDto);
        }
        catch (Exception e){
            throw new PersistenceException("Error al guardar el producto", e.getMessage());
        }
    }

    @Override
    public ProductDto deleteById(Long id) {
        final var found = findById(id);

        try {
            jpaProductRepository.deleteById(id);

            return found;
        }
        catch (Exception e){
            throw  new PersistenceException("Error al eliminar la categoria", e.getMessage());
        }
    }
}
