package shop.ayotl.backend.controller.authentication;

import shop.ayotl.backend.dto.authentication.LoginRequest;
import shop.ayotl.backend.dto.authentication.LoginResponse;
import shop.ayotl.backend.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid final LoginRequest request) {
        final var response = authenticationService.login(request);

        return ResponseEntity.ok(response);
    }
}
