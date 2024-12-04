package shop.ayotl.backend.service.product;

import shop.ayotl.backend.dto.product.*;

import java.util.List;

public interface ProductService {
    List<ProductInCartOutputDto> findAllInUserCart();
    List<ProductOutputDto> findAll(ProductFilter filter);
    ProductOutputDto findById(Long id);
    ProductOutputDto create(ProductCreateRequest request);
    ProductOutputDto update(ProductUpdateRequest request);
    ProductOutputDto deleteById(Long id);
}
