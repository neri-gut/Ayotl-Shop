package shop.ayotl.backend.repository.cartProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.CartProduct;

import java.util.Optional;

public interface JpaCartProductRepository extends JpaRepository<CartProductRepository, Long > {
    Optional<CartProduct> findByproduct_Id(Long productId);

}
