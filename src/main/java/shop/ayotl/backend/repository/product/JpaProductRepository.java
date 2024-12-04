package shop.ayotl.backend.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.ayotl.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCategory_id(Long userId);

    @Query("""
SELECT p
FROM Product p
INNER JOIN p.category c
WHERE (c.id = :categoryId OR :categoryId IS NULL)
    AND (LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%') OR :name IS NULL OR LENGTH(TRIM(:name)) = 0)
""")
    List<Product> findAllWithFilters(@Param("categoryId") Long categoryId, @Param("name") String name);
}