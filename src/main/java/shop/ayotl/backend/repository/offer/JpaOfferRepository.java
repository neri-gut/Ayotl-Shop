package shop.ayotl.backend.repository.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Offer;

public interface JpaOfferRepository extends JpaRepository<Offer, Long> {
}
