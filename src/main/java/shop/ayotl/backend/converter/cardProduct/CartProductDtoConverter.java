package shop.ayotl.backend.converter.cardProduct;

import shop.ayotl.backend.dto.cartProduct.CartProductDto;

import shop.ayotl.backend.model.CartProduct;

public class CartProductDtoConverter {
    public CartProductDto modelToDto(CartProduct model){
        return CartProductDto
                .builder()
                .id(model.getId())
                .cartId(model.getCart().getId())
                .productId(model.getProduct().getId())
                .quantity(Long.valueOf(model.getQuantity()))
                .build();
    }
}
/*  private Long id;
    private Long cartId;
    private Long productId;
    private Long quantity;
/*.id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .category(product.getCategory())
        .stock(product.getStock())
        .imagePath(product.getImagePath())
        .createAt(product.getCreatedAt())
        .updateAt(product.getUpdatedAt())
        .build();
 return Cart.builder().id(dto.getId()).user(user).createdAt(dto.getCreatedAt()).build();*/