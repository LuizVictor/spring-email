package br.com.luizvictor.springemail.repositories;

import br.com.luizvictor.springemail.entities.userVerification.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByToken(String token);
}