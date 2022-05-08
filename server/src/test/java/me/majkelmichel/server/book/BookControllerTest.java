package me.majkelmichel.server.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.majkelmichel.server.exceptions.BookDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class BookControllerTest {

    @MockBean
    BookService bookService;
    BookDto BOOK_1 = new BookDto(1L, "Metro 2033", new Date(), 1L);
    BookDto BOOK_2 = new BookDto(2L, "Metro 2034", new Date(), 1L);
    BookDto BOOK_3 = new BookDto(3L, "Metro 2035", new Date(), 2L);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBooks() throws Exception {
        List<BookDto> books = new ArrayList<>(Arrays.asList(BOOK_1, BOOK_2, BOOK_3));

        Mockito.when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].title", is("Metro 2035")));
    }

    @Test
    void getBook() throws Exception {
        BookDto bookDto = new BookDto(1L, "Divergent", new Date(), 1L);

        Mockito.when(bookService.getBook(1L)).thenReturn(bookDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Divergent")));
    }

    @Test
    void getBook_withWrongId_throwsError() throws Exception {
        Mockito.when(bookService.getBook(4L)).thenThrow(BookDoesNotExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBook() throws Exception {
        BookCreationDto bookCreationDto = new BookCreationDto("Divergent", new Date(), 1L);

        BookDto bookDto = new BookDto(1L, bookCreationDto.getTitle(), bookCreationDto.getPublicationDate(), bookCreationDto.getAuthorId());

        Mockito.when(bookService.createBook(bookCreationDto)).thenReturn(bookDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookCreationDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Divergent")));
    }

    @Test
    void updateBook() throws Exception {
        BookCreationDto bookCreationDto = new BookCreationDto("Updated book", new Date(), 2L);

        BookDto bookDto = new BookDto(1L, bookCreationDto.getTitle(), bookCreationDto.getPublicationDate(), bookCreationDto.getAuthorId());

        Mockito.when(bookService.updateBook(2L, bookCreationDto)).thenReturn(bookDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/books/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(bookCreationDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Updated book")));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isNoContent());
    }
}