
package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.controller.BookController;

@DisplayName("Запуск теста Security")
@ExtendWith({SpringExtension.class})
@WebMvcTest({BookController.class})
@SpringBootTest
class BooksMongoDaoTest {
    @Autowired
    private MockMvc mockMvc;

    BooksMongoDaoTest() {
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void testBookDeleteAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bookDelete", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBookEditAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/edit", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBookInsertAuthenticatedOnAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/insert", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
