package shop.ayotl.backend.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    protected final String message;
    protected final String details;
}
