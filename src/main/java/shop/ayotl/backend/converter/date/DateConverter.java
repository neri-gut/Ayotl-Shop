package shop.ayotl.backend.converter.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public final class DateConverter {
    private DateConverter() {}

    public static String temporalToString(Temporal temporal, String pattern) {
        final var formatter = DateTimeFormatter.ofPattern(pattern);

        return formatter.format(temporal);
    }

    public static LocalDate stringToLocalDate(String date, String pattern) {
        final var formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDate.parse(date, formatter);
    }
}
