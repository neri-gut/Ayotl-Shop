package shop.ayotl.backend.repository.product;

import shop.ayotl.backend.dto.product.ProductDto;

import java.util.List;

public interface ProductRepository {
    List<ProductDto> findAllWithFilters(Long categoryId, String name);
    ProductDto findById(Long id);
    ProductDto save(ProductDto productDto);
    ProductDto deleteById(Long id);
}
