package br.com.luizvictor.springemail.entities.user;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link User}
 */
public record UserDetailsDto(Long id, String name, String email, boolean enabled,
                             LocalDateTime createdAt) implements Serializable {
    public UserDetailsDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isEnabled(),
                user.getCreatedAt()
        );
    }
}