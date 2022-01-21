package com.login.login_test.advice;

import com.login.login_test.advice.exception.CustomEmailSignInFailedException;
import com.login.login_test.advice.exception.CustomUserNotFoundException;
import com.login.login_test.model.response.CommonResult;
import com.login.login_test.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;
    private final MessageSource messageSource;


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
// 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CustomUserNotFoundException e) {
// 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),
                getMessage("userNotFound.msg"));
    }

    protected CommonResult emailSignInFailed(HttpServletRequest request, CustomEmailSignInFailedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("emailSigninFailed.code")), getMessage("emailSigninFailed.msg"));
    }

    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
