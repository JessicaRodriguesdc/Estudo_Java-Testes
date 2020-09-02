package com.library.libraryapi.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.libraryapi.api.dto.BooKDTO;
import com.library.libraryapi.exception.BusinessException;
import com.library.libraryapi.model.entity.Book;
import com.library.libraryapi.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mvc;

    //Criar uma instancia mocada
    @MockBean
    BookService service;

    //validacao de integridade
    @Test
    @DisplayName("Deve criar um livro com sucesso.")
    public void createBookTest() throws Exception{
        BooKDTO dto = createNewBook();
        Book saveBook = Book.builder().id(10l).author("Jessi").title("As aventuras").isbn("001").build();

        BDDMockito.given(service.save(Mockito.any(Book.class)))
                .willReturn(saveBook);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated() )
                .andExpect(jsonPath("id").isNotEmpty() )
                .andExpect(jsonPath("id").value(10l) )
                .andExpect(jsonPath("title").value(dto.getTitle()) )
                .andExpect(jsonPath("author").value(dto.getAuthor()) )
                .andExpect(jsonPath("isbn").value(dto.getIsbn()) )

        ;
    }

    @Test
    @DisplayName("Deve  lancar erro de validacao quando nao houver dados suficiente para criacao do livro.")
    public void createInvalidBookTest() throws Exception{
        String json = new ObjectMapper().writeValueAsString(new BooKDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest() )
                .andExpect(jsonPath("errors", hasSize(3)));
    }

    //validacao de regra de negocio
    @Test
    @DisplayName("Deve lancar um erro ao tentar cadastrar um livro com isbn ja utilizado.")
    public void createBookWithDuplicatedIsbn() throws Exception{
        BooKDTO dto = createNewBook();
        String json = new ObjectMapper().writeValueAsString(dto);
        String mensagemErro = "Isbn ja cadastrado.";
        BDDMockito.given(service.save(Mockito.any(Book.class)))
                .willThrow(new BusinessException(mensagemErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform( request )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors",hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(mensagemErro))
        ;

    }

    @Test
    @DisplayName("Deve obter informacoes de um livro")
    public void getBookDetailsTest() throws Exception{
        //cenario (given)
        Long id = 1l;

        Book book = Book.builder()
                    .id(id)
                    .title(createNewBook().getTitle())
                    .author(createNewBook().getAuthor())
                    .isbn(createNewBook().getIsbn())
                    .build();

        BDDMockito.given(service.getById(id)).willReturn(Optional.of(book));

        //execulcao (when)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id) )
                .andExpect(jsonPath("title").value(createNewBook().getTitle()) )
                .andExpect(jsonPath("author").value(createNewBook().getAuthor()) )
                .andExpect(jsonPath("isbn").value(createNewBook().getIsbn()) );

    }

    private BooKDTO createNewBook() {
        return BooKDTO.builder().author("Jessi").title("As aventuras").isbn("001").build();
    }
}
