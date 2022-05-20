package com.finaltest.app.controler;

import com.finaltest.app.dto.UserDto;
import com.finaltest.app.model.request.UserDetailsRequestModel;
import com.finaltest.app.model.response.UserRest;
import com.finaltest.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest rest = new UserRest();
        UserDto userDto = new UserDto();
        //copy user from request to dto
        BeanUtils.copyProperties(userDetails,userDto);

        UserDto createUser = userService.createUser(userDto);
        //copy user from create user to response user
        BeanUtils.copyProperties(createUser,rest);

        return rest;
    }

    @GetMapping("/userId")
    public String getUser(){
        return "User details";
    }
}
