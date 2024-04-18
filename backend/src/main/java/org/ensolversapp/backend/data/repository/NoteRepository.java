package org.ensolversapp.backend.data.repository;

import org.ensolversapp.backend.data.model.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByCategoriesName(String categoryName);
}
