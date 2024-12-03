package shop.ayotl.backend.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.ayotl.backend.model.Category;

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
    private Category category;
    private Integer stock;
    private String imagePath;
    private LocalDate createAt;
    private LocalDate updateAt;
}
