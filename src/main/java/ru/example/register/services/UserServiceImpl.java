package ru.example.register.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.register.db.entity.User;
import ru.example.register.db.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author Maxim Komov
 * Class overriding user's service methods
 */

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        //user.setPhoto();
        return userRepository.save(user);
    }

    @Override
    public User change(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        String username = new String(Base64.getDecoder().decode(authToken)).split(":", 2)[0];
        User foundedUsername = userRepository.findByUsername(username);
        return foundedUsername;
    }
}
