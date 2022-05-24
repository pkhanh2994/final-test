package com.finaltest.app.controler;

import com.finaltest.app.dto.UserDto;
import com.finaltest.app.exception.UserServiceException;
import com.finaltest.app.model.request.UserDetailsRequestModel;
import com.finaltest.app.model.response.ErrorMessages;
import com.finaltest.app.model.response.UserRest;
import com.finaltest.app.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
        UserRest rest = new UserRest();
        UserDto userDto = new UserDto();
        //copy user from request to dto
        if(userDetails.getEmail().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessages());

        BeanUtils.copyProperties(userDetails,userDto);

        UserDto createUser = userService.createUser(userDto);
        //copy user from create user to response user
        BeanUtils.copyProperties(createUser,rest);

        return rest;
    }

    @GetMapping("/{userId}")
    public UserRest getUser(@PathVariable String userId){
         UserRest userRest = new UserRest();
         UserDto userDto = userService.getUserByUserId(userId);
         BeanUtils.copyProperties(userDto,userRest);

        return userRest;
    }
}
