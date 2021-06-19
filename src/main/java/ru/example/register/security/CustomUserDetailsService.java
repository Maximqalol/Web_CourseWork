package ru.example.register.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.example.register.db.entity.User;
import ru.example.register.db.repository.UserRepository;
import ru.example.register.security.CustomUserDetails;

/**
 * Реализация интерфейса UserDetailsService.
 *
 * @author Комов Максим
 */

public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Репозиторий для пользователя.
     */
    @Autowired
    UserRepository userRepository;


    /**
     * Поиск сущности пользователя на основе eго username.
     *
     * @param username имя пользователя
     * @return основная информация о пользователе
     * @throws UsernameNotFoundException исключение, если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}