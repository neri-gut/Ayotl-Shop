package shop.ayotl.backend.util;

import org.springframework.validation.FieldError;

import java.util.List;

public final class FieldBeanExceptionUtil {
    private FieldBeanExceptionUtil() {}

    public static String fieldErrorsToString(List<FieldError> fieldErrors) {
        return String.join(
                "\n",
                fieldErrors
                        .stream()
                        .map(FieldBeanExceptionUtil::constructuFieldErrorMessageString)
                        .toList()
        );
    }

    private static String constructuFieldErrorMessageString(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}
