package org.ensolversapp.backend.data.model.category;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ensolversapp.backend.data.model.AbstractEntity;
import org.ensolversapp.backend.data.model.note.Note;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Category extends AbstractEntity {
    @NotNull private String name;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "id", nullable = false)
    private Note note;
}
