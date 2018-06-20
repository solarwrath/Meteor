package Meteor.core;

import java.lang.reflect.Array;
import java.sql.*;

public class DBHandler {
    // JDBC URL, username and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/unimanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public void addUser(User givenUser){
        String givenUsername = givenUser.getUsername();
        String givenPassword = givenUser.getPassword();
        String givenEmail = givenUser.getEmail();
        String givenFullName = givenUser.getFullName();
        String givenGender = givenUser.getGender();
        Date currentDate = new Date(new java.util.Date().getTime());
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlToExecute = "INSERT INTO students (username, password, email, full_name, gender, date_added)\n" +
                "VALUES (?, ?, ?, ?, ?, ?); ";
        try (PreparedStatement preparedStatement = con.prepareStatement(sqlToExecute)) {
            preparedStatement.setString(1, givenUsername);
            preparedStatement.setString(2, givenPassword);
            preparedStatement.setString(3, givenEmail);
            preparedStatement.setString(4, givenFullName);
            preparedStatement.setString(5, givenGender);
            preparedStatement.setDate(6, currentDate);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
