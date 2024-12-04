package shop.ayotl.backend.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.ayotl.backend.dto.category.CategoryDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDto category;
    private Integer stock;
    private String imagePath;
    private String imageMimeType;
    private LocalDate createAt;
    private LocalDate updateAt;
}
