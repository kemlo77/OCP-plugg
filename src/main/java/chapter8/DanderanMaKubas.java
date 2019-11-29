package kap8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DanderanMaKubas {

  public static void main(String[] args) throws FileNotFoundException {

    File source = new File("src/test3.txt");
    File destination = new File("src/test4.txt");

    PrintWriter printWriter = new PrintWriter(destination);

    printWriter.print(5); // PrintWriter method
    printWriter.println(6); // PrintWriter method
    printWriter.format("7"); // PrintWriter method, does not insert line breaks
    printWriter.printf("8"); // PrintWriter method, does not insert line breaks
    printWriter.write(String.valueOf(9)); // Writer method
    printWriter.close();

    System.out.println("line.separator: " + System.getProperty("line.separator"));

  }

}


