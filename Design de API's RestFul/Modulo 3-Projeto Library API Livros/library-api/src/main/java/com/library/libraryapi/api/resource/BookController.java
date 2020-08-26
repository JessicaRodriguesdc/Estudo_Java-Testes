package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.BooKDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooKDTO create(@RequestBody BooKDTO dto){
//        BooKDTO dto = new BooKDTO();
//        dto.setAuthor("Autor");
//        dto.setTitle("Meu Livro");
//        dto.setIsbn("1213212");
//        dto.setId(1l);
        return dto;
    }
}
