package chapter10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDatabaseVaryRS {

  public static void main(String[] args) {
    String databaseURL = "jdbc:derby:bikesdb;create=true";

    try (Connection conn = DriverManager.getConnection(databaseURL)
        ; Statement statement = conn
        .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

//      if (!doesTableExists("bikes", conn)) {
//        String sql =
//            "CREATE TABLE bikes "
//                + "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
//                + ", title varchar(128))";
//        statement.execute(sql);
//      }
//
//      //  Create
//      statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Crescent')");
//      statement.executeUpdate("INSERT INTO bikes VALUES (default, 'BMX')");
//      statement.executeUpdate("INSERT INTO bikes VALUES (default, 'DBS')");
//      statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Monark')");
//      statement.executeUpdate("INSERT INTO bikes VALUES (default, 'Kalas')");

      // Read
      String selectSqlStr = "SELECT * FROM bikes";

      ResultSet resultSet = statement.executeQuery(selectSqlStr);
      while (resultSet.next()) {
        System.out.print(resultSet.getInt("id") + " " + resultSet.getString("title")+", ");
      } // 1 Crescent, 2 BMX, 3 DBS, 4 Monark, 5 Kalas,

      System.out.println();

      resultSet.beforeFirst();
      while (resultSet.next()) {
        System.out.print(resultSet.getInt("id") + " " + resultSet.getString("title")+", ");
      } // 1 Crescent, 2 BMX, 3 DBS, 4 Monark, 5 Kalas,

      System.out.println();

      resultSet.afterLast();
      while (resultSet.previous()) {
        System.out.print(resultSet.getInt("id") + " " + resultSet.getString("title")+", ");
      } // 5 Kalas, 4 Monark, 3 DBS, 2 BMX, 1 Crescent,

      System.out.println("\n");

      if (resultSet.first()) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 1 Crescent

      if (resultSet.last()) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 5 Kalas

      System.out.println();

      if (resultSet.absolute(-1)) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 5 Kalas

      if (resultSet.absolute(2)) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 2 BMX

      if (resultSet.relative(2)) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 4 Monark

      if (resultSet.relative(-1)) {
        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
      } // 3 DBS

      DriverManager.getConnection("jdbc:derby:;shutdown=true");

    } catch (SQLException ex) {
      if (ex.getSQLState().equals("XJ015")) {
        System.out.println("Derby shutdown normally");
      } else {
        ex.printStackTrace();
      }
    }
  }

//  private static boolean doesTableExists(String tableName, Connection conn) throws SQLException {
//    DatabaseMetaData meta = conn.getMetaData();
//    ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);
//    return result.next();
//  }

}