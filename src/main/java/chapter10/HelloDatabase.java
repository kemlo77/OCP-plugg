package chapter10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDatabase {

  public static void main(String[] args) {
    String databaseURL = "jdbc:derby:bikesdb;create=true";

    try (Connection conn = DriverManager.getConnection(databaseURL);
        Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);) {

      // create database
      if (!doesTableExists("bikes", conn)) {
        String sql =
            "CREATE TABLE bikes "
                + "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
                + ", title varchar(128))";
        statement.executeUpdate(sql);
      }

      //  Create
      String insertSqlStr = "INSERT INTO bikes VALUES (default, 'Crescent')";
      int insertedRows = statement.executeUpdate(insertSqlStr);
      System.out.println("insertedRows: " + insertedRows);

      // Read
      String selectSqlStr = "SELECT * FROM bikes";
      ResultSet result = statement.executeQuery(selectSqlStr);
      while (result.next()) {
        System.out.println(result.getInt("id") + " " + result.getString("title"));
      }

      // Update
      String updateSqlStr = "UPDATE bikes SET title = 'Monark' WHERE title = 'Crescent'";
      int updatedRows = statement.executeUpdate(updateSqlStr);
      System.out.println("updatedRows: " + updatedRows);

      // Delete
      String deleteSqlStr = "DELETE FROM bikes WHERE title = 'Monark'";
      int deletedRows = statement.executeUpdate(deleteSqlStr);
      System.out.println("deletedRows: " + deletedRows);

      DriverManager.getConnection("jdbc:derby:;shutdown=true");

    } catch (SQLException ex) {
      if (ex.getSQLState().equals("XJ015")) {
        System.out.println("Derby shutdown normally");
      } else {
        ex.printStackTrace();
      }
    }
  }

  private static boolean doesTableExists(String tableName, Connection conn) throws SQLException {
    DatabaseMetaData meta = conn.getMetaData();
    ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);
    return result.next();
  }

}