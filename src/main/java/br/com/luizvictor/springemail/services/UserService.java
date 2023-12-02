package br.com.luizvictor.springemail.services;

import br.com.luizvictor.springemail.entities.user.User;
import br.com.luizvictor.springemail.entities.user.UserDetailsDto;
import br.com.luizvictor.springemail.entities.user.UserDto;
import br.com.luizvictor.springemail.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsDto save(UserDto dto) {
        User user = new User(dto);
        return new UserDetailsDto(userRepository.save(user));
    }

    public UserDetailsDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsDto(user);
    }
}
