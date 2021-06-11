package ru.example.register.services;

import ru.example.register.db.entity.User;

/**
 * @author Maxim Komov
 * Interface for user's service methods
 */

public interface IUserService {

    User create(User user);
    User change(User user);
    void delete(User user);
    User findByUsername(String username);

}
