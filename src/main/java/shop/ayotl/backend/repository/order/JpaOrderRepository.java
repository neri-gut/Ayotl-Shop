package shop.ayotl.backend.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.order.Order;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
