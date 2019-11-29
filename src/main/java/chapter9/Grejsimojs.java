package chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Grejsimojs {

  public static void main(String[] args) {
    try {
      Files.list(Paths.get(".")).forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}