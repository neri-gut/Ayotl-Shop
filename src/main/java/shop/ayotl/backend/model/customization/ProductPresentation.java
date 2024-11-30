package shop.ayotl.backend.model.customization;

import lombok.Getter;
import shop.ayotl.backend.config.exception.InvalidValueException;

import java.util.Arrays;

@Getter
public enum ProductPresentation {
    HUNDRED_ML("100ML"),
    HUNDRED_AND_HALF_ML("150ML"),
    TWO_HUNDRED_ML("200ML"),;

    private final String presentationName;

    ProductPresentation(String presentationName) {
        this.presentationName = presentationName;
    }

    public static ProductPresentation fromString(String presentationName) {
        return Arrays.stream(values())
                .filter(presentation -> presentationName.equalsIgnoreCase(presentation.presentationName))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException("Presentación " + presentationName + " no válida", ""));
    }

}
