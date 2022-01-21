package com.login.login_test.controller.v1;

import com.login.login_test.advice.exception.CustomEmailSignInFailedException;
import com.login.login_test.config.security.JwtTokenProvider;
import com.login.login_test.domain.User;
import com.login.login_test.dto.request.JoinRequestDto;
import com.login.login_test.model.response.CommonResult;
import com.login.login_test.model.response.SingleResult;
import com.login.login_test.repository.UserJpaRepository;
import com.login.login_test.service.ResponseService;
import com.login.login_test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Api(tags = { "1. Sign" })
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        User user = userJpaRepository.findByuId(id).orElseThrow(CustomEmailSignInFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CustomEmailSignInFailedException();

        return responseService
                .getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name) {

        return responseService.getSingleResult(userService.save(id, password, name));
        /*
        userJpaRepository.save(User.builder().uId(id).password(passwordEncoder.encode(password)).name(name)
                .roles(Collections.singletonList("ROLE_USER")).build());
        return responseService.getSuccessResult();

         */
    }
    @ApiOperation(value = "가입", notes = "바디를 이용한 회원가입을 한다.")
    @PostMapping(value = "/signupBody")
    public CommonResult signinBody(@ApiParam(value = "requestBody 객체", required = true) @RequestBody JoinRequestDto joinRequestDto) {

        return responseService.getSingleResult(userService.saveBody(joinRequestDto));

    }
    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signupLeader")
    public CommonResult signinLeader(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name) {

        return responseService.getSingleResult(userService.saveLeader(id, password, name));

    }
}