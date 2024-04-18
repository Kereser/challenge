package org.ensolversapp.backend.service.note;

import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.ensolversapp.backend.data.model.EntityName;
import org.ensolversapp.backend.data.model.error.exception.EntityNotFoundException;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.model.note.mapper.NoteMapper;
import org.ensolversapp.backend.data.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<NoteResponseDTO> getAllNotes() {
        List<Note> noteList;

        try {
            noteList = noteRepository.findAll();
        } catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }

        return noteMapper.getResponseListDTO(noteList);
    }

    @Override
    public NoteResponseDTO createNote(NoteReqDTO noteReqDTO) {
        noteReqDTO.setCreatedAt(Instant.now());
        noteReqDTO.setUpdatedAt(Instant.now());

        Note newNote;
        try {
            newNote = noteRepository.save(noteMapper.getNote(noteReqDTO));
        } catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }

        return noteMapper.getResponseDTO(newNote);
    }

    @Override
    public void deleteNote(Long noteId) {
        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty())
            throw new EntityNotFoundException(EntityName.NOTE.toString(), "Id");

        try {
            noteRepository.deleteById(noteId);
        } catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }
    }

    @Override
    public NoteResponseDTO updateNote(Long noteId, NoteReqDTO noteReqDTO) {
        Optional<Note> optNote = noteRepository.findById(noteId);

        if (optNote.isEmpty())
            throw new EntityNotFoundException(EntityName.NOTE.toString(), "Id");

        noteReqDTO.setUpdatedAt(Instant.now());

        Note updatedNote = noteMapper.updateNote(noteReqDTO, optNote.get());
        noteRepository.save(updatedNote);
        return noteMapper.getResponseDTO(updatedNote);
    }

    @Override
    public List<NoteResponseDTO> getAllNotesFiltered(String filter) {
        List<Note> allFilteredNotes;

        try {
            allFilteredNotes = noteRepository.findAllByCategoriesName(filter);
        }catch (Exception e) {
            throw new ServerWebInputException(e.getMessage());
        }
        return noteMapper.getResponseListDTO(allFilteredNotes);
    }
}
