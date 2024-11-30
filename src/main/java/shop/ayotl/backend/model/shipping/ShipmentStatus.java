package shop.ayotl.backend.model.shipping;

import lombok.Getter;
import shop.ayotl.backend.config.exception.InvalidValueException;

import java.util.Arrays;

@Getter
public enum ShipmentStatus {
    PENDING("PENDIENTE"),
    IN_TRANSIT("EN TRANSITO"),
    DELIVERED("ENTREGADO"),;

    private final String statusName;

    ShipmentStatus(String statusName) {
        this.statusName = statusName;
    }

    public static ShipmentStatus fromString(String statusName) {
        return Arrays.stream(values())
                .filter(status -> statusName.equalsIgnoreCase(status.statusName))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException("Estado '" + statusName + "' de envío no válido", ""));
    }
}
