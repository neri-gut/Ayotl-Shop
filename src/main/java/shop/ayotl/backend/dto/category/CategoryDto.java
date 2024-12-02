package shop.ayotl.backend.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
