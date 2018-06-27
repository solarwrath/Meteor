package Meteor.core;

import com.mysql.cj.exceptions.ConnectionIsClosedException;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String full_name;
    private Date date;
    private Gender gender;

    public enum Gender{
        MALE("Male"), FEMALE("Female");
        private String gender;
        Gender(String gender){
            this.gender = gender;
        }
        public String getGenderToString(){ return gender;}
    }

    public User(String username, String password, String email, String full_name, Gender gender) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static boolean isValidUsename(String givenUsername) {
        return givenUsername != null && givenUsername.length() >= 2 && givenUsername.length() <= 20 && !givenUsername.trim().contains(" ");
    }

    public static boolean isValidPassword(String givenPassword) {
        return givenPassword != null && givenPassword.length() >= 3 && givenPassword.length() <= 30 && !givenPassword.trim().contains(" ");
    }

    public static boolean isValidEmail(String givenEmail) {
        return givenEmail != null && givenEmail.length() <= 64 && java.util.regex.Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(givenEmail).matches();
    }

    public static boolean isValidFullName(String givenFullName) {
        return givenFullName != null && givenFullName.length() <= 30 && java.util.regex.Pattern.compile("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$").matcher(givenFullName).matches();
    }

    public static boolean usernameAlreadyTaken(String givenUsername) {
        try {
            return DBHandler.returnFromSQLQuery("SELECT * FROM students WHERE username='" + givenUsername + "'").next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean emailAlreadyTaken(String givenEmail) throws SQLException {
        return DBHandler.returnFromSQLQuery("SELECT * FROM students WHERE email='" + givenEmail + "'").next();
    }

    public static String getPasswordFromEmail(String givenEmail) throws SQLException {
        //TODO Custom Exceptions
        ResultSet tempRS = DBHandler.returnFromSQLQuery("SELECT password FROM students WHERE email='" + givenEmail + "'");
        if (tempRS.next()) {
            if (tempRS.getString("password").length() > 0) {
                return tempRS.getString("password");
            }
        }
        return "Error";
    }

    public static String getUsernameFromEmail(String givenEmail) throws SQLException {
        //TODO Custom Exceptions
        ResultSet tempRS = DBHandler.returnFromSQLQuery("SELECT username FROM students WHERE email='" + givenEmail + "'");
        if (tempRS.next()) {
            if (tempRS.getString("username").length() > 0) {
                return tempRS.getString("username");
            }
        }
        return "Error";
    }

    public ArrayList<String> validateUser(User givenUser) throws SQLException, ConnectionIsClosedException, ConnectException {
        ArrayList<String> listOfErrors = new ArrayList<>();
        if (!isValidUsename(givenUser.getUsername())) {
            listOfErrors.add("username");
        }
        if (!isValidPassword(givenUser.getPassword())) {
            listOfErrors.add("password");
        }
        if (!isValidEmail(givenUser.getEmail())) {
            listOfErrors.add("email");
        }
        if (!isValidFullName(givenUser.getFullName())) {
            listOfErrors.add("full_name");
        }
        if (usernameAlreadyTaken(givenUser.getUsername())) {
            listOfErrors.add("username_already_taken");
        }
        if (emailAlreadyTaken(givenUser.getEmail())) {
            listOfErrors.add("email_already_taken");
        }
        return listOfErrors;
    }

}
