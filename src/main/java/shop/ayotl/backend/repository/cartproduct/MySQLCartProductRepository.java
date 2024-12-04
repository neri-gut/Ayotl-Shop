package shop.ayotl.backend.repository.cartproduct;

import org.springframework.stereotype.Repository;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.config.exception.PersistenceException;
import shop.ayotl.backend.converter.cartproduct.CartProductDtoConverter;
import shop.ayotl.backend.dto.cartproduct.CartProductDto;
import shop.ayotl.backend.repository.cart.JpaCartRepository;
import shop.ayotl.backend.repository.product.JpaProductRepository;

@Repository
public class MySQLCartProductRepository implements CartProductRepository {
    private final JpaCartProductRepository jpaCartProductRepository;
    private final JpaCartRepository jpaCartRepository;
    private final JpaProductRepository jpaProductRepository;
    private final CartProductDtoConverter converter;

    public MySQLCartProductRepository(
            JpaCartProductRepository jpaCartProductRepository,
            JpaCartRepository jpaCartRepository,
            JpaProductRepository jpaProductRepository,
            CartProductDtoConverter converter
    ) {
        this.jpaCartProductRepository = jpaCartProductRepository;
        this.jpaCartRepository = jpaCartRepository;
        this.jpaProductRepository = jpaProductRepository;
        this.converter = converter;
    }

    @Override
    public CartProductDto findByCartIdAndProductId(Long cartId, Long productId) {
        final var found = jpaCartProductRepository.findByCart_IdAndProduct_Id(cartId, productId)
                .orElseThrow(() -> new NotFoundException("No se encontró el producto dentro del carrito", ""));

        return converter.modelToDto(found);
    }

    @Override
    public CartProductDto save(final CartProductDto dto) {
        final var cart = jpaCartRepository.findById(dto.getCartId())
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado", ""));
        final var product = jpaProductRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado", ""));

        final var toCreate = converter.dtoToModel(dto, cart, product);

        try {
            final var saved = jpaCartProductRepository.save(toCreate);

            return converter.modelToDto(saved);
        }
        catch (Exception e) {
            throw new PersistenceException("Error al actualizar el producto dentro del carrito", e.getMessage());
        }
    }
}
