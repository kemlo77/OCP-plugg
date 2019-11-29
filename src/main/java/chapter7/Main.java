package kap7;

public class Main {


  public static void main(String[] args) {

    BankAccount account = new BankAccount("123456-789", 1000.00);

    Thread t1 = new Thread(new Runnable(){

      @Override
      public void run(){

        account.deposit(200.00);
        account.withdraw(123);

      }
    });
    Thread t2 = new Thread(new Runnable(){

      @Override
      public void run(){

        account.deposit(200.00);
        account.withdraw(123);

      }
    });

    t1.start();
    t2.start();




  }

}
