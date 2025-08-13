package com.hackathon.backend.users.internal;

import com.hackathon.backend.users.User;
import com.hackathon.backend.users.UserService;
import com.hackathon.backend.users.dto.RegisterRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class UserInternalController {

    private final UserService userService;

    public UserInternalController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    User save(@RequestBody RegisterRequest user) {
        return userService.register(user);
    }

    @GetMapping("/admin/users")
    List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/admin/user/{id}")
    User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
