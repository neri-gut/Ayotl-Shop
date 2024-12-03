package shop.ayotl.backend.service.product;

import shop.ayotl.backend.dto.product.ProductCreateRequest;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.dto.product.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto create(ProductCreateRequest request);
    ProductDto update(ProductUpdateRequest request);
    ProductDto deleteById(Long id);
}
