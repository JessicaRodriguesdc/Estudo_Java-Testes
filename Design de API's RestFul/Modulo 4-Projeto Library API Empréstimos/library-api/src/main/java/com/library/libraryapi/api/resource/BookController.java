package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.BooKDTO;
import com.library.libraryapi.api.exception.ApiErrors;
import com.library.libraryapi.exception.BusinessException;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service,ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooKDTO create(@RequestBody @Valid BooKDTO dto){
        Book entity = modelMapper.map(dto,Book.class);
        entity = service.save(entity);
        return modelMapper.map(entity,BooKDTO.class);
    }

    @GetMapping("{id}")
    public BooKDTO get(@PathVariable Long id){
        return service
                .getById(id)
                .map( book -> modelMapper.map(book,BooKDTO.class))
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Book book = service.getById(id).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.delete(book);
    }

    @PutMapping("{id}")
    public BooKDTO update( @PathVariable Long id, BooKDTO dto ){
       return service.getById(id).map( book -> {

            book.setAuthor(dto.getAuthor());
            book.setTitle(dto.getTitle());
            book = service.update(book);
            return modelMapper.map(book, BooKDTO.class);

        }).orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<BooKDTO> find(BooKDTO dto, Pageable pageableRequest){
        Book filter = modelMapper.map(dto,Book.class);
        Page<Book> result = service.find(filter, pageableRequest);
        List<BooKDTO> list = result.getContent().stream()
                        .map( entity -> modelMapper.map(entity,BooKDTO.class) )
                        .collect(Collectors.toList());

        return new PageImpl<BooKDTO>(list,pageableRequest,result.getTotalElements());
    }
}
