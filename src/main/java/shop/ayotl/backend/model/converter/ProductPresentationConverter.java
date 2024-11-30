package shop.ayotl.backend.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import shop.ayotl.backend.model.customization.ProductPresentation;

@Converter(autoApply = true)
public class ProductPresentationConverter implements AttributeConverter<ProductPresentation, String> {
    @Override
    public String convertToDatabaseColumn(ProductPresentation productPresentation) {
        return productPresentation.getPresentationName();
    }

    @Override
    public ProductPresentation convertToEntityAttribute(String presentationName) {
        return ProductPresentation.fromString(presentationName);
    }
}
