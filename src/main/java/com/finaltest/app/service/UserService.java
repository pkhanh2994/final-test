package com.finaltest.app.service;

import com.finaltest.app.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
      UserDto createUser(UserDto userDto);

      UserDto getUser(String email);

     UserDto getUserByUserId(String userId);
}
