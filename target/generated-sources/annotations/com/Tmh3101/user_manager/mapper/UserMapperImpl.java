package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
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
    public User toUser(UserCreationRequest userCreationRequest) {
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
    public void updateUser(User user, UserCreationRequest userCreationRequest) {
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
        Set<String> set = user.getRoles();
        if ( set != null ) {
            userResponse.roles( new LinkedHashSet<String>( set ) );
        }

        return userResponse.build();
    }
}
