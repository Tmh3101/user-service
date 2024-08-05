package com.Tmh3101.user_manager.test;

import com.Tmh3101.user_manager.entity.User;

import java.sql.Date;

public class UserTest {
    public static void main(String[] args) {
        User user = User.builder()
                .id("123456")
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(Date.valueOf("2004-01-31"))
                .phoneNumber("+1234567890")
                .address("123 Main St")
                .email("johndoe@example.com")
                .password("password123")
                .build();

        System.out.println(user.getDateOfBirth());
    }
}
