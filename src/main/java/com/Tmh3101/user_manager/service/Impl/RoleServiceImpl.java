package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.RoleRequest;
import com.Tmh3101.user_manager.dto.response.RoleResponse;
import com.Tmh3101.user_manager.entity.Permission;
import com.Tmh3101.user_manager.entity.Role;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.mapper.RoleMapper;
import com.Tmh3101.user_manager.repo.PermissionRepo;
import com.Tmh3101.user_manager.repo.RoleRepo;
import com.Tmh3101.user_manager.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    RoleRepo roleRepo;
    PermissionRepo permissionRepo;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        List<Permission> permissions = permissionRepo.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepo.save(role));
    }

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public RoleResponse getRoleByName(String name){
        return roleMapper.toRoleResponse(findRoleByName(name));
    }


    @Override
    public RoleResponse update(String name) {
        Role role = findRoleByName(name);
        return roleMapper.toRoleResponse(roleRepo.save(role));
    }

    @Override
    public RoleResponse delete(String name) {
        Role role = findRoleByName(name);
        roleRepo.deleteById(name);
        return roleMapper.toRoleResponse(role);
    }

    Role findRoleByName(String name){
        return roleRepo.findRoleByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ANY_ROLES));
    }
}
