package shop.ayotl.backend.controller.product;

import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.ayotl.backend.dto.product.*;
import shop.ayotl.backend.service.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> findAll(ProductFilter filter) {
        final var results = service.findAll(filter);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDto> findById(@PathVariable Long id){
        final var found = service.findById(id);
        return ResponseEntity.ok(found);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOutputDto> create(@ModelAttribute @Valid ProductCreateRequest request){
        final var created = service.create(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOutputDto> update(@PathVariable Long id, @ModelAttribute ProductUpdateRequest request){
        request.setId(id);

        final var updated = service.update(request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductOutputDto> deleteById(@PathVariable Long id){
        final var deleted = service.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

}
