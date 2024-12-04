package shop.ayotl.backend.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.ayotl.backend.dto.category.CategoryDto;

@Getter
@Setter
@Builder
public class ProductOutputDto {
    private Long id;
    private String name;
    private String description;
    private String price;
    private CategoryDto category;
    private Integer stock;
    private String imageDataUrl;
    private String createdAt;
    private String updatedAt;
}
