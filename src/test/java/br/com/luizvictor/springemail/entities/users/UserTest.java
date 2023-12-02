package br.com.luizvictor.springemail.entities.users;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;
    @BeforeAll
    static void beforeAll() {
        UserDto dto = new UserDto("John Doe", "john@email.com", "123456");
        user = new User(dto);
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