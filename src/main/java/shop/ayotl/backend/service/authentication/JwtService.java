package shop.ayotl.backend.service.authentication;

import shop.ayotl.backend.dto.user.UserDto;

public interface JwtService {
    String generateAuthenticationToken(UserDto user);
    String usernameFromToken(String token);
    boolean isTokenValid(String token);
}
