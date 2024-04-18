package org.ensolversapp.backend.service.note;

import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService {
    List<NoteResponseDTO> getAllNotes();
    NoteResponseDTO createNote(NoteReqDTO noteReqDTO);
    void deleteNote(Long noteId);
    NoteResponseDTO updateNote(Long noteId, NoteReqDTO noteReqDTO);
    List<NoteResponseDTO> getAllNotesFiltered(String filter);
}
