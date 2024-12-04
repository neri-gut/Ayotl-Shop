package shop.ayotl.backend.dto.cartproduct;

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
    private Integer quantity;
}
