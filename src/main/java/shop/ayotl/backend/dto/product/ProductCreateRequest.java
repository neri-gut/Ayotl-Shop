package shop.ayotl.backend.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String price;

    @NotNull
    private Long categoryId;

    @NotNull
    private Integer stock;

    private MultipartFile imageFile;
}
