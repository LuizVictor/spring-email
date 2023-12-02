package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDto;
import br.com.luizvictor.springemail.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void save_withValidUser_mustReturnUserDetailsDto() {
        var dto = new UserDto("John Doe", "john@email.com", "123456");
        var user = new User(dto);

        when(userRepository.save(any(User.class))).thenReturn(user);

        var details = userService.save(dto);

        assertEquals("John Doe", details.name());
        assertEquals("john@email.com", details.email());
        assertFalse(details.enabled());
        assertNotNull(details.createdAt());
    }
}