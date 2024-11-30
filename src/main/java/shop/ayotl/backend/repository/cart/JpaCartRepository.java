package shop.ayotl.backend.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Cart;

public interface JpaCartRepository extends JpaRepository<Cart, Long> {
}
