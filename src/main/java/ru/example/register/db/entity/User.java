package ru.example.register.db.entity;

import javax.persistence.*;

/**
 * Представление таблицы Users.
 *
 * @author Комов Максим
 */

@Entity
@Table(name = "Users", schema = "public")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "Last_name")
    private String last_name;

    @Column(name = "First_name")
    private String first_name;

    @Column(name = "Middle_name")
    private String middle_name;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Count")
    private int count;

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = Picture.class)
    @JoinColumn(name = "Id_picture")
    private Picture picture;

    public User() {
    }

    public User(int id_user, String last_name, String first_name, String middle_name, String username, String password, int count, Picture picture) {
        this.id_user = id_user;
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.username = username;
        this.password = password;
        this.count = count;
        this.picture = picture;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}

