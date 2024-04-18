package org.ensolversapp.backend.data.dto.category;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ensolversapp.backend.data.model.note.Note;

import java.time.Instant;

@Builder
@Getter
@Setter
public class CategoryReqDTO {
    @NotNull private String name;
    @Nullable private Note note;
    @Nullable private Instant createdAt;
    @Nullable private Instant updatedAt;
}
