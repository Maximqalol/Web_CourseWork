package ru.example.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.example.register.db.entity.User;
import ru.example.register.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Maxim Komov
 * Controller who handles registration and authorization
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String userLogin(){
        return "Successful authentication!";
    }

    @PostMapping("/register")
    public User userRegistration(@RequestParam("file") MultipartFile file, @RequestBody User user) throws IOException {
        user.setPhoto(file.getBytes());
        userService.create(user);
        return user;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userNow = userService.findByUsername(user.getUsername());
        boolean is_user = passwordEncoder.matches(user.getPassword(), userNow.getPassword());
        return is_user;
    }

    @RequestMapping("/user")
    public User getCurrentUserData(HttpServletRequest request) {
        return userService.getCurrentUser(request);
    }

    @PostMapping("/edit")
    public User editUser(@RequestParam("file") MultipartFile file, User user, Authentication authentication) throws IOException {
        user.setId_user(userService.findByUsername(authentication.getName()).getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setPhoto(file.getBytes());
        user.setCount(userService.findByUsername(authentication.getName()).getCount());
        userService.change(user);
        return user;
    }

    @GetMapping("/delete")
    public void deleteUser(Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        userService.delete(user);
    }

}
