package kap10;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloDatabaseAlternative {

  public static void main(String[] args) {
    String databaseURL = "jdbc:derby:flowersdb;create=true";

    try (Connection conn = DriverManager.getConnection(databaseURL)
        ; Statement statement = conn.createStatement();) {

      // create database
      if (!doesTableExists("flowers", conn)) {
        String sql =
            "CREATE TABLE flowers "
                + "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
                + ", title varchar(128))";
        statement.execute(sql);
      }

      //  Create
      String insertSqlStr = "INSERT INTO flowers VALUES (default, 'Daffodil')";
      boolean insertReturnsResultSet = statement.execute(insertSqlStr);
      System.out.println("insertReturnsResultSet: " + insertReturnsResultSet); // false
      System.out.println(statement.getUpdateCount() + " rows inserted");       // 1 rows inserted

      // Read
      String selectSqlStr = "SELECT * FROM flowers";
      boolean selectReturnsResultSet = statement.execute(selectSqlStr);
      System.out.println("selectReturnsResultSet: " + selectReturnsResultSet); // true
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        System.out.println(result.getInt("id") + " " + result.getString("title"));
      } // 1 Daffodil

      // Update
      String updateSqlStr = "UPDATE flowers SET title = 'Dandelion' WHERE title = 'Daffodil'";
      boolean updateReturnsResultSet = statement.execute(updateSqlStr);
      System.out.println("updateReturnsResultSet: " + updateReturnsResultSet); // false
      System.out.println(statement.getUpdateCount() + " rows updated");        //1 rows updated

      // Delete
      String deleteSqlStr = "DELETE FROM flowers WHERE title = 'Dandelion'";
      boolean deleteReturnsResultSet = statement.execute(deleteSqlStr);
      System.out.println("deleteReturnsResultSet: " + deleteReturnsResultSet); // false
      System.out.println(statement.getUpdateCount() + " rows deleted");        // 1 rows deleted

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