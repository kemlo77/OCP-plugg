package chapter9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FilesTester {

  public static void main(String[] args) {

    Path relDirPath = Paths.get("src/main/");
    Path absDirPath = Paths.get("/home/fredrik/Documents");

    Path relFilePath = Paths.get("../OCP-plugg/README.md");
    Path absFilePath = Paths.get("/home/fredrik/IdeaProjects/OCP-plugg/README.md");

    Path nonExistentFile = Paths.get("arne-banarne");

    System.out.println("\n_Testing a Path_");
    // Testing a Path
    System.out.println(Files.exists(relDirPath));                                // true
    System.out.println(Files.exists(nonExistentFile));                           // false
    System.out.println(Files.exists(nonExistentFile, LinkOption.NOFOLLOW_LINKS)); // false

    System.out.println("\n_Testing uniqueness_");
    // Testing uniqueness
    // first it compares using .equals(). If so, it returns true
    // if .equals() returns false, it then it locates each file and deterimines if they're the same
    try {
      System.out.println(Files.isSameFile(relDirPath, relDirPath)); // true
      System.out.println(Files.isSameFile(relDirPath, absDirPath)); // false

      System.out.println(Files.isSameFile(relDirPath, relDirPath)); // true
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\n_Making directories_");
    // Making directories

    // kastar exceptions om det inte går att skapa katalogen. T.ex. om den redan finns.
    try {
      Files.createDirectory(Paths.get("arne-banarne")); // skapar arne-banarne
      Files.createDirectory(
          Paths.get("arne-banarne/kex")); // skapar arne-banarne/kex om arne-banarne finns
      //Files.createDirectories(Paths.get("/home/fredrik/juice/pressad/kiwi")); // skapar juice/pressad/kiwi
    } catch (IOException e) {
      e.printStackTrace();
      // kan kasta: DirectoryNotEmptyException, NoSuchFileException, etc
    }

    System.out.println("\n_Duplicating file contents_");
    // Duplicating file contents
    // directory copies are shallow rather than deep (files & subdirectories not copied)
    try {
      Files.copy(Paths.get("README.md"), Paths.get("temp1.txt"));
      Files.copy(Paths.get("README.md"), Paths.get("temp2.txt"), LinkOption.NOFOLLOW_LINKS);
      Files.copy(Paths.get("README.md"), Paths.get("temp3.txt"), StandardCopyOption.COPY_ATTRIBUTES);
      Files.copy(Paths.get("README.md"), Paths.get("temp3.txt"), StandardCopyOption.REPLACE_EXISTING);
      Files.copy(Paths.get("arne-banarne"), Paths.get("arne-potatis"));
    } catch (IOException e) {
      e.printStackTrace();
      // kan kasta: FileAlreadyExistsException
    }

    // to/from (IO) streams
    try (InputStream inputStream = new FileInputStream("README.md");
        OutputStream outputStream = new FileOutputStream("temp4.txt")) {
      Files.copy(inputStream, Paths.get("temp5.txt"));
      Files.copy(inputStream, Paths.get("temp6.txt"),StandardCopyOption.REPLACE_EXISTING);
      Files.copy(Paths.get("README.md"), outputStream);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    System.out.println("\n_Changing a file location_");
    // Changing a file location
    //Moving or renaming
    // If the file is a symbolic link then the symbolic link itself, not the target of the link, is moved.
    try {
      Files.move(Paths.get("temp6.txt"), Paths.get("temp6b.txt"));
      Files.move(Paths.get("temp6b.txt"), Paths.get("temp6c.txt"),StandardCopyOption.ATOMIC_MOVE);
      Files.move(Paths.get("temp6c.txt"), Paths.get("temp6b.txt"),StandardCopyOption.REPLACE_EXISTING);
      //Files.move(Paths.get("/home/fredrik/temp"), Paths.get("/home/fredrik/temp2"));
    } catch (IOException e) {
      e.printStackTrace();
    }



    System.out.println("\n_Removing a file_");
    // Removing a file
    // This method may be invoked to remove a file or an empty directory.
    // If the file is a symbolic link then the symbolic link itself, not the target of the link, is removed.
    try {
      Files.delete(Paths.get("arne-banarne/kex"));
      Files.delete(Paths.get("arne-banarne"));
      Files.delete(Paths.get("temp1.txt"));
      Files.delete(Paths.get("temp2.txt"));
      Files.delete(Paths.get("temp3.txt"));
      Files.delete(Paths.get("temp4.txt"));
      Files.delete(Paths.get("temp5.txt"));
      Files.delete(Paths.get("temp6b.txt"));
      Files.deleteIfExists(Paths.get("arne-potatis"));

    } catch (IOException e) {
      e.printStackTrace();
      // kan kasta: DirectoryNotEmptyException, NoSuchFileException, etc
    }


    System.out.println("\n_Reading and writing file data_");
    // Reading and writing file data
    //Charset.defaultCharset()
    try (BufferedReader reader = Files.newBufferedReader(Paths.get("tempText.txt"))) {
      String currentLine = null;
      while ((currentLine = reader.readLine()) != null) {
        System.out.println(currentLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    //BufferedReader reader = Files.newBufferedReader(Paths.get("tempText.txt"), Charset.defaultCharset());
    //BufferedReader reader = Files.newBufferedReader(Paths.get("tempText.txt"), Charset.forName("US-ASCII"));

    Path path = Paths.get("tempText.txt");
    List<String> data = new ArrayList<>();
    try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
      writer.write("asdf");
      writer.newLine();
      writer.write("qwer");
      writer.newLine();
      writer.write("zxcv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    //BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.APPEND);


    System.out.println("\n_Reading files_");
    // Reading files
    Path pathX = Paths.get("tempText.txt");
    try {
      final List<String> lines = Files.readAllLines(pathX);
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    //Files.readAllLines(pathX, Charset.defaultCharset());


  }

}
