package chapter10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HelloDatabaseDifferentGetMethods {

  public static void main(String[] args) {

    try (Connection conn = DriverManager.getConnection("jdbc:derby:bikesdb;create=true");
        Statement statement = conn.createStatement();) {

      // Read
      ResultSet resultSet = statement.executeQuery("SELECT * FROM bikes");
      if (resultSet.next()) {

        int id1 = resultSet.getInt("id");
        int id2 = resultSet.getInt(1);

        String brand1 = resultSet.getString("title");
        String brand2 = resultSet.getString(2);

        boolean b1 = resultSet.getBoolean("bockstyre");
        boolean b2 = resultSet.getBoolean(3);

        double d1 = resultSet.getDouble("antalTum");
        double d2 = resultSet.getDouble(4);

        long l1 = resultSet.getLong("ramNr");
        long l2 = resultSet.getLong(5);

        Object o1 = resultSet.getObject("xyz");
        Object o2 = resultSet.getObject(6);

        java.sql.Date date1 = resultSet.getDate("ettDatum");
        java.sql.Date date2 = resultSet.getDate(7);
        LocalDate ld1 = date1.toLocalDate();

        java.sql.Time t1 = resultSet.getTime("ettKlockslag");
        java.sql.Time t2 = resultSet.getTime(8);
        LocalTime lt1 = t1.toLocalTime();

        java.sql.Timestamp ts1 = resultSet.getTimestamp("enTidpunkt");
        java.sql.Timestamp ts2 = resultSet.getTimestamp(9);
        LocalDateTime ldt1 = ts1.toLocalDateTime();


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