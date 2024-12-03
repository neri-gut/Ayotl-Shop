package shop.ayotl.backend.repository.product;

import shop.ayotl.backend.dto.product.ProductDto;

import java.util.List;

public interface ProductRepository {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto findByCategory(Long categoryId);
    ProductDto findByName(String name);
    ProductDto save(ProductDto productDto);
    ProductDto deleteById(Long id);
}
