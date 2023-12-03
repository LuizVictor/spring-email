package br.com.luizvictor.springemail.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;
    @Mock
    private JavaMailSender mailSender;

    @Test
    void sendVerificationEmail() {
        String to = "john@email.com";
        String token = "8e50230e-91e1-11ee-b9d1-0242ac120002";

        emailService.sendVerificationEmail(to, token);

        ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(message.capture());

        SimpleMailMessage sentMessage = message.getValue();

        assertEquals("Account Verification", sentMessage.getSubject());
        assertEquals("noreply@email.com", sentMessage.getFrom());
        assertEquals("john@email.com", Objects.requireNonNull(sentMessage.getTo())[0]);
        assertTrue(sentMessage.getText().contains("http://localhost:8080/api/users/validate?token=" + token));
    }
}