package shop.ayotl.backend.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import shop.ayotl.backend.model.shipping.ShipmentStatus;

@Converter(autoApply = true)
public class ShipmentStatusConverter implements AttributeConverter<ShipmentStatus, String> {

    @Override
    public String convertToDatabaseColumn(ShipmentStatus shipmentStatus) {
        return shipmentStatus.name();
    }

    @Override
    public ShipmentStatus convertToEntityAttribute(String statusName) {
        return ShipmentStatus.fromString(statusName);
    }
}
