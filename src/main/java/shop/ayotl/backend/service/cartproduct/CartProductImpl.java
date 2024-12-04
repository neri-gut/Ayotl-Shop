package shop.ayotl.backend.service.cartproduct;

import org.springframework.stereotype.Service;
import shop.ayotl.backend.config.exception.DomainException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.dto.cart.CartDto;
import shop.ayotl.backend.dto.cartproduct.CartProductDto;
import shop.ayotl.backend.repository.cart.CartRepository;
import shop.ayotl.backend.repository.cartproduct.CartProductRepository;
import shop.ayotl.backend.repository.product.ProductRepository;
import shop.ayotl.backend.service.authentication.AuthenticationService;

@Service
public class CartProductImpl implements CartProductService {
    private final CartProductRepository repository;
    private final AuthenticationService authenticationService;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartProductImpl(
            CartProductRepository repository,
            AuthenticationService authenticationService,
            CartRepository cartRepository,
            ProductRepository productRepository
    ) {
        this.repository = repository;
        this.authenticationService = authenticationService;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(Long productId) {
        final var authUser = authenticationService.authenticatedUser();
        CartDto userCart = null;

        try {
            userCart = cartRepository.findByUserId(authUser.getId());
        }
        catch (NotFoundException e) {
            userCart = CartDto.builder().userId(authUser.getId()).build();
            userCart = cartRepository.save(userCart);
        }

        final var product = productRepository.findById(productId);

        CartProductDto found;

        try {
            found = repository.findByCartIdAndProductId(userCart.getId(), productId);
        }
        catch (NotFoundException e) {
            found = CartProductDto.builder()
                    .cartId(userCart.getId())
                    .productId(productId)
                    .quantity(0)
                    .build();
        }

        if (product.getStock() < found.getQuantity() + 1) {
            throw new DomainException("No hay suficiente stock del producto", "");
        }

        found.setQuantity(found.getQuantity() + 1);

        repository.save(found);
    }
}
