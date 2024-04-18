package org.ensolversapp.backend.service.note;

import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.model.note.mapper.NoteMapper;
import org.ensolversapp.backend.data.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @Mock private NoteRepository noteRepository;
    @Mock private NoteMapper noteMapper;
    @InjectMocks NoteServiceImpl noteServiceImpl;
    private NoteResponseDTO noteResponseDTO;
    private NoteReqDTO noteReqDTO;
    private Note note;

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
                .categories(null)
                .build();

        note = Note.builder()
                .id(1L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .title("First note")
                .content("Note about the first time a note was created")
                .archived(true)
                .categories(null)
                .build();
    }

    @Nested
    class GetNotes {
        @Test
        void getAllNotes_GetAll_WhenValid() {
            when(noteRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(note)));
            when(noteMapper.getResponseListDTO(Mockito.anyList())).thenReturn(new ArrayList<>(Arrays.asList(noteResponseDTO)));

            List<NoteResponseDTO> nResDTO = noteServiceImpl.getAllNotes();
            assertThat(nResDTO, hasSize(1));
            assertThat(nResDTO.get(0), samePropertyValuesAs(noteResponseDTO));
        }

        @Test
        void getAllNotesFiltered_GetFilteredNotes_WhenValidParam() {
            when(noteRepository.findAllByCategoriesName(anyString())).thenReturn(new ArrayList<>(Arrays.asList(note)));
            when(noteMapper.getResponseListDTO(Mockito.anyList())).thenReturn(new ArrayList<>(Arrays.asList(noteResponseDTO)));

            List<NoteResponseDTO> nResDTO = noteServiceImpl.getAllNotesFiltered("Any String");
            assertThat(nResDTO, hasSize(1));
            assertThat(nResDTO.get(0), samePropertyValuesAs(noteResponseDTO));
        }
    }


    @Test
    void createNote_CreateOneNote_WhenValidInput() {
        // There is no mock for other methods bc this is the last one to return the result and
        // the one that matters.
        when(noteRepository.save(Mockito.any())).thenReturn(note);
        when(noteMapper.getResponseDTO(Mockito.any(Note.class))).thenReturn(noteResponseDTO);

        NoteResponseDTO nResponseDTO = noteServiceImpl.createNote(noteReqDTO);
        assertThat(nResponseDTO, samePropertyValuesAs(noteResponseDTO));
    }

    @Test
    void deleteNote() {
        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));
        noteServiceImpl.deleteNote(1L);
        verify(noteRepository).findById(anyLong());
    }

    @Test
    void updateNote() {
        when(noteRepository.findById(anyLong())).thenReturn(Optional.of(note));
        when(noteMapper.getResponseDTO(Mockito.any(Note.class))).thenReturn(noteResponseDTO);
        when(noteMapper.updateNote(Mockito.any(NoteReqDTO.class), Mockito.any(Note.class))).thenReturn(note);

        NoteResponseDTO nResponseDTO = noteServiceImpl.updateNote(1L, noteReqDTO);
        assertThat(nResponseDTO, samePropertyValuesAs(noteResponseDTO));
        verify(noteRepository).findById(anyLong());
    }
}