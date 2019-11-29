package kap8;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;

public class Dialoguer {

  public static void main(String[] args) throws IOException {
    Console console = System.console();

    if (console != null) {
      String userInput = console.readLine("Hur är läget? ");
      console.flush();
      console.writer().println("Du sa att läget är: " + userInput);

      console.writer().format("%s = %d", "joe", 35);
      console.writer().println();
      console.printf("%s = %d", "joe", 35); //identical to .format()
      console.writer().println();

      console.writer().print("Säg en sak till: ");
      console.flush();
      BufferedReader reader = new BufferedReader(console.reader());
      String anotherThing = reader.readLine(); //kastar IOException
      console.writer().println("Den andra saken: " + anotherThing);

      console.writer().print("Säg en hemlig grej: ");
      console.flush();
      char[] thirdThing = console.readPassword("Skriv nåt hemligt");
      console.writer().println("Den hemliga grejen: " + thirdThing);
    }


  }

}
