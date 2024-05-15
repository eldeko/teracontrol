package com.teracontrol.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teracontrol.dto.UserDto;
import com.teracontrol.models.User;
import com.teracontrol.svc.UserService;

import java.util.List;

@ComponentScan(basePackages = "com.teracontrol.svc")

@RestController
public class UsersController {

    @Autowired
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }   

    @PostMapping("/api/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto user) {

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setFirstDay(user.getFirstDay());
        newUser.setTitle(user.getTitle());   
        newUser.setSeniority(user.getSeniority());
        newUser.setRegion(user.getRegion());
        newUser.setComputerModel(user.getComputerModel());       

        return userService.createUser(newUser);
    }

}
