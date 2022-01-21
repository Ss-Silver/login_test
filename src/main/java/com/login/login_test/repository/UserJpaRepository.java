package com.login.login_test.repository;

import com.login.login_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByuId(String email);
}
