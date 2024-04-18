package org.ensolversapp.backend.service.category;

import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.model.EntityName;
import org.ensolversapp.backend.data.model.category.Category;
import org.ensolversapp.backend.data.model.category.mapper.CategoryMapper;
import org.ensolversapp.backend.data.model.error.exception.EntityNotFoundException;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.repository.CategoryRepository;
import org.ensolversapp.backend.data.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import java.time.Instant;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private NoteRepository noteRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, NoteRepository noteRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.noteRepository = noteRepository;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException(EntityName.CATEGORY.toString(), "Id");
        }

        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryReqDTO categoryReqDTO, Long noteId) {
        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty()) {
            throw new EntityNotFoundException(EntityName.NOTE.toString(), "Id");
        }

        Category baseCategory;
        categoryReqDTO.setNote(optNote.get());
        categoryReqDTO.setCreatedAt(Instant.now());
        categoryReqDTO.setUpdatedAt(Instant.now());
        try {
            baseCategory = categoryRepository.save(categoryMapper.getCategory(categoryReqDTO));
        } catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }
        return categoryMapper.getResponseDTO(baseCategory);
    }
}
