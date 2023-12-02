package br.com.luizvictor.springemail.entities.userVerification;

import org.junit.jupiter.api.Test;

import static br.com.luizvictor.springemail.common.UserConstant.VALID_USER;
import static org.junit.jupiter.api.Assertions.*;

class UserVerificationTest {
    @Test
    void mustCreateUserVerification() {
        var userVerification = new UserVerification(VALID_USER);

        assertEquals("John Doe", userVerification.getUser().getName());
        assertEquals("UNVERIFIED", userVerification.getStatus().name());
        assertNotNull(userVerification.getToken());
    }
}