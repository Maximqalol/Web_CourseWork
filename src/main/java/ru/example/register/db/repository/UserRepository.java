package ru.example.register.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.register.db.entity.User;

/**
 * @author Maxim Komov
 * Repository with method for searching users
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findById(int id);

}
