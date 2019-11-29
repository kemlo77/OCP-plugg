package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DamnDatabase {


  public static void main(String[] args) throws SQLException {
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
      //Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }

    String databaseURL = "jdbc:derby:tempdb;create=true";

    Connection conn = DriverManager.getConnection(databaseURL);
    Statement statement = conn.createStatement();

    try {
      DriverManager.getConnection("jdbc:derby:;shutdown=true");
    } catch (SQLException e) {
      if (e.getSQLState().equals("XJ015")) {
        System.out.println("Derby shut down normally");
      } else {
        e.printStackTrace();
      }
    }


  }

}
