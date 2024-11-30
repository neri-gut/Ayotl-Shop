package shop.ayotl.backend.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import shop.ayotl.backend.model.customization.ProductFragrance;

@Converter(autoApply = true)
public class ProductFragranceConverter implements AttributeConverter<ProductFragrance, String> {
    @Override
    public String convertToDatabaseColumn(ProductFragrance productFragrance) {
        return productFragrance.getFragranceName();
    }

    @Override
    public ProductFragrance convertToEntityAttribute(String fragranceName) {
        return ProductFragrance.fromString(fragranceName);
    }
}
