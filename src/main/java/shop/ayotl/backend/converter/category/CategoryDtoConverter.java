package shop.ayotl.backend.converter.category;

import shop.ayotl.backend.dto.category.CategoryCreateRequest;
import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.category.CategoryUpdateRequest;
import shop.ayotl.backend.model.Category;

public class CategoryDtoConverter {
    public CategoryDto modelToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category dtoToModel(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public CategoryDto createRequestToDto(CategoryCreateRequest request) {
        return CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CategoryDto updateRequestToDto(CategoryUpdateRequest request) {
        return CategoryDto.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
