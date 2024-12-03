package shop.ayotl.backend.dto.cart;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartOutputDto {
    private Long id;
    private Long userId;
    private String createdAt;
}
