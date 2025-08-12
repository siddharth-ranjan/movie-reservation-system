package com.hackathon.backend.users.internal;

import com.hackathon.backend.users.User;
import com.hackathon.backend.users.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {}