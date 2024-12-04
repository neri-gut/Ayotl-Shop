package shop.ayotl.backend.converter.cartproduct;

import shop.ayotl.backend.dto.cartproduct.CartProductDto;
import shop.ayotl.backend.model.Cart;
import shop.ayotl.backend.model.CartProduct;
import shop.ayotl.backend.model.Product;

public class CartProductDtoConverter {
    public CartProductDto modelToDto(CartProduct model) {
        final var builder = CartProductDto.builder()
                .id(model.getId())
                .quantity(model.getQuantity());

        if (model.getCart() != null) {
            builder.cartId(model.getCart().getId());
        }

        if (model.getProduct() != null) {
            builder.productId(model.getProduct().getId());
        }

        return builder.build();
    }

    public CartProduct dtoToModel(CartProductDto dto, Cart cart, Product product) {
        return CartProduct.builder()
                .id(dto.getId())
                .cart(cart)
                .product(product)
                .quantity(dto.getQuantity())
                .build();
    }
}
