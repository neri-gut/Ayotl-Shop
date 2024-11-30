package shop.ayotl.backend.config.security;

import shop.ayotl.backend.config.security.userdetails.UserDetailsImpl;
import shop.ayotl.backend.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {
    private final UserRepository userRepository;

    public AuthenticationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    // Removes the ROLE_ default prefix for roles
    // Source: https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
    ) {
        final var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            final var user = userRepository.findByEmail(email);

            return UserDetailsImpl.buildUserDetails(user);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
