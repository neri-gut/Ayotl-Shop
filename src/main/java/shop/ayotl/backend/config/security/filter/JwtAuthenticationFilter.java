package shop.ayotl.backend.config.security.filter;

import shop.ayotl.backend.service.authentication.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static shop.ayotl.backend.common.constant.AuthenticationConstants.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService= jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().contains(LOGIN_URL_MAPPING)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (! StringUtils.hasText(authHeader) || ! authHeader.startsWith(TOKEN_TYPE_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final var jwt = authHeader.substring(TOKEN_TYPE_PREFIX.length());
        final String username = jwtService.usernameFromToken(jwt);
        final var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (username == null || authentication != null) {
            filterChain.doFilter(request, response);
            return;
        }

        final var userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(jwt)) {
            final var usrPassAuthToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            usrPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usrPassAuthToken);
        }

        filterChain.doFilter(request, response);
    }
}
