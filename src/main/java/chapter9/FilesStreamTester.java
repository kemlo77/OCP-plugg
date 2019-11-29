package chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FilesStreamTester {

  public static void main(String[] args) {

    System.out.println("\nWalking a directory");
    // traverses directory in a depth-first, lazy manner
    // Will iterate up to Integer.MAX_VALUE directories deep by default
    // Avoid circular paths (can happen with symlinks) -> FileSystemLoopException
    Path homeDir = Paths.get("/home/fredrik");
    try {
      System.out.println(Files.walk(homeDir).filter(p -> p.toString().endsWith(".java")).count()); // 107
      System.out.println(Files.walk(homeDir,2).filter(p -> p.toString().endsWith(".java")).count()); // 1

    } catch (IOException e) {
      e.printStackTrace();
    }


    System.out.println("\nSearching a directory");
    try {
      Files.find(homeDir,Integer.MAX_VALUE,(p,a)->p.toString().endsWith(".java") && a.size() > 4000)
          .filter(p->Files.isRegularFile(p))
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }


    System.out.println("\nListing directory contents");
    try {
      Files.list(homeDir)
          .filter(p -> !Files.isDirectory(p))
          .map(p -> p.toAbsolutePath())
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\nPrinting file contents");
    try {
      Files.lines(Paths.get("tempText.txt"))
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
