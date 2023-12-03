package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDetailsDto;
import br.com.luizvictor.springemail.entities.user.UserDto;
import br.com.luizvictor.springemail.entities.userVerification.UserVerification;
import br.com.luizvictor.springemail.repositories.UserRepository;
import br.com.luizvictor.springemail.repositories.UserVerificationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserVerificationRepository userVerificationRepository;
    private final EmailService emailService;

    public UserService(
            UserRepository userRepository,
            UserVerificationRepository userVerificationRepository1,
            EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.userVerificationRepository = userVerificationRepository1;
        this.emailService = emailService;
    }

    @Transactional
    public UserDetailsDto save(UserDto dto) {
        User user = userRepository.save(new User(dto));
        String token = createUserVerification(user);
        emailService.sendVerificationEmail(user.getEmail(), token);

        return new UserDetailsDto(user);
    }

    public UserDetailsDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        return new UserDetailsDto(user);
    }

    @Transactional
    public void validateToken(String token) {
        UserVerification userVerification = userVerificationRepository.findByToken(token).orElseThrow(
                () -> new EntityNotFoundException("Token not found")
        );

        userVerification.verify();
        Long userID = userVerification.getUser().getId();
        User user = userRepository.getReferenceById(userID);
        user.enable();

        userVerificationRepository.save(userVerification);
        userRepository.save(user);
    }

    private String createUserVerification(User user) {
        UserVerification userVerification = new UserVerification(user);
        userVerificationRepository.save(userVerification);
        return userVerification.getToken();
    }
}
