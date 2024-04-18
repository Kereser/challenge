package org.ensolversapp.backend.data.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ensolversapp.backend.data.model.AbstractDTO;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO extends AbstractDTO {
    @NotNull private String name;
}
