package shop.ayotl.backend.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartCreateRequest {
    @NotNull
    private Long userId;
}
