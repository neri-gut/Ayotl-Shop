package shop.ayotl.backend.controller.cartproduct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.ayotl.backend.dto.product.ProductInCartOutputDto;
import shop.ayotl.backend.service.cartproduct.CartProductService;
import shop.ayotl.backend.service.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/cart-products")
public class CartProductController {
    private final CartProductService cartProductService;
    private final ProductService productService;

    public CartProductController(CartProductService cartProductService, ProductService productService) {
        this.cartProductService = cartProductService;
        this.productService = productService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long productId) {
        cartProductService.addProductToCart(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductInCartOutputDto>> findAllInUserCart() {
        final var results = productService.findAllInUserCart();

        return ResponseEntity.ok(results);
    }
}
