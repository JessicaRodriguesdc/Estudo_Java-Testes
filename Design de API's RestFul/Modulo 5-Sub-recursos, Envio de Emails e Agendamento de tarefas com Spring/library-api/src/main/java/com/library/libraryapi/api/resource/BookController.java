package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.BooKDTO;
import com.library.libraryapi.api.dto.LoanDto;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.entity.Loan;
import com.library.libraryapi.service.BookService;
import com.library.libraryapi.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final ModelMapper modelMapper;
    private final LoanService loanService;


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

    @GetMapping("{id}/loans")
    public Page<LoanDto> loansByBook(@PathVariable Long id, Pageable pageable){
        Book book = service.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Page<Loan> result = loanService.getLoansByBook(book, pageable);
        List<LoanDto> list = result.getContent()
                .stream()
                .map(loan -> {
                    Book loanBook = loan.getBook();
                    BooKDTO bookDTO = modelMapper.map(loanBook, BooKDTO.class);
                    LoanDto loanDto = modelMapper.map(loan, LoanDto.class);
                    loanDto.setBook(bookDTO);
                    return loanDto;
                }).collect(Collectors.toList());

        return new PageImpl<LoanDto>(list, pageable, result.getTotalElements());
    }
}
