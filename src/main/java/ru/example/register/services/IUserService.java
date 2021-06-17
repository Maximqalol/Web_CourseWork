package ru.example.register.services;

import ru.example.register.db.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Maxim Komov
 * Interface for user's service methods
 */

public interface IUserService {

    User create(User user);
    User change(User user);
    void delete(User user);
    User findByUsername(String username);
    User findById(int id);
    User getCurrentUser(HttpServletRequest httpServletRequest);

}
