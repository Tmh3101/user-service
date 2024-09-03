package com.Tmh3101.user_manager.repo;

import com.Tmh3101.user_manager.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepo extends JpaRepository<InvalidatedToken, String> {
}
