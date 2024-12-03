package shop.ayotl.backend.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Cart;

import java.util.Optional;

public interface JpaCartRepository extends JpaRepository<Cart, Long> {
Optional<Cart> findByUser_id(Long userId);
}
