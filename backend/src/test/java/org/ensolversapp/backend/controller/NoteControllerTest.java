package org.ensolversapp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.model.note.mapper.NoteMapper;
import org.ensolversapp.backend.data.repository.CategoryRepository;
import org.ensolversapp.backend.service.category.CategoryServiceImpl;
import org.ensolversapp.backend.service.note.NoteService;
import org.ensolversapp.backend.service.note.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = {NoteMapper.class, NoteController.class})
public class NoteControllerTest {

    @MockBean NoteServiceImpl noteServiceImpl;
    @MockBean CategoryServiceImpl categoryServiceImpl;
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    private NoteResponseDTO noteResponseDTO;
    private NoteReqDTO noteReqDTO;
    private CategoryReqDTO categoryReqDTO;
    private CategoryResponseDTO categoryResponseDTO;

    @BeforeEach
    void init() {
        noteReqDTO = NoteReqDTO.builder()
                .title("First note")
                .content("Note about the first time a note was created")
                .archived(true)
                .build();

        noteResponseDTO = NoteResponseDTO.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .title("First note")
                .content("Note about the first time a note was created")
                .archived(true)
                .build();

        categoryReqDTO = CategoryReqDTO.builder()
                .name("Family")
                .note(null)
                .build();

        categoryResponseDTO = CategoryResponseDTO.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .name("Family")
                .build();
    }

    @Nested
    class getNotes {
        @Test
        void getAllNotes_GetNotes_WhenCall() throws Exception {
            when(noteServiceImpl.getAllNotes()).thenReturn(new ArrayList<>(Arrays.asList(noteResponseDTO)));

            mockMvc.perform(get("/notes"))
                    .andExpect(jsonPath("[0].title").value(noteResponseDTO.getTitle()))
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(status().isOk());

            verify(noteServiceImpl).getAllNotes();
            verifyNoMoreInteractions(noteServiceImpl);
        }

        @Test
        void getAllNotesFiltered_GetFilteredNotes_WhenFilterParam() throws Exception {
            when(noteServiceImpl.getAllNotesFiltered(anyString())).thenReturn(new ArrayList<>(Arrays.asList(noteResponseDTO)));

            mockMvc.perform(get("/notes").param("filter", "Work"))
                    .andExpect(jsonPath("[0].title").value(noteResponseDTO.getTitle()))
                    .andExpect(jsonPath("[0].content").value(noteResponseDTO.getContent()))
                    .andExpect(status().isOk());
        }
    }


    @Test
    void createNote_CreateOneNote_WhenValidInput() throws Exception {
        when(noteServiceImpl.createNote(Mockito.any(NoteReqDTO.class))).thenReturn(noteResponseDTO);

        mockMvc.perform(post("/notes").content(objectMapper.writeValueAsString(noteReqDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(noteResponseDTO.getTitle()))
                .andExpect(status().isCreated());

        verify(noteServiceImpl).createNote(Mockito.any(NoteReqDTO.class));
        verifyNoMoreInteractions(noteServiceImpl);
    }

    @Test
    // Puede validarse con el updateAt a ver si cambió
    void updateNote_UpdateOneNote_WhenValidInput() throws Exception {
        when(noteServiceImpl.updateNote(eq(1L), Mockito.any(NoteReqDTO.class))).thenReturn(noteResponseDTO);

        mockMvc.perform(put("/notes/1").content(objectMapper.writeValueAsString(noteReqDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(noteResponseDTO.getTitle()))
                .andExpect(jsonPath("$.content").value(noteResponseDTO.getContent()))
                .andExpect(status().isOk());

        verify(noteServiceImpl).updateNote(eq(1L), Mockito.any(NoteReqDTO.class));
        verifyNoMoreInteractions(noteServiceImpl);
    }

    @Test
    void deleteNote_DeleteOneNote_WhenValidInput() throws Exception {
        MvcResult res = mockMvc.perform(delete("/notes/1")).andReturn();

        assertThat(res.getResponse().getStatus(), is(204));
        verify(noteServiceImpl).deleteNote(Mockito.anyLong());
        verifyNoMoreInteractions(noteServiceImpl);
    }

    @Test
    void createCategory_CreateOneCategory_WhenValidInput() throws Exception {
        when(categoryServiceImpl.createCategory(Mockito.any(CategoryReqDTO.class), anyLong())).thenReturn(categoryResponseDTO);

        mockMvc.perform(post("/notes/1/categories").content(objectMapper.writeValueAsString(categoryReqDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(categoryReqDTO.getName()))
                .andExpect(status().isCreated());
    }
}
