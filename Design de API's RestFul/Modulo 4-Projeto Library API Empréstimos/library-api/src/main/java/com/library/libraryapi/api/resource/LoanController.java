package com.library.libraryapi.api.resource;

import com.library.libraryapi.api.dto.LoanDto;
import com.library.libraryapi.api.dto.LoanFilterDTO;
import com.library.libraryapi.api.dto.ReturnedLoanDTO;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.entity.Loan;
import com.library.libraryapi.service.BookService;
import com.library.libraryapi.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody LoanDto dto){

        Book book = bookService
                .getBookByIsbn(dto.getIsbn())
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,"Book not found for passed isbn"));

        Loan entity = Loan.builder()
                .book(book)
                .customer(dto.getCustomer())
                .loanDate(LocalDate.now())
                .build();

        entity = service.save(entity);
        return entity.getId();

    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook (
            @PathVariable Long id,
            @RequestBody ReturnedLoanDTO dto){
        Loan loan = service.getById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        loan.setReturned(dto.getReturned());

        service.update(loan);
    }

    @GetMapping
    public Page<LoanDto> find (LoanFilterDTO dto, Pageable pageable){
        Page<Loan> result = service.find(dto, pageable);
        return null;
    }
}
