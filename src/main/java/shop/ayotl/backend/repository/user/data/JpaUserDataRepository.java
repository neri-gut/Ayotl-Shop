package shop.ayotl.backend.repository.user.data;

import shop.ayotl.backend.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
