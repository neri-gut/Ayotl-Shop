package shop.ayotl.backend.config.exception;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException(String details) {
        super("", details);
    }
}
