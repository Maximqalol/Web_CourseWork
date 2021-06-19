package ru.example.register.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.example.register.db.entity.User;
import java.util.Collection;

/**
 * Реализация интерфейса UserDetails.
 *
 * @author Комов Максим
 */

public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Наш кастомный пользователь.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }


    /**
     * Возвращает полномочия, предоставленные пользователю.
     *
     * @return коллекцию полномочий
     */
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    /**
     * Возвращение пороля для нашего пользователя.
     *
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    /**
     * Возвращение ника для нашего пользователя.
     *
     * @return ник пользователя
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Указывает, истек ли срок действия учетной записи пользователя.
     *
     * @return статус пользователя
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * Указывает, заблокирован пользователь или разблокирован.
     *
     * @return статус пользователя
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Указывает, истек ли срок действия учетных данных (пароля) пользователя.
     *
     * @return статус пользователя
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Указывает, включен или отключен пользователь.
     *
     * @return статус пользователя
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * Возвращение полного имени пользователя.
     *
     * @return полное имя пользователя
     */
    public String getFullName() {
        return user.getLast_name() + " " + user.getFirst_name() + " " + user.getMiddle_name();
    }
}