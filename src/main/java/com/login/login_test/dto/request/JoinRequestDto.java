package com.login.login_test.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequestDto {
    private String id;
    private String password;
    private String name;
}
