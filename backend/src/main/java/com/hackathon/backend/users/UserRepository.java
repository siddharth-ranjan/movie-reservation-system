package com.hackathon.backend.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

/**
 * The public contract for accessing user data.
 * <p>
 * This interface defines the data operations for the User aggregate
 * that are exposed to other modules in the application. It hides
 * the underlying persistence technology.
 */

    /**
     * Saves a given user.
     * @param user The user entity to save.
     * @return The saved user entity.
     */
    User save(User user);

    /**
     * Finds a user by their unique email address.
     * @param email The email to search for.
     * @return An Optional containing the user if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their unique ID.
     * @param id The ID of the user.
     * @return An Optional containing the user if found, otherwise empty.
     */
    Optional<User> findById(Long id);

    /**
     * Finds all users.
     * @return All saved user entity.
     */
    List<User> findAll();

    boolean existsByEmail(String email);
}