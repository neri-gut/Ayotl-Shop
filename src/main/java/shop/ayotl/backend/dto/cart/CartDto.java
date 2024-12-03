package shop.ayotl.backend.dto.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class CartDto {
    private Long id;
    private Long userId;
    private LocalDate createdAt;

}
