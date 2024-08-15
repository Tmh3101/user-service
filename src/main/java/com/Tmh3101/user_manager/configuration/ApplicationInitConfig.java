package com.Tmh3101.user_manager.configuration;

import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.enums.Role;
import com.Tmh3101.user_manager.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepo userRepo){
        return  args -> {
            if(userRepo.findUserByEmail("admin@gmail.com").isEmpty()){
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());

                User admin = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                userRepo.save(admin);
                log.warn("Admin user has been created with default password: 'admin', please change it");
            }
        };
    }
}
