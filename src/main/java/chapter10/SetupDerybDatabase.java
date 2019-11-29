package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDerybDatabase {

  public static void main(String[] args) {

    String url = "jdbc:derby:zoo;create=true";

    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement()) {

      //stmt.executeUpdate("DROP TABLE species");
      //stmt.executeUpdate("DROP TABLE animal");

      stmt.executeUpdate(
          "CREATE TABLE species (id INTEGER PRIMARY KEY, name VARCHAR(255), num_acres DECIMAL)");
      stmt.executeUpdate(
          "CREATE TABLE animal (id INTEGER PRIMARY KEY, species_id integer, name VARCHAR(255), date_born TIMESTAMP)");

      stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
      stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");

      stmt.executeUpdate("INSERT INTO animal VALUES (1, 1 , 'Elsa',  '2001-05-06 02:15:00')");
      stmt.executeUpdate("INSERT INTO animal VALUES (2, 2 , 'Zelda', '2002-08-15 02:15:00')");
      stmt.executeUpdate("INSERT INTO animal VALUES (3, 1 , 'Ester', '2002-09-09 02:15:00')");
      stmt.executeUpdate("INSERT INTO animal VALUES (4, 1 , 'Eddie', '2010-06-08 02:15:00')");
      stmt.executeUpdate("INSERT INTO animal VALUES (5, 2 , 'Zoe',   '2005-11-12 02:15:00')");


    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
