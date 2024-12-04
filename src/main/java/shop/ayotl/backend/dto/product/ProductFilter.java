package shop.ayotl.backend.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {
    private Long categoryId;
    private String name;
}
