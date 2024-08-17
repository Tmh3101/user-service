package com.Tmh3101.user_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    EMAIL_EXISTED(1001, "Email existed", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTED(1002, "Phone number existed", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_NOT_EMPTY(1003, "Email must be not empty", HttpStatus.BAD_REQUEST),
    EMAIL_VALID(1004, "Email invalid", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_NOT_EMPTY(1005, "Phone number must be not empty", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_VALID(1006, "Phone number must have 10 number", HttpStatus.BAD_REQUEST),
    PASSWORD_VALID(1007, "Password must be at least 8 character", HttpStatus.BAD_REQUEST),
    NOT_FOUND_ANY_USERS(1008, "Not found any users", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1009, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1010, "You do not permission", HttpStatus.FORBIDDEN)
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
