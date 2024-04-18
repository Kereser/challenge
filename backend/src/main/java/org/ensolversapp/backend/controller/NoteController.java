package org.ensolversapp.backend.controller;

import jakarta.validation.Valid;
import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.ensolversapp.backend.service.category.CategoryServiceImpl;
import org.ensolversapp.backend.service.note.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired private NoteServiceImpl noteServiceImpl;
    @Autowired private CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<NoteResponseDTO> getAllNotes() {
        return noteServiceImpl.getAllNotes();
    }

    @GetMapping(params = {"filter"})
    @ResponseStatus(HttpStatus.OK)
    List<NoteResponseDTO> getAllNotesFiltered(@RequestParam String filter) {
        return noteServiceImpl.getAllNotesFiltered(filter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NoteResponseDTO createNote(@RequestBody @Valid NoteReqDTO noteReqDTO) {
        return noteServiceImpl.createNote(noteReqDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteNote(@PathVariable Long id) {
        noteServiceImpl.deleteNote(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    NoteResponseDTO updateNote(@PathVariable Long id, @RequestBody @Valid NoteReqDTO noteReqDTO) {
        return noteServiceImpl.updateNote(id, noteReqDTO);
    }

    @PostMapping("/{noteId}/categories")
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponseDTO createCategory(@RequestBody @Valid CategoryReqDTO categoryReqDTO, @PathVariable Long noteId) {
        return categoryServiceImpl.createCategory(categoryReqDTO, noteId);
    }
}
