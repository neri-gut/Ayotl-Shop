package shop.ayotl.backend.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private String details;
}
