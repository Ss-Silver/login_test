package com.login.login_test.service;

import com.login.login_test.advice.exception.CustomUserNotFoundException;
import com.login.login_test.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk){
        return userJpaRepository.findById(Long.valueOf(userPk)).orElseThrow(CustomUserNotFoundException::new);
    }
}
