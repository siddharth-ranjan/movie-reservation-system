package com.hackathon.backend.users.internal;

import com.hackathon.backend.users.User;
import com.hackathon.backend.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserInternalController {

    @Autowired
    private final UserService userService;

    public UserInternalController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable String id) {
        return userService.findUser();
    }



}
