package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.BooKDTO;
import com.library.libraryapi.api.exception.ApiErrors;
import com.library.libraryapi.exception.BusinessException;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        Book book = service.getById(id).get();
        return modelMapper.map(book,BooKDTO.class);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusnessExceptions(BusinessException ex){
        return new ApiErrors(ex);
    }
}
