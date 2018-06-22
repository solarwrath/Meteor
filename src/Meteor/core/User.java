package Meteor.core;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean isValidUsename(String givenUsername) {
        if (givenUsername == null || givenUsername.length() < 2 || givenUsername.length() > 20 || givenUsername.trim().contains(" ")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidPassword(String givenPassword) {
        if (givenPassword == null || givenPassword.length() < 3|| givenPassword.length() > 30 || givenPassword.trim().contains(" ")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidEmail(String givenEmail) {
        if (givenEmail == null || givenEmail.length() > 64 || !java.util.regex.Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(givenEmail).matches()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidFullName(String givenFullName) {
        if (givenFullName == null || givenFullName.length() > 30 || !java.util.regex.Pattern.compile("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$").matcher(givenFullName).matches()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean usernameAlreadyTaken(String givenUsername){
        try{
            return DBHandler.returnFromSQLQuery("SELECT * FROM students WHERE username='"+givenUsername+"'").next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    public boolean emailAlreadyTaken(String givenEmail){
        try{
            return DBHandler.returnFromSQLQuery("SELECT * FROM students WHERE email='"+givenEmail+"'").next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    public ArrayList<String> validateUser(User givenUser){
        ArrayList<String> listOfErrors = new ArrayList<>();
        if(!isValidUsename(givenUser.getUsername())){
            listOfErrors.add("username");
        }
        if(!isValidPassword(givenUser.getPassword())){
            listOfErrors.add("password");
        }
        if(!isValidEmail(givenUser.getEmail())){
            listOfErrors.add("email");
        }
        if(!isValidFullName(givenUser.getFullName())){
            listOfErrors.add("full_name");
        }
        if(usernameAlreadyTaken(givenUser.getUsername())){
            listOfErrors.add("username_already_taken");
        }
        if(emailAlreadyTaken(givenUser.getEmail())){
            listOfErrors.add("email_already_taken");
        }
        return listOfErrors;
    }

}
