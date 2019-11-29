package kap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDatabaseVaryRS {

  public static void main(String[] args) {
    String databaseURL = "jdbc:derby:bikesdb;create=true";

    try (Connection conn = DriverManager.getConnection(databaseURL)
        ; Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);) {



      //  Create
      //statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Crescent')");
      //statement.executeUpdate("INSERT INTO bikes VALUES (default, 'BMX')");
      //statement.executeUpdate("INSERT INTO bikes VALUES (default, 'DBS')");
      //statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Monark')");
      //statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Kalas')");


      // Read
      String selectSqlStr = "SELECT * FROM bikes";

      ResultSet result = statement.executeQuery(selectSqlStr);
      while (result.next()) {
        System.out.println(result.getInt("id") + " " + result.getString("title"));
      }


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