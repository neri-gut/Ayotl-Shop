package shop.ayotl.backend.controller.category;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.ayotl.backend.dto.category.CategoryCreateRequest;
import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.category.CategoryUpdateRequest;
import shop.ayotl.backend.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        final var results = service.findAll();

        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        final var found = service.findById(id);

        return ResponseEntity.ok(found);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryCreateRequest request) {
        final var created = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryUpdateRequest request) {
        request.setId(id);

        final var updated = service.update(request);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteById(@PathVariable Long id) {
        final var deleted = service.deleteById(id);

        return ResponseEntity.ok(deleted);
    }
}
