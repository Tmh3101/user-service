package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.PermissionRequest;
import com.Tmh3101.user_manager.dto.response.PermissionResponse;
import com.Tmh3101.user_manager.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
