package com.library.libraryapi.service.impl;

import com.library.libraryapi.exception.BusinessException;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.repository.BookRepository;
import com.library.libraryapi.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {

        if(repository.existsByIsbn(book.getIsbn())){
            throw new BusinessException("Isbn ja cadastrado.");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }
}
