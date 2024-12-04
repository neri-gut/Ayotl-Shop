package shop.ayotl.backend.config.exception;

import shop.ayotl.backend.common.response.ErrorResponse;
import shop.ayotl.backend.util.FieldBeanExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // <--- 400 BAD REQUEST --->
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var response = ErrorResponse
                .builder()
                .message("Malformed request")
                .details(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {InvalidParamsException.class})
    protected ResponseEntity<ErrorResponse> handleInvalidParamsException(InvalidParamsException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {InvalidValueException.class})
    protected ResponseEntity<ErrorResponse> handleInvalidParamsException(InvalidValueException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {DuplicatedDataException.class})
    protected ResponseEntity<ErrorResponse> handleDuplicatedDataException(DuplicatedDataException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Bean fields validation failed
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var errors = FieldBeanExceptionUtil.fieldErrorsToString(ex.getBindingResult().getFieldErrors());

        log.error("Field errors: \n{}", errors, ex);

        final var response = ErrorResponse
                .builder()
                .message("Request Data error")
                .details(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // <--- 401 Unauthorized --->
    @ExceptionHandler(value = {InvalidCredentialsException.class})
    protected ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // <--- 403 FORBIDDEN --->
    @ExceptionHandler(value = {ForbiddenException.class})
    protected ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        final var response = ErrorResponse
                .builder()
                .message("Forbidden")
                .details(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    // <--- 404 NOT FOUND --->
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // <--- 500 INTERNAL SERVER ERROR --->
    @ExceptionHandler(value = {PersistenceException.class})
    protected ResponseEntity<ErrorResponse> handlePersistenceException(PersistenceException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {FormatException.class})
    protected ResponseEntity<ErrorResponse> handleFormatException(FormatException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {IOException.class})
    protected ResponseEntity<ErrorResponse> handleFormatException(IOException exception) {
        final var response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .details(exception.getDetails())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {
        log.error("General error", exception);
        final var response = ErrorResponse
                .builder()
                .message("General error")
                .details(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
