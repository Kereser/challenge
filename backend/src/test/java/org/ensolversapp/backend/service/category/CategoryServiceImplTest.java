package org.ensolversapp.backend.service.category;

import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.model.category.Category;
import org.ensolversapp.backend.data.model.category.mapper.CategoryMapper;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.repository.CategoryRepository;
import org.ensolversapp.backend.data.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock private CategoryRepository categoryRepository;
    @Mock private CategoryMapper categoryMapper;
    @Mock private NoteRepository noteRepository;
    @InjectMocks private CategoryServiceImpl categoryServiceImpl;
    private CategoryResponseDTO categoryResponseDTO;
    private CategoryReqDTO categoryReqDTO;
    private Category category;
    private Note note;

    @BeforeEach
    void init() {
        note = Note.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .title("First note")
                .content("Note about the first time a note was created")
                .archived(true)
                .categories(null)
                .build();

        category = Category.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .name("First note to create")
                .note(note)
                .build();

        categoryReqDTO = CategoryReqDTO.builder()
                .note(null)
                .name("First note to create")
                .build();

        categoryResponseDTO = CategoryResponseDTO.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .name("First note to create")
                .build();
    }

    @Test
    void deleteCategory() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        categoryServiceImpl.deleteCategory(1L);
        verify(categoryRepository).findById(anyLong());
    }

    @Test
    void createCategory() {
        when(categoryMapper.getResponseDTO(Mockito.any(Category.class))).thenReturn(categoryResponseDTO);
        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));
        when(categoryRepository.save(any())).thenReturn(category);

        CategoryResponseDTO cResponseDTO = categoryServiceImpl.createCategory(categoryReqDTO, 1L);
        verify(categoryMapper).getResponseDTO(Mockito.any(Category.class));
        assertThat(cResponseDTO, samePropertyValuesAs(categoryResponseDTO));
    }
}