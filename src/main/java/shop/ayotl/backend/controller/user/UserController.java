package shop.ayotl.backend.controller.user;

import shop.ayotl.backend.dto.user.UserCreateRequest;
import shop.ayotl.backend.dto.user.UserOutputDto;
import shop.ayotl.backend.dto.user.UserPasswordUpdateRequest;
import shop.ayotl.backend.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/users", "/api/users/"})
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> findAll() {
        final var users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDto> findById(@PathVariable final Long id) {
        final var found = userService.findById(id);

        return ResponseEntity.ok(found);
    }

    @PatchMapping(value = "/{id}/password-update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid final UserPasswordUpdateRequest request) {
        request.setId(id);
        userService.updatePassword(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserOutputDto> create(@RequestBody @Valid final UserCreateRequest request) {
        final var created = userService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
