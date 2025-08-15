package com.hackathon.backend.users;

import com.hackathon.backend.users.dto.RegisterRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User newUser = new User(
                user.username(),
                passwordEncoder().encode(user.password()),
                user.email(),
                user.birthdate()
        );
        return userRepository.save(newUser);
    }

    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public User update(User user) {
//        User toUpdate = userRepository.findById(user.getId()).orElse(null);
//
//        if(toUpdate == null){
//            return new User();
//        }
//        toUpdate.setEmail(user.getEmail());
//        toUpdate.setBirthDate(user.getBirthDate());
//        toUpdate.setUsername(user.getUsername());
//        return userRepository.save(toUpdate);
//    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
