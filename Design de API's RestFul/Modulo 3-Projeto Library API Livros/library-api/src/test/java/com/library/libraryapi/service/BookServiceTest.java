package com.library.libraryapi.service;

import com.library.libraryapi.exception.BusinessException;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.model.repository.BookRepository;
import com.library.libraryapi.service.impl.BookServiceImpl;
import net.minidev.json.JSONUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest() {
        //cenario
        Book book = createValidBook();
        Mockito.when(repository.existsByIsbn(Mockito.anyString())).thenReturn(false);
        Mockito.when(repository.save(book)).thenReturn(
                Book.builder().id(1l)
                        .isbn("123")
                        .author("Jessi")
                        .title("As aventuras").build());

        //execulcao
        Book saveBook = service.save(book);

        //verificacao
        assertThat(saveBook.getId()).isNotNull();
        assertThat(saveBook.getIsbn()).isEqualTo("123");
        assertThat(saveBook.getTitle()).isEqualTo("As aventuras");
        assertThat(saveBook.getAuthor()).isEqualTo("Jessi");

    }

    private Book createValidBook() {
        return Book.builder().isbn("123").author("Jessi").title("As aventuras").build();
    }

    @Test
    @DisplayName("Deve lamcar um erro de negocio ao tentar salvar um livro com o isbn duplicado")
    public void shouldNotSaveABookWithDuplicatedISBN() {
        //cenario
        Book book = createValidBook();
        Mockito.when(repository.existsByIsbn(Mockito.anyString())).thenReturn(true);

        //execucao
        Throwable exception = Assertions.catchThrowable(() -> service.save(book));

        //verificacao
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Isbn ja cadastrado.");

        Mockito.verify(repository, Mockito.never()).save(book);

    }

    @Test
    @DisplayName("Deve obter um livro por id")
    public void getByIdTest(){
        //cenario
        Long id = 1l;
        Book book = createValidBook();
        book.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(book));

        //execucao
        Optional<Book> foundBook = service.getById(id);

        //verificacoes
        assertThat( foundBook.isPresent()).isTrue();
        assertThat( foundBook.get().getId() ).isEqualTo(id);
        assertThat( foundBook.get().getAuthor() ).isEqualTo(book.getAuthor());
        assertThat( foundBook.get().getIsbn() ).isEqualTo(book.getIsbn());
        assertThat( foundBook.get().getTitle() ).isEqualTo(book.getTitle());
    }

    @Test
    @DisplayName("Deve retornar vazio ao obter um livro por id quando ele nao existe na base.")
    public void bookNotFoundByIdTest(){
        //cenario
        Long id = 1l;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        //execucao
        Optional<Book> book = service.getById(id);

        //verificacoes
        assertThat( book.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Deve deletar um livro.")
    public void deleteBookTeste(){
        Book book = Book.builder().id(1l).build();

        //execucao
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> service.delete(book));

        //verificacao
        Mockito.verify(repository, Mockito.times(1)).delete(book);
    }


    @Test
    @DisplayName("Deve ocorre um erro ao tentar deletar um livro inexistente.")
    public void deleteInvalidBookTeste(){
        Book book = new Book();

        //execucao
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, ()-> service.delete(book));

        //verificacao
        Mockito.verify(repository,Mockito.never()).delete(book);
    }


    @Test
    @DisplayName("Deve ocorre um erro ao tentar atualizar um livro inexistente.")
    public void updateInvalidBookTeste(){
        Book book = new Book();

        //execucao
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, ()-> service.update(book));

        //verificacao
        Mockito.verify(repository,Mockito.never()).save(book);
    }


    @Test
    @DisplayName("Deve atualizar um livro.")
    public void updateBookTeste(){
        //cenario
        Long id = 1l;

        //livro a atualizar
        Book updatingBook = Book.builder().id(id).build();

        //simulacao
        Book updateBook = createValidBook();
        updateBook.setId(id);

        Mockito.when(repository.save(updatingBook)).thenReturn(updateBook);

        //execucao
        Book book = service.update(updatingBook);

        //verificacoes
        assertThat(book.getId()).isEqualTo(updateBook.getId());
        assertThat(book.getTitle()).isEqualTo(updateBook.getTitle());
        assertThat(book.getIsbn()).isEqualTo(updateBook.getIsbn());
        assertThat(book.getAuthor()).isEqualTo(updateBook.getAuthor());

    }

    @Test
    @DisplayName("Deve filtrar livros pelas propriedades.")
    public void findBookTest(){
        //cenario
        Book book = createValidBook();

        PageRequest pageRequest = PageRequest.of(0,10);
        List<Book> lista = Arrays.asList(book);
        Page<Book> page = new PageImpl<Book>(lista,pageRequest ,1);

        Mockito.when(repository.findAll(Mockito.any(Example.class),Mockito.any(PageRequest.class)))
                .thenReturn(page);

        //execucao
        Page<Book> result = service.find(book, pageRequest);

        //verificacao
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(lista);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }
}