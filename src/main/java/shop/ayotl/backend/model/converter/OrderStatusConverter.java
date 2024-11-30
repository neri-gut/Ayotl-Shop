package shop.ayotl.backend.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import shop.ayotl.backend.model.order.OrderStatus;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.getStatusName();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String statusName) {
        return OrderStatus.fromString(statusName);
    }
}
