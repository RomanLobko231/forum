package com.roman.forum.repository;

import com.roman.forum.model.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<ForumUser, UUID> {
    Optional<ForumUser> findByUsername(String username);

    Optional<ForumUser> findByEmail(String email);

    boolean existsUserByUsernameOrEmail(String username, String email);
}
