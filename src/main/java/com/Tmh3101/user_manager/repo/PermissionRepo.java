package com.Tmh3101.user_manager.repo;

import com.Tmh3101.user_manager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepo extends JpaRepository<Permission, String> {
    Optional<Permission> findPermissionByName(String name);
}