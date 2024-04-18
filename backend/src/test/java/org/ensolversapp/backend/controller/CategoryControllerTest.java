package org.ensolversapp.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ensolversapp.backend.data.dto.category.CategoryReqDTO;
import org.ensolversapp.backend.data.dto.category.CategoryResponseDTO;
import org.ensolversapp.backend.data.model.category.mapper.CategoryMapper;
import org.ensolversapp.backend.data.model.note.Note;
import org.ensolversapp.backend.service.category.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = {CategoryMapper.class, CategoryController.class})
public class CategoryControllerTest {
    @MockBean private CategoryServiceImpl categoryServiceImpl;
    @Autowired MockMvc mockMvc;

    @Test
    void deleteCategory_DeleteOneCategory_WhenValidInput() throws Exception {
        MvcResult res = mockMvc.perform(delete("/categories/1")).andReturn();

        assertThat(res.getResponse().getStatus(), is(204));
        verify(categoryServiceImpl).deleteCategory(anyLong());
        verifyNoMoreInteractions(categoryServiceImpl);
    }

}
