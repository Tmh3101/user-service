package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.UserRequest;
import com.Tmh3101.user_manager.dto.response.PermissionResponse;
import com.Tmh3101.user_manager.dto.response.RoleResponse;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.Permission;
import com.Tmh3101.user_manager.entity.Role;
import com.Tmh3101.user_manager.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest userCreationRequest) {
        if ( userCreationRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userCreationRequest.getEmail() );
        user.firstName( userCreationRequest.getFirstName() );
        user.lastName( userCreationRequest.getLastName() );
        user.dateOfBirth( userCreationRequest.getDateOfBirth() );
        user.phoneNumber( userCreationRequest.getPhoneNumber() );
        user.address( userCreationRequest.getAddress() );
        user.password( userCreationRequest.getPassword() );

        return user.build();
    }

    @Override
    public void updateUser(User user, UserRequest userCreationRequest) {
        if ( userCreationRequest == null ) {
            return;
        }

        user.setEmail( userCreationRequest.getEmail() );
        user.setFirstName( userCreationRequest.getFirstName() );
        user.setLastName( userCreationRequest.getLastName() );
        user.setDateOfBirth( userCreationRequest.getDateOfBirth() );
        user.setPhoneNumber( userCreationRequest.getPhoneNumber() );
        user.setAddress( userCreationRequest.getAddress() );
        user.setPassword( userCreationRequest.getPassword() );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.email( user.getEmail() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dateOfBirth( user.getDateOfBirth() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.address( user.getAddress() );
        userResponse.roles( roleSetToRoleResponseSet( user.getRoles() ) );

        return userResponse.build();
    }

    protected PermissionResponse permissionToPermissionResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionResponse.PermissionResponseBuilder permissionResponse = PermissionResponse.builder();

        permissionResponse.name( permission.getName() );
        permissionResponse.description( permission.getDescription() );

        return permissionResponse.build();
    }

    protected Set<PermissionResponse> permissionSetToPermissionResponseSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionResponse> set1 = new LinkedHashSet<PermissionResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Permission permission : set ) {
            set1.add( permissionToPermissionResponse( permission ) );
        }

        return set1;
    }

    protected RoleResponse roleToRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setName( role.getName() );
        roleResponse.setDescription( role.getDescription() );
        roleResponse.setPermissions( permissionSetToPermissionResponseSet( role.getPermissions() ) );

        return roleResponse;
    }

    protected Set<RoleResponse> roleSetToRoleResponseSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleResponse( role ) );
        }

        return set1;
    }
}
