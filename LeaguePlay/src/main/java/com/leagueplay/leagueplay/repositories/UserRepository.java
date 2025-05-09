package com.leagueplay.leagueplay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leagueplay.leagueplay.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail (String email);
}
