package shop.ayotl.backend.repository.category;

import shop.ayotl.backend.dto.category.CategoryDto;

import java.util.List;

public interface CategoryRepository {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto findByName(String name);
    CategoryDto save(CategoryDto dto);
    CategoryDto deleteById(Long id);
}
