package shop.ayotl.backend.repository.user.data;

import shop.ayotl.backend.dto.user.data.UserDataDto;

public interface UserDataRepository {
    UserDataDto findByUserId(Long userId);
    UserDataDto save(UserDataDto user);
    void deleteByUserId(Long userId);
}
