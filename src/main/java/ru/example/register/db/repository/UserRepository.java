package ru.example.register.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.register.db.entity.User;

/**
 * Репозиторий для пользователей.
 *
 * @author Комов Максим
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Поиск картинки по ее названию.
     *
     * @param username имя пользователя
     * @return найденное имя пользователя
     */
    User findByUsername(String username);

    /**
     * Поиск картинки по ее названию.
     *
     * @param id айди пользователя
     * @return найденное айди пользователя
     */
    User findById(int id);

}
