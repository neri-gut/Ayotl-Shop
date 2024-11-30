package shop.ayotl.backend.service.authentication;

public interface JwtService {
    String generateAuthenticationToken(String username);
    String usernameFromToken(String token);
    boolean isTokenValid(String token);
}
