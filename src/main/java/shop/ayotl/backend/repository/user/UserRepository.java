package shop.ayotl.backend.repository.user;

import shop.ayotl.backend.dto.user.UserDto;

import java.util.List;

public interface UserRepository {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto findByEmail(String email);
    UserDto save(UserDto user);
    void deleteById(Long id);
}
