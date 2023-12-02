package br.com.luizvictor.springemail.entities.users;

import br.com.luizvictor.springemail.entities.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static br.com.luizvictor.springemail.common.UserConstant.VALID_USER_DTO;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;
    @BeforeAll
    static void beforeAll() {
        user = new User(VALID_USER_DTO);
    }

    @Test
    void mustCreateUser() {
        assertEquals("John Doe", user.getName());
        assertEquals("john@email.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertFalse(user.isEnabled());
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void mustEnableUser() {
        user.enable();
        assertTrue(user.isEnabled());
    }
}