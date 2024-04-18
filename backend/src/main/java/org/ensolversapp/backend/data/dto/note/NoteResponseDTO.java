package org.ensolversapp.backend.data.dto.note;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ensolversapp.backend.data.model.AbstractDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class NoteResponseDTO extends AbstractDTO {
    @NotNull private String title;
    @NotNull private String content;
    @NotNull private Boolean archived;
    @Nullable
    private List<CategoryResponseDTO> categories;

}
