package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.PermissionRequest;
import com.Tmh3101.user_manager.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    PermissionResponse delete(String name);
}
