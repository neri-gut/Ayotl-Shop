package shop.ayotl.backend.model.order;

import lombok.Getter;
import shop.ayotl.backend.config.exception.InvalidValueException;

import java.util.Arrays;

@Getter
public enum OrderStatus {
    PENDING("PENDIENTE"),
    PROCESSED("PROCESADO"),
    SENT("ENVIADO"),
    DELIVERED("ENTREGADO"),
    CANCELLED("CANCELADO");

    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public static OrderStatus fromString(String statusName) {
        return Arrays.stream(values())
                .filter(status -> statusName.equalsIgnoreCase(status.statusName))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException("Estado '" + statusName + "' de orden no válido", ""));
    }
}
