package ru.netology.sql.data;

import lombok.Value;

@Value
public class AuthHelper {

    @Value
    public class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public class VerifyInfo {
        private String code;
    }

    public AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("petya", "qwerty098");
    }
}
