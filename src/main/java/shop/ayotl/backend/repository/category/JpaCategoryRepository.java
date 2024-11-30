package shop.ayotl.backend.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Category;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
}
