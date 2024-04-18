package org.ensolversapp.backend.service.category;

import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;

public interface CategoryService {
    void deleteCategory(Long categoryId);
    CategoryResponseDTO createCategory(CategoryReqDTO categoryReqDTO, Long noteId);
}
