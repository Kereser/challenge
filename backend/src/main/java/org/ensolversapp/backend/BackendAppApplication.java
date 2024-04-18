package org.ensolversapp.backend;

import org.ensolversapp.backend.data.dto.note.NoteResponseDTO;
import org.ensolversapp.backend.data.model.note.mapper.NoteMapper;
import org.ensolversapp.backend.data.repository.CategoryRepository;
import org.ensolversapp.backend.data.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class BackendAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendAppApplication.class, args);
	}
}
