package shop.ayotl.backend.repository.product;

import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.dto.product.ProductInCartDto;

import java.util.List;

public interface ProductRepository {
    List<ProductInCartDto> findAllInUserCart(Long userId, Long cartId);
    List<ProductDto> findAllWithFilters(Long categoryId, String name);
    ProductDto findById(Long id);
    ProductDto save(ProductDto productDto);
    ProductDto deleteById(Long id);
}
