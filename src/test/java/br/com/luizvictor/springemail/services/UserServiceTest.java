package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.userVerification.UserVerification;
import br.com.luizvictor.springemail.repositories.UserRepository;
import br.com.luizvictor.springemail.repositories.UserVerificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    @Mock
    private EmailService emailService;
    @Mock
    private UserVerificationRepository userVerificationRepository;

    @Test
    void save_withValidUser_mustReturnUserDetailsDto() {
        when(userRepository.save(any(User.class))).thenReturn(VALID_USER);

        var details = userService.save(VALID_USER_DTO);

        verify(emailService).sendVerificationEmail(anyString(), anyString());
        assertEquals("John Doe", details.name());
        assertEquals("john@email.com", details.email());
        assertFalse(details.enabled());
        assertNotNull(details.createdAt());
    }

    @Test
    void validateToken_mustUpdateUserAndVerification() {
        String token = "8e50230e-91e1-11ee-b9d1-0242ac120002";
        var user = new User(VALID_USER_DTO);
        var userVerification = mock(UserVerification.class);

        when(userVerificationRepository.findByToken(token)).thenReturn(Optional.of(userVerification));
        when(userRepository.getReferenceById(any())).thenReturn(user);
        when(userVerification.getUser()).thenReturn(user);

        userService.validateToken(token);

        verify(userVerificationRepository).save(userVerification);
        verify(userRepository).save(user);

        assertTrue(user.isEnabled());
    }
}