package com.seb42.main30.seb42_main_030.user.mapper;

import com.seb42.main30.seb42_main_030.user.dto.UserDto;
import com.seb42.main30.seb42_main_030.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-20T01:55:19+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostToUser(UserDto.Post post) {
        if ( post == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( post.getEmail() );
        user.setNickname( post.getNickname() );
        user.setPassword( post.getPassword() );
        user.setImageUrl( post.getImageUrl() );

        return user;
    }

    @Override
    public User userPatchToUser(UserDto.Patch patch) {
        if ( patch == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( patch.getUserId() );
        user.setNickname( patch.getNickname() );
        user.setPassword( patch.getPassword() );
        user.setImageUrl( patch.getImageUrl() );

        return user;
    }

    @Override
    public UserDto.Response userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        long userId = 0L;
        String nickname = null;
        String email = null;
        String password = null;
        String imageUrl = null;

        userId = user.getUserId();
        nickname = user.getNickname();
        email = user.getEmail();
        password = user.getPassword();
        imageUrl = user.getImageUrl();

        UserDto.Response response = new UserDto.Response( userId, nickname, email, password, imageUrl );

        return response;
    }

    @Override
    public List<UserDto.Response> usersToUserResponses(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto.Response> list = new ArrayList<UserDto.Response>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponse( user ) );
        }

        return list;
    }
}
