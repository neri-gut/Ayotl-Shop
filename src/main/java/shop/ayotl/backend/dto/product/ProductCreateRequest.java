package shop.ayotl.backend.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import shop.ayotl.backend.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private Category category;
    @NotBlank
    private Integer stock;
    @NotBlank
    private String imagePath;
    @NotBlank
    private LocalDate createAt;
    @NotBlank
    private LocalDate updateAt;
}
