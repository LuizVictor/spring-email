package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDetailsDto;
import br.com.luizvictor.springemail.entities.user.UserDto;
import br.com.luizvictor.springemail.entities.userVerification.UserVerification;
import br.com.luizvictor.springemail.repositories.UserRepository;
import br.com.luizvictor.springemail.repositories.UserVerificationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserVerificationRepository userVerificationRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, UserVerificationRepository userVerificationRepository1, EmailService emailService) {
        this.userRepository = userRepository;
        this.userVerificationRepository = userVerificationRepository1;
        this.emailService = emailService;
    }

    public UserDetailsDto save(UserDto dto) {
        User user = userRepository.save(new User(dto));
        UserVerification userVerification = new UserVerification(user);
        userVerificationRepository.save(userVerification);
        emailService.sendVerificationEmail(user.getEmail(), userVerification.getToken());

        return new UserDetailsDto(user);
    }

    public UserDetailsDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsDto(user);
    }

    public void verifyToken(String token) {
        UserVerification userVerification = userVerificationRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
        userVerification.verify();
        userVerificationRepository.save(userVerification);
        User user = userRepository.getReferenceById(userVerification.getUser().getId());
        user.enable();
        userRepository.save(user);
    }
}
