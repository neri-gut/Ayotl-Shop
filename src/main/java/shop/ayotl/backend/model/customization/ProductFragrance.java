package shop.ayotl.backend.model.customization;

import lombok.Getter;
import shop.ayotl.backend.config.exception.InvalidValueException;

import java.util.Arrays;

@Getter
public enum ProductFragrance {
    FLORAL("FLORAL"),
    WOODY("AMADERADO"),
    CITRIC("CITRICO"),
    FRUITY("FRUTAL"),
    SWEET("DULCE"),
    WATERY("ACUOSO"),
    SPICY("ESPECIADO");

    private final String fragranceName;

    ProductFragrance(String fragranceName) {
        this.fragranceName = fragranceName;
    }

    public static ProductFragrance fromString(String fragranceName) {
        return Arrays.stream(values())
                .filter(fragrance -> fragranceName.equalsIgnoreCase(fragrance.fragranceName))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException("Fragancia " + fragranceName + " no válida", ""));
    }

}
