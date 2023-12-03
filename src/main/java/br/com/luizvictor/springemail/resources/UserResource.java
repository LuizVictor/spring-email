package br.com.luizvictor.springemail.resources;

import br.com.luizvictor.springemail.entities.user.UserDetailsDto;
import br.com.luizvictor.springemail.entities.user.UserDto;
import br.com.luizvictor.springemail.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> save(@RequestBody UserDto dto) {
        UserDetailsDto user = userService.save(dto);
        return ResponseEntity.created(URI.create("/api/users/" + user.id())).body(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDetailsDto> findById(@PathVariable Long id) {
        UserDetailsDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return ResponseEntity.ok().body("Validated user");
    }
}
