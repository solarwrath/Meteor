package Meteor.core;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String full_name;
    private Date date;

    public User(String username, String password, String email, String last_name, String gender, Date date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.gender = gender;
        this.date = date;
    }

    public User(String username, String password, String email, String full_name, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.gender = gender;
    }

    public User(String username, String password, String email, String full_name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    private String gender;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
