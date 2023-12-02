package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.luizvictor.springemail.common.UserConstant.VALID_USER;
import static br.com.luizvictor.springemail.common.UserConstant.VALID_USER_DTO;
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
        when(userRepository.save(any(User.class))).thenReturn(VALID_USER);

        var details = userService.save(VALID_USER_DTO);

        assertEquals("John Doe", details.name());
        assertEquals("john@email.com", details.email());
        assertFalse(details.enabled());
        assertNotNull(details.createdAt());
    }
}