package shop.ayotl.backend.controller.cartproduct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.ayotl.backend.service.cartproduct.CartProductService;

@RestController
@RequestMapping("/api/cart-products")
public class CartProductController {
    private final CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long productId) {
        cartProductService.addProductToCart(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
