package org.ensolversapp.backend.data.model.category.mapper;

import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.model.category.Category;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.model.note.mapper.NoteMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NoteMapper.class})
public interface CategoryMapper {
    CategoryResponseDTO getResponseDTO(Category category);
    List<CategoryResponseDTO> getResponseListDTO(List<Category> categories);
    Category getCategory(CategoryReqDTO categoryReqDTO);
}
