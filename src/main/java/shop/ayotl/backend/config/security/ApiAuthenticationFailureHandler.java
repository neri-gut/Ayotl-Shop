package shop.ayotl.backend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import shop.ayotl.backend.common.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiAuthenticationFailureHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        resolveException(request, response, authException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        resolveException(request, response, accessDeniedException);
    }

    private static void resolveException(HttpServletRequest request, HttpServletResponse response, RuntimeException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        final var objectMapper = new ObjectMapper();
        final var errorResponse = ErrorResponse
                .builder()
                .message("Invalid or expired token")
                .details(exception.getMessage())
                .build();

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
