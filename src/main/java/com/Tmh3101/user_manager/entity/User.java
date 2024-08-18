package com.Tmh3101.user_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String email;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String phoneNumber;
    String address;
    String password;

    @ManyToMany
    Set<Role> roles;
}
