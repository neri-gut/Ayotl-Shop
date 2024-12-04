package shop.ayotl.backend.service.authentication;

import shop.ayotl.backend.config.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import shop.ayotl.backend.dto.role.RoleDto;
import shop.ayotl.backend.dto.user.UserDto;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;

    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String generateAuthenticationToken(final UserDto user) {
        final var roleNames = user.getRoles()
                .stream()
                .map(RoleDto::getName)
                .toList();

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("roles", roleNames)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtConfig.getExpiration()))
                .signWith(signingKey(), Jwts.SIG.HS512)
                .compact();
    }

    private SecretKey signingKey() {
        final var keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret());

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims claimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String usernameFromToken(String token) {
        final var claims = claimsFromToken(token);

        return claims.getSubject();
    }

    private Date expirationFromToken(String token) {
        final var claims = claimsFromToken(token);

        return claims.getExpiration();
    }

    @Override
    public boolean isTokenValid(String token) {
        final var expiration = expirationFromToken(token);

        return (new Date()).before(expiration);
    }
}
