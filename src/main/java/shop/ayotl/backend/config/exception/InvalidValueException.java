package shop.ayotl.backend.config.exception;

public class InvalidValueException extends BaseException {
    public InvalidValueException(String message, String details) {
        super(message, details);
    }
}
