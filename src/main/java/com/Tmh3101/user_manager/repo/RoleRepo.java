package com.Tmh3101.user_manager.repo;

import com.Tmh3101.user_manager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(String name);
}
