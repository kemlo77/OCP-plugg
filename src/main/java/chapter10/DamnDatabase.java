package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DamnDatabase {


  public static void main(String[] args) throws SQLException {
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
    } catch (Exception e) {
      e.printStackTrace(); //Does not get executed!
    }

    String databaseURL = "jdbc:derby:booksdb;create=true";

    Connection conn = DriverManager.getConnection(databaseURL);
    Statement statement = conn.createStatement();

    //DriverManager.getConnection("jdbc:derby:;shutdown=true");


  }

}
