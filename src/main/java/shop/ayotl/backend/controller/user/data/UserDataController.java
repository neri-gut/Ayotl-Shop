package shop.ayotl.backend.controller.user.data;

import shop.ayotl.backend.dto.user.data.UserDataCreateRequest;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import shop.ayotl.backend.dto.user.data.UserDataUpdateRequest;
import shop.ayotl.backend.service.user.data.UserDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{id}/data")
public class UserDataController {
    private final UserDataService service;

    public UserDataController(UserDataService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserDataDto> findByUserId(@PathVariable("id") Long id) {
        final var found = service.findByUserId(id);

        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<UserDataDto> create(@PathVariable Long id, @RequestBody @Valid UserDataCreateRequest request) {
        request.setUserId(id);

        final var created = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping
    public ResponseEntity<UserDataDto> update(@PathVariable Long id, @RequestBody @Valid UserDataUpdateRequest request) {
        request.setUserId(id);

        final var updated = service.update(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }
}
