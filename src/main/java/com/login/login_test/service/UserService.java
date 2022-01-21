package com.login.login_test.service;

import com.login.login_test.advice.exception.CustomUserNotFoundException;
import com.login.login_test.domain.Role;
import com.login.login_test.domain.User;
import com.login.login_test.dto.request.JoinRequestDto;
import com.login.login_test.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;


    //회원 모두 조회
    @Transactional(readOnly = true)
    public List findAll() {
        return userJpaRepository.findAll();
    }
    //회원 단건 조회
    @Transactional(readOnly = true)
    public User findUserById(long msrl){
        return userJpaRepository.findById(msrl).orElseThrow(CustomUserNotFoundException::new);
    }
    //회원가입_유저
    @Transactional
    public User save(String uid,String password, String name) {
        User user = User.builder().uId(uid).password(passwordEncoder.encode(password)).name(name).role(Role.ROLE_USER)
                .roles(Collections.singletonList("ROLE_USER")).build();
        return userJpaRepository.save(user);
    }

    //회원가입_body
    @Transactional
    public User saveBody(JoinRequestDto joinRequestDto) {
        User user = User.builder().uId(joinRequestDto.getId()).password(passwordEncoder.encode(joinRequestDto.getPassword())).name(joinRequestDto.getName()).role(Role.ROLE_USER)
                .roles(Collections.singletonList("ROLE_USER")).build();
        return userJpaRepository.save(user);
    }
    //회원가입_리더
    @Transactional
    public User saveLeader(String uid,String password, String name) {
        User user = User.builder().uId(uid).password(passwordEncoder.encode(password)).name(name).role(Role.ROLE_LEADER)
                .roles(Collections.singletonList("ROLE_LEADER")).build();
        return userJpaRepository.save(user);
    }
    //회원 수정
    @Transactional
    public User modify(long msrl, String name) {
        User user = User.builder().msrl(msrl).name(name).build();
        return userJpaRepository.save(user);
    }
    //회원 삭제
    @Transactional
    public void delete(long msrl) {
        userJpaRepository.deleteById(msrl);
    }


}
