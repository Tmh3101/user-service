package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.PermissionRequest;
import com.Tmh3101.user_manager.dto.response.PermissionResponse;
import com.Tmh3101.user_manager.service.Impl.PermissionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    PermissionServiceImpl permissionServiceImpl;

    @PostMapping("/add")
    public ApiResponse<PermissionResponse> addPermission(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionServiceImpl.create(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<PermissionResponse>> getAllPermissions(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionServiceImpl.getAll())
                .build();
    }

    @DeleteMapping("/delete/{permissionName}")
    public ApiResponse<PermissionResponse> deletePermission(@PathVariable String permissionName){
        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionServiceImpl.delete(permissionName))
                .build();
    }

}
