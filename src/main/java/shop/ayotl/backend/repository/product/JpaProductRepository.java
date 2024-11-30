package shop.ayotl.backend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Product;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
