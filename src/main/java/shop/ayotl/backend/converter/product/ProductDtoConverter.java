package shop.ayotl.backend.converter.product;

import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.product.ProductCreateRequest;
import shop.ayotl.backend.dto.product.ProductDto;
import shop.ayotl.backend.dto.product.ProductUpdateRequest;
import shop.ayotl.backend.model.Product;

public class ProductDtoConverter {
    public ProductDto modelToDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .imagePath(product.getImagePath())
                .createAt(product.getCreatedAt())
                .updateAt(product.getUpdatedAt())
                .build();
    }

    public Product dtoToModel(ProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .stock(dto.getStock())
                .imagePath(dto.getImagePath())
                .createdAt(dto.getCreateAt())
                .updatedAt(dto.getUpdateAt())
                .build();
    }

    public ProductDto createRequestToDto(ProductCreateRequest request){
        return ProductDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stock(request.getStock())
                .imagePath(request.getImagePath())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }

    public ProductDto updateRequestToDo(ProductUpdateRequest request){
        return ProductDto.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .stock(request.getStock())
                .imagePath(request.getImagePath())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }

}
