package com.login.login_test.domain;

import lombok.Getter;

public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_LEADER("리더"),
    ROLE_USER("유저");

    @Getter
    private String value;

    @Getter
    private String key;

    Role(String value){
        this.value = value;
    }
}
