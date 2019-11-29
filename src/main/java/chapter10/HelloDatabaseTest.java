package kap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDatabaseTest {

  public static void main(String[] args) {
    String databaseURL = "jdbc:derby:ballsdb;create=true";

    try (Connection conn = DriverManager.getConnection(databaseURL)
        ; Statement statement = conn.createStatement();) {

      // create database

      String sql = "CREATE TABLE balls (id INTEGER PRIMARY KEY, title varchar(128))";
      statement.executeUpdate(sql);

      DriverManager.getConnection("jdbc:derby:;shutdown=true");

    } catch (SQLException ex) {
      if (ex.getSQLState().equals("XJ015")) {
        System.out.println("Derby shutdown normally");
      } else {
        ex.printStackTrace();
      }
    }
  }


}