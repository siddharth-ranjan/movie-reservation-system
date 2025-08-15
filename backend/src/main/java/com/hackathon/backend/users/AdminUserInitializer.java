package com.hackathon.backend.users;

import com.hackathon.backend.users.dto.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@movieapp.com");
            adminUser.setPassword(passwordEncoder.encode("pass"));
            adminUser.setRole(Role.ADMIN);
            userRepository.save(adminUser);
            System.out.println(">>>> Admin user created!");
        }
    }
}