package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.RoleRequest;
import com.Tmh3101.user_manager.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    List<RoleResponse> getAll();
    RoleResponse update(String name);
    RoleResponse delete(String name);
}
