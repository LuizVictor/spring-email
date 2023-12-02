package br.com.luizvictor.springemail.entities.userVerification;

import br.com.luizvictor.springemail.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users_verification")
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdAt;

    public UserVerification(User user) {
        this.user = user;
        this.status = Status.UNVERIFIED;
        this.token = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public void verify() {
        this.status = Status.VERIFIED;
    }
}
