package com.hackathon.backend.users;

import jakarta.persistence.*;

@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String username;
//    private String password;
    private String email;
}
