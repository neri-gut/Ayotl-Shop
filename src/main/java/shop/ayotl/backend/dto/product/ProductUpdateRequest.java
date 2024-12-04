package shop.ayotl.backend.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private String price;
    private Long categoryId;
    private Integer stock;
    private MultipartFile imageFile;
}
