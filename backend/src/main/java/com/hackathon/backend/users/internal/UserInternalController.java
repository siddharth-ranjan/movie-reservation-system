package com.hackathon.backend.users.internal;

import com.hackathon.backend.users.UserService;
import com.hackathon.backend.users.dto.LoginRequest;
import com.hackathon.backend.users.dto.RegisterRequest;
import com.hackathon.backend.users.internal.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserInternalController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserInternalController(UserService userService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register") // Changed from /create
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().
                    body("User already registered. Please login");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
        final String jwt = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
}