package br.com.luizvictor.springemail.resources;

import br.com.luizvictor.springemail.repositories.UserRepository;
import br.com.luizvictor.springemail.repositories.UserVerificationRepository;
import br.com.luizvictor.springemail.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.luizvictor.springemail.common.UserConstant.VALID_USER_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserResourceTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void save_withValidData_mustReturnStatusCreated() throws Exception {
        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(VALID_USER_DTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@email.com"))
                .andExpect(jsonPath("$.enabled").isBoolean())
                .andExpect(jsonPath("$.enabled").value(false));

        assertEquals(1, userRepository.count());
        assertEquals(1, userVerificationRepository.count());
    }

    @Test
    void findById_withExistingId_mustReturnStatusOk() throws Exception {
        var user = userService.save(VALID_USER_DTO);
        mvc.perform(get("/api/users/{id}", user.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.id()))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@email.com"));
    }

    @Test
    void validateToken_mustReturnStatusOk() throws Exception {
        userService.save(VALID_USER_DTO);
        var verification = userVerificationRepository.getReferenceById(1L);

        mvc.perform(get("/api/users/validate")
                        .param("token", verification.getToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Validated user"));

        assertEquals(1, userRepository.count());
    }

    @Test
    void invalideToken_mustReturnThrowException() throws Exception {
        userService.save(VALID_USER_DTO);

        mvc.perform(get("/api/users/validate")
                        .param("token", "invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}