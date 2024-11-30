package shop.ayotl.backend.service.user;

import shop.ayotl.backend.dto.user.UserCreateRequest;
import shop.ayotl.backend.dto.user.UserOutputDto;
import shop.ayotl.backend.dto.user.UserPasswordUpdateRequest;

import java.util.List;

public interface UserService {
    List<UserOutputDto> findAll();
    UserOutputDto findById(Long id);
    UserOutputDto create(UserCreateRequest request);
    void updatePassword(UserPasswordUpdateRequest request);
    void deleteById(Long id);
}
