package shop.ayotl.backend.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.ayotl.backend.model.Category;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
