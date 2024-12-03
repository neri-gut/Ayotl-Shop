package shop.ayotl.backend.dto.cartProduct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartProductDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Long quantity;
}

