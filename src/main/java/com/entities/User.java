package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "surveys")
public class User {

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    public boolean is_equal(User user) {
        return (login.equals(user.getLogin()) && password.equals(user.getPassword()));
    }
}