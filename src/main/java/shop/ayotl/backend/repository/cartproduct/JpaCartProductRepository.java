package shop.ayotl.backend.repository.cartproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.CartProduct;

import java.util.Optional;

public interface JpaCartProductRepository extends JpaRepository<CartProduct, Long> {
    Optional<CartProduct> findByCart_IdAndProduct_Id(Long cartId, Long productId);
}
