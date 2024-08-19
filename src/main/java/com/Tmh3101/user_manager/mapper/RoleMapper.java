package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.RoleRequest;
import com.Tmh3101.user_manager.dto.response.RoleResponse;
import com.Tmh3101.user_manager.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
