package shop.ayotl.backend.service.category;

import shop.ayotl.backend.dto.category.CategoryCreateRequest;
import shop.ayotl.backend.dto.category.CategoryDto;
import shop.ayotl.backend.dto.category.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto create(CategoryCreateRequest request);
    CategoryDto update(CategoryUpdateRequest request);
    CategoryDto deleteById(Long id);
}
