package br.com.luizvictor.springemail.repositories;

import br.com.luizvictor.springemail.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
