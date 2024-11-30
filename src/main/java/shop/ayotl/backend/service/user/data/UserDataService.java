package shop.ayotl.backend.service.user.data;

import shop.ayotl.backend.dto.user.data.UserDataCreateRequest;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import shop.ayotl.backend.dto.user.data.UserDataUpdateRequest;

public interface UserDataService {
    UserDataDto findByUserId(Long userId);
    UserDataDto create(UserDataCreateRequest request);
    UserDataDto update(UserDataUpdateRequest request);
}
