package shop.ayotl.backend.repository.product;

import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.product.ProductDtoConverter;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.repository.category.JpaCategoryRepository;

import java.util.List;

@Repository
public class MySQLProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final ProductDtoConverter converter;
    private final JpaCategoryRepository jpaCategoryRepository;

    public MySQLProductRepository(JpaProductRepository jpaProductRepository, ProductDtoConverter converter, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.converter = converter;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }


    @Override
    public List<ProductDto> findAll() {
        return jpaProductRepository.findAll()
                .stream()
                .map(converter::modelToDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        final var found= jpaProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("producto no encontrado", ""));
        return converter.modelToDto(found);
    }

    @Override
    public ProductDto findByCategory(Long categoryId) {
        final var found= jpaProductRepository.findByCategory_id(categoryId)
                .orElseThrow(() -> new NotFoundException("categoria no encontrada", ""));
        return converter.modelToDto(found);
    }

    @Override
    public ProductDto findByName(String name) {
        final var found= jpaProductRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("producto no encontrado", ""));
        return converter.modelToDto(found);
    }

    @Override
    public ProductDto save(ProductDto productDto) throws PersistenceException {
        final var toSave= converter.dtoToModel(productDto);

        try {
            final var saved = jpaProductRepository.save(toSave);
            return converter.modelToDto(saved);
        }catch (Exception e){
            throw new PersistenceException("Error al guardar el producto", e.getMessage());
        }
    }

    @Override
    public ProductDto deleteById(Long id) {
        final var found = findById(id);
        try {
            jpaProductRepository.deleteById(id);
            return found;
        }catch (Exception e){
            throw  new PersistenceException("Error al eliminar la categoria", e.getMessage());
        }
    }
}
