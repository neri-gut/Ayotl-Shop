package shop.ayotl.backend.converter.product;

import org.springframework.util.StringUtils;
import shop.ayotl.backend.common.constant.DatePattern;
import shop.ayotl.backend.converter.bigdecimal.BigDecimalConverter;
import shop.ayotl.backend.converter.date.DateConverter;
import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.product.ProductCreateRequest;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.dto.product.ProductOutputDto;
import shop.ayotl.backend.dto.product.ProductUpdateRequest;
import shop.ayotl.backend.model.Category;
import shop.ayotl.backend.model.Product;

public class ProductDtoConverter {
    private static final String DATE_PATTERN = DatePattern.DD_MM_YYYY;

    public ProductDto modelToDto(Product product, CategoryDto category) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(category)
                .stock(product.getStock())
                .imagePath(product.getImagePath())
                .imageMimeType(product.getImageMimeType())
                .createAt(product.getCreatedAt())
                .updateAt(product.getUpdatedAt())
                .build();
    }

    public Product dtoToModel(ProductDto dto, Category category) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(category)
                .stock(dto.getStock())
                .imagePath(dto.getImagePath())
                .imageMimeType(dto.getImageMimeType())
                .createdAt(dto.getCreateAt())
                .updatedAt(dto.getUpdateAt())
                .build();
    }

    public ProductOutputDto dtoToOutputDto(ProductDto dto, String imageDataUrl) {
        return ProductOutputDto
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(BigDecimalConverter.toString(dto.getPrice()))
                .category(dto.getCategory())
                .stock(dto.getStock())
                .imageDataUrl(imageDataUrl)
                .createdAt(DateConverter.temporalToString(dto.getCreateAt(), DATE_PATTERN))
                .updatedAt(DateConverter.temporalToString(dto.getUpdateAt(), DATE_PATTERN))
                .build();
    }

    public ProductDto createRequestToDto(ProductCreateRequest request, CategoryDto category){
        return ProductDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(BigDecimalConverter.fromString(request.getPrice()))
                .category(category)
                .stock(request.getStock())
                .build();
    }

    public ProductDto updateRequestToDo(ProductUpdateRequest request, CategoryDto category){
        final var builder = ProductDto.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .stock(request.getStock());

        if (StringUtils.hasText(request.getPrice())) {
            builder.price(BigDecimalConverter.fromString(request.getPrice()));
        }

        return builder.build();
    }
}
