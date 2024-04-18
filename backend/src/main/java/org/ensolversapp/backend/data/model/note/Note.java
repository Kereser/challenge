package org.ensolversapp.backend.data.model.note;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ensolversapp.backend.data.model.AbstractEntity;
import org.ensolversapp.backend.data.model.category.Category;

import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Note extends AbstractEntity {
    @NotNull private String title;
    @NotNull private String content;
    @NotNull private Boolean archived;

    @Nullable
    @OneToMany(mappedBy = "note", cascade = {CascadeType.REMOVE})
    private List<Category> categories;

}
