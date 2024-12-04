package shop.ayotl.backend.config.security;

import shop.ayotl.backend.config.security.filter.JwtAuthenticationFilter;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static shop.ayotl.backend.common.constant.AuthenticationConstants.CREATE_USER_URL_MAPPING;
import static shop.ayotl.backend.common.constant.AuthenticationConstants.LOGIN_URL_MAPPING;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final ApiAuthenticationFailureHandler apiAuthenticationFailureHandler;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;


    public SecurityConfig(
            ApiAuthenticationFailureHandler apiAuthenticationFailureHandler,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthFilter
    ) {
        this.apiAuthenticationFailureHandler = apiAuthenticationFailureHandler;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        final var mvcReqMatcher = new MvcRequestMatcher.Builder(introspector);

        http
                .csrf(csrfConfigurerCustomizer())
                .cors(cors -> cors.configure(http))
                .sessionManagement(sessionMgmt -> sessionMgmt.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(mvcReqMatcher.pattern("/")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.POST, LOGIN_URL_MAPPING)).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.POST, CREATE_USER_URL_MAPPING)).permitAll()
                                .requestMatchers(mvcReqMatcher.pattern("/api/**")).authenticated()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @NotNull
    private Customizer<CsrfConfigurer<HttpSecurity>> csrfConfigurerCustomizer() {
        return csrf -> {
            try {
                csrf.disable()
                        .exceptionHandling(
                                exceptionHandling -> exceptionHandling
                                        .authenticationEntryPoint(apiAuthenticationFailureHandler)
                                        .accessDeniedHandler(apiAuthenticationFailureHandler)
                        );
            } catch (Exception e) {
                throw new CsrfException(e.getMessage());
            }
        };
    }
}