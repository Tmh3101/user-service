package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.PermissionRequest;
import com.Tmh3101.user_manager.dto.response.PermissionResponse;
import com.Tmh3101.user_manager.entity.Permission;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.mapper.PermissionMapper;
import com.Tmh3101.user_manager.repo.PermissionRepo;
import com.Tmh3101.user_manager.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    PermissionRepo permissionRepo;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepo.save(permission));
    }

    @Override
    public List<PermissionResponse> getAll() {
        return  permissionRepo.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    @Override
    public PermissionResponse delete(String name) {
        PermissionResponse permissionResponse = permissionMapper.toPermissionResponse(getPermissionByName(name));
        permissionRepo.deleteById(name);
        return permissionResponse;
    }

    private Permission getPermissionByName(String name){
        return permissionRepo.findPermissionByName(name)
                             .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ANY_PERMISSIONS));
    }
}
