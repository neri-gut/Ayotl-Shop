package shop.ayotl.backend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCategory_id(Long userId);
    Optional<Product> findByName(String name);
}
