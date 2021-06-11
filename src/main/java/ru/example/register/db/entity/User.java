package ru.example.register.db.entity;


import org.hibernate.annotations.Type;
import javax.persistence.*;

/**
 * Class User for JPA technology with Getters and Setters
 * @author Maxim Komov
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

    @Column(name = "Photo")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] photo;

    @Column(name = "Count")
    private int count;

    public User() {
    }

    public User(int id_user, String last_name, String first_name, String middle_name, String username, String password, byte[] photo, int count) {
        this.id_user = id_user;
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.count = count;
    }

    public int getId() {
        return id_user;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public int getCount() {
        return count;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

