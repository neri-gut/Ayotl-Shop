package shop.ayotl.backend.repository.shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.shipping.Shipment;

public interface JpaShipmentRepository extends JpaRepository<Shipment, Long> {
}
