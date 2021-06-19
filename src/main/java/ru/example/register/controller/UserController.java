package ru.example.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.example.register.db.dto.UserUpdate;
import ru.example.register.db.entity.Picture;
import ru.example.register.db.entity.User;
import ru.example.register.services.IPictureService;
import ru.example.register.services.IUserService;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Komov
 * Controller who handles registration and authorization
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final IUserService userService;
    private final IPictureService pictureService;

    @Autowired
    public UserController(IUserService userService, IPictureService pictureService) {
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @GetMapping("/test")
    public String userLogin(){
        return "Successful authentication!";
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userNow = userService.findByUsername(user.getUsername());
        userNow.setCount(userService.findByUsername(user.getUsername()).getCount() + 1);
        userService.change(userNow);
        boolean is_user = passwordEncoder.matches(user.getPassword(), userNow.getPassword());
        return is_user;
    }

    @RequestMapping("/user")
    public User getCurrentUserData(HttpServletRequest request) {
        return userService.getCurrentUser(request);
    }

    @PostMapping("/register")
    public User userRegistration(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PostMapping("/edit")
    public User editUser(@RequestBody UserUpdate userUpdate) {
        User user = userService.findByUsername(userUpdate.getName());
        user.setLast_name(userUpdate.getLast_name());
        user.setFirst_name(userUpdate.getFirst_name());
        user.setMiddle_name(userUpdate.getMiddle_name());
        user.setUsername(userUpdate.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userUpdate.getPassword());
        user.setPassword(encodedPassword);
        user.setPicture(pictureService.getPicture(userUpdate.getPicture_id()));
        //userNow.setPhoto(file.getBytes());
        userService.change(user);
        return user;
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody String username){
        User user = userService.findByUsername(username);
        //Picture picture = pictureService.getPicture(user.getPicture().getId());
        userService.delete(user);
    }

}
