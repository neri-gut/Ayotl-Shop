package shop.ayotl.backend.controller.cart;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.ayotl.backend.dto.cart.CartCreateRequest;
import shop.ayotl.backend.dto.cart.CartOutputDto;
import shop.ayotl.backend.service.cart.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CartOutputDto> findById(@PathVariable Long id){
        final var found = service.findById(id);
        return ResponseEntity.ok(found);
    }

    @GetMapping ("/users/{userId}")
    public ResponseEntity<CartOutputDto> findByUserId(@PathVariable Long userId){
        final var found = service.findByUserId(userId);
        return ResponseEntity.ok(found);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartOutputDto> create(@RequestBody @Valid CartCreateRequest request){
        final var created = service.create(request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
