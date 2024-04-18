package org.ensolversapp.backend.data.model.note.mapper;

import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.dto.note.NoteReqDTO;
import org.ensolversapp.backend.data.model.category.Category;
import org.ensolversapp.backend.data.model.category.mapper.CategoryMapper;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface NoteMapper {
    NoteResponseDTO getResponseDTO(Note note);
    List<NoteResponseDTO> getResponseListDTO(List<Note> notes);
    Note getNote(NoteReqDTO noteReqDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Note updateNote(NoteReqDTO noteReqDTO, @MappingTarget Note note);
}
