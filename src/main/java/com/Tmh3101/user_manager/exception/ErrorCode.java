package com.Tmh3101.user_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    EMAIL_EXISTED(1001, "Email existed"),
    PHONE_NUMBER_EXISTED(1002, "Phone number existed"),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error"),
    EMAIL_NOT_EMPTY(1003, "Email must be not empty"),
    EMAIL_VALID(1004, "Email invalid"),
    PHONE_NUMBER_NOT_EMPTY(1005, "Phone number must be not empty"),
    PHONE_NUMBER_VALID(1006, "Phone number must have 10 number"),
    PASSWORD_VALID(1007, "Password must be at least 8 character"),
    NOT_FOUND_ANY_USERS(1008, "Not found any users"),

    ;

    private int code;
    private String message;
}
