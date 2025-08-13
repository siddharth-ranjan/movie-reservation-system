package com.hackathon.backend.users.dto;

import java.time.LocalDate;

public record RegisterRequest(String username, String password, String email, LocalDate birthdate) {}
