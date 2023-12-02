package br.com.luizvictor.springemail.entities.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private LocalDateTime createdAt;

    public User(UserDto dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.enabled = false;
        this.createdAt = LocalDateTime.now();
    }

    public void enable() {
        this.enabled = true;
    }
}
