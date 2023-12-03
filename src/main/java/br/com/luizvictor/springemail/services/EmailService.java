package br.com.luizvictor.springemail.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendVerificationEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("noreply@email.com");
        message.setSubject("Account Verification");
        String URL = "http://localhost:8080/api/users/validate?token=";
        message.setText("Hello, please click on the link below to verify your account. \n\n" + URL + token);

        mailSender.send(message);
    }
}
