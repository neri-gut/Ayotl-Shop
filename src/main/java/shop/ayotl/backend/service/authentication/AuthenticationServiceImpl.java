package shop.ayotl.backend.service.authentication;

import shop.ayotl.backend.config.exception.InvalidCredentialsException;
import shop.ayotl.backend.dto.authentication.LoginRequest;
import shop.ayotl.backend.dto.authentication.LoginResponse;
import shop.ayotl.backend.dto.user.UserDto;
import shop.ayotl.backend.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            UserRepository userRepository
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public LoginResponse login(final LoginRequest request) {
        final var usrPassAuthToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            authenticationManager.authenticate(usrPassAuthToken);
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException(e.getMessage());
        }

        final var userFound = userRepository.findByEmail(request.getEmail());
        final var jwt = jwtService.generateAuthenticationToken(userFound);

        return new LoginResponse(jwt);
    }

    @Override
    public UserDto authenticatedUser() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        final var email= authentication.getName();

        return userRepository.findByEmail(email);
    }
}
