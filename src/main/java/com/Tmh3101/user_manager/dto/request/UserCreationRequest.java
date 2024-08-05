package com.Tmh3101.user_manager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotEmpty(message = "EMAIL_NOT_EMPTY")
    @Email(message = "EMAIL_VALID")
    String email;
    String firstName;
    String lastName;
    Date dateOfBirth;
    @NotEmpty(message = "PHONE_NUMBER_NOT_EMPTY")
    @Size(min = 10, max = 10, message = "PHONE_NUMBER_VALID")
    String phoneNumber;
    String address;
    @Size(min = 8, message = "PASSWORD_VALID")
    String password;
}
