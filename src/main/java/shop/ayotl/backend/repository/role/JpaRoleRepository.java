package shop.ayotl.backend.repository.role;

import shop.ayotl.backend.model.Role;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(@NotNull String name);
}
