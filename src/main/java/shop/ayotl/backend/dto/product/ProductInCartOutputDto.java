package shop.ayotl.backend.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductInCartOutputDto {
    private Long id;
    private String name;
    private String imageDataUrl;
    private Integer quantity;
}
