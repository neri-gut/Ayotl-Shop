package shop.ayotl.backend.service.authentication;

import shop.ayotl.backend.dto.authentication.LoginRequest;
import shop.ayotl.backend.dto.authentication.LoginResponse;
import shop.ayotl.backend.dto.user.UserDto;

public interface AuthenticationService {
    LoginResponse login(final LoginRequest request);
    UserDto authenticatedUser();
}
