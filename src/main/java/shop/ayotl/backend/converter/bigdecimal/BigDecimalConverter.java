package shop.ayotl.backend.converter.bigdecimal;

import shop.ayotl.backend.config.exception.FormatException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BigDecimalConverter {
    private BigDecimalConverter() {}

    private static final int DEFAULT_SCALE = 2;
    private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal fromString(String number) {
        try {
            return new BigDecimal(number).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        }
        catch (Exception e) {
            throw new FormatException("Error al leer el número: " + number, e.getMessage());
        }
    }

    public static String toString(BigDecimal bigDecimal) {
        return bigDecimal
                .setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE)
                .toString();
    }
}
