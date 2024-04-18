package org.ensolversapp.backend.data.dto.note;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class NoteReqDTO {
    @NotNull @Size(min=2) private String title;
    @Nullable @Size(min=2) private String content;
    @NotNull private Boolean archived;
    @Nullable private Instant createdAt;
    @Nullable private Instant updatedAt;
}
