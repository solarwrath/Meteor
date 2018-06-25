package Meteor.core;

import Meteor.Main;

import java.lang.reflect.Array;
import java.sql.*;

public class DBHandler {
    // JDBC URL, username and password of MySQL server
    private static PropertiesConfig config = Main.propertiesConfig;
    private static final String URL = config.URL();
    private static final String USER = config.user();
    private static final String PASSWORD = config.password();

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public DBHandler() throws SQLException{
        getConnection();
    }

    //pbbly need to establish one connection per session somehow.
    private static void getConnection() throws SQLException{
        con = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addUser(User givenUser) throws SQLException{
        getConnection();
        String givenUsername = givenUser.getUsername();
        String givenPassword = givenUser.getPassword();
        String givenEmail = givenUser.getEmail();
        String givenFullName = givenUser.getFullName();
        String givenGender = givenUser.getGender();
        Date currentDate = new Date(new java.util.Date().getTime());
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
        finally {
            con.close();
        }
    }

    public static ResultSet returnFromSQLQuery(String givenSQLString) throws SQLException{
        getConnection();
        return con.prepareStatement(givenSQLString).executeQuery();
    }
}
