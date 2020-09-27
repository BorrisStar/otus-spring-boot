package spring.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import spring.rest.BookController;
import spring.security.repository.UserRepository;
import spring.service.AuthorDaoService;
import spring.service.BookDaoService;
import spring.service.CommentDaoService;
import spring.service.GenreDaoService;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan("spring")
@WebMvcTest(controllers = BookController.class)
@MockBeans({@MockBean(BookDaoService.class), @MockBean(AuthorDaoService.class), @MockBean(GenreDaoService.class),
                   @MockBean(CommentDaoService.class), @MockBean(UserRepository.class)})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStartPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin@mail.ru")
    @Test
    void shouldShowAllBooksGet() throws Exception {
        this.mockMvc.perform(get("/api/books/all")).andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/books", "/api/books/all", "/api/books/delete/1", "/api/books/edit/1", "/api/books/search/1"})
    void parameterizedNotAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(unauthenticated());
    }

    @WithMockUser(username = "admin@mail.ru")
    @ParameterizedTest
    @ValueSource(strings = {"/api/books", "/api/books/all", "/api/books/delete/1", "/api/books/edit/1", "/api/books/search/1"})
    void parameterizedAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(authenticated());
    }
}
