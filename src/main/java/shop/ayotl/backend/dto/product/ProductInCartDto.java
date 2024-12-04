package shop.ayotl.backend.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductInCartDto {
    private Long id;
    private String name;
    private String imagePath;
    private String imageMimeType;
    private Integer quantity;
}
