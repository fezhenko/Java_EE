package org.example.socialnetwork.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.model.AppUser;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public List<UserDto> toDto(List<AppUser> appUsers) {
        if ( appUsers == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( appUsers.size() );
        for ( AppUser appUser : appUsers ) {
            list.add( toDto( appUser ) );
        }

        return list;
    }

    @Override
    public UserDto toDto(AppUser createdAppUser) {
        if ( createdAppUser == null ) {
            return null;
        }

        Long userId = null;
        String name = null;
        String role = null;
        Date createdAt = null;

        userId = createdAppUser.getUserId();
        name = createdAppUser.getName();
        role = createdAppUser.getRole();
        createdAt = createdAppUser.getCreatedAt();

        UserDto userDto = new UserDto( userId, name, role, createdAt );

        return userDto;
    }
}
