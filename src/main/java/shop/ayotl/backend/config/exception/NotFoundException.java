package shop.ayotl.backend.config.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, String details) {
        super(message, details);
    }
}
