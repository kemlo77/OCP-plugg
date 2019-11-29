package chapter8;

import java.io.File;

public class FileTester {

  public static void main(String[] args) {
    File file = new File("/Users/fredrik/gitProjects/Flum/vaseFile.txt");

    System.out.println("file exists: " + file.exists()); //file exists: true

    if (file.exists()) {
      System.out.println("File name: " + file.getName());             //File name: Flum
      System.out.println("Absolute path: " + file.getAbsolutePath()); //Absolute path: /Users/fredrik/gitProjects/Flum
      System.out.println("Is directory: " + file.isDirectory());      //Is directory: true
      System.out.println("Parent path: " + file.getParent());         //Parent path: /Users/fredrik/gitProjects
      if (file.isFile()) {
        System.out.println("File size: " + file.length());
        System.out.println("File last modified: " + file.lastModified());
      } else {
        for (File subFile : file.listFiles()) {
          System.out.println("\t"+subFile.getName()); // prints names of files in directory
        }
      }
    }

    System.out.println(System.getProperty("file.separator")); // /
    System.out.println(File.separator);                       // /
  }
}
