package shop.ayotl.backend.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
