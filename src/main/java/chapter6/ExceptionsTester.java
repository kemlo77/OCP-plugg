package chapter6;

public class ExceptionsTester {

  public static void main(String[] args) {

    try {
      //protected code
    } catch (RuntimeException e) {
      // one or several catch blocks
      System.out.println(e);
      System.out.println(e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      // one or several catch blocks
    } finally {
      //at most one finally block
    }

  }

  static void troublesomeMethod() throws ArithmeticException, NullPointerException {
    System.out.println("nothing here");

  }
}




