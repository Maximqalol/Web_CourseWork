package ru.example.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.example.register.db.entity.User;
import ru.example.register.services.IUserService;
import ru.example.register.utils.ImageUtil;
import java.io.IOException;

/**
 * @author Maxim Komov
 * Controller who handles registration and authorization
 */

@Controller
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String authorizationPage(){
        return "login";
    }

    @GetMapping("/mainpage")
    public String mainPageView(Model model, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("user", user);
        model.addAttribute("date", new java.util.Date());
        user.setCount(user.getCount() + 1);
        userService.change(user);
        return "mainpage";
    }

    @GetMapping("/reggg")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "reggg";
    }

    @PostMapping("/register")
    public String userRegistration(@RequestParam("file") MultipartFile file,@RequestParam("username") String username, User user) throws IOException {
        User user1 = userService.findByUsername(user.getUsername());
        if (username.equals(user1))
        {
            return "error";
        }
        else {
            user.setPhoto(file.getBytes());
            userService.create(user);
            return "/test";
        }
    }

    @GetMapping("/editing")
    public String userEditProfile(Model model, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("last_name", user.getLast_name());
        model.addAttribute("first_name", user.getFirst_name());
        model.addAttribute("middle_name", user.getMiddle_name());
        model.addAttribute("username", user.getUsername());
        return "editing";
    }
    @PostMapping("/edit")
    public String editProfile(@RequestParam("file") MultipartFile file, User user, Authentication authentication) throws IOException {
        user.setId_user(userService.findByUsername(authentication.getName()).getId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setPhoto(file.getBytes());
        user.setCount(userService.findByUsername(authentication.getName()).getCount());
        userService.change(user);
        return "login";
    }

    @GetMapping("/delete")
    public String deleteUser(Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        userService.delete(user);
        return "login";
    }

}
