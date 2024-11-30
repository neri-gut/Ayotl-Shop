package shop.ayotl.backend.repository.user;

import jakarta.validation.constraints.NotNull;
import shop.ayotl.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@NotNull String email);
}
