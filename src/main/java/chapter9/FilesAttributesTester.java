package chapter9;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

public class FilesAttributesTester {

  public static void main(String[] args) {

    Path relDirPath = Paths.get("src/main/");
    Path absDirPath = Paths.get("/home/fredrik/Documents");

    Path relFilePath = Paths.get("../OCP-plugg/README.md");
    Path absFilePath = Paths.get("/home/fredrik/IdeaProjects/OCP-plugg/README.md");

    Path symbolicLinkDir = Paths.get("/var/lock");
    Path symbolicLinkFile = Paths.get("/initrd.img");

    System.out.println("\nReading common attributes");
    System.out.println(Files.isSymbolicLink(relDirPath));       // false
    System.out.println(Files.isRegularFile(relDirPath));        // false
    System.out.println(Files.isDirectory(relDirPath));          // true

    System.out.println(Files.isSymbolicLink(symbolicLinkDir));  // true
    System.out.println(Files.isRegularFile(symbolicLinkDir));   // false
    System.out.println(Files.isDirectory(symbolicLinkDir));     // true

    System.out.println(Files.isSymbolicLink(symbolicLinkFile)); // true
    System.out.println(Files.isRegularFile(symbolicLinkFile));  // true
    System.out.println(Files.isDirectory(symbolicLinkFile));    // false

    System.out.println("\nChecking file visibility");
    try {
      System.out.println(Files.isHidden(Paths.get(".git)")));        // true
      System.out.println(Files.isHidden(Paths.get("gradle.build"))); // false
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\nTesting file accessibility");
    // throws no exceptions. Will return false if file does not exist
    System.out.println(Files.isReadable(relDirPath));             // true
    System.out.println(Files.isReadable(absFilePath));            // true

    System.out.println(Files.isExecutable(absFilePath));          // false
    System.out.println(Files.isExecutable(Paths.get("gradlew"))); // true

    System.out.println("\nReading file length");
    try {
      System.out.println(Files.size(absDirPath));                 // 4096
      System.out.println(Files.size(absFilePath));                // 63
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\nManaging file modifications");
    try {
      Path tempFilePath = Paths.get("tempText.txt");
      FileTime filetime = Files.getLastModifiedTime(tempFilePath);
      System.out.println(filetime.toInstant()); // 2019-11-10T11:03:42Z

      FileTime newFileTime = FileTime.fromMillis(System.currentTimeMillis());
      Files.setLastModifiedTime(tempFilePath, newFileTime);
      System.out.println(Files.getLastModifiedTime(tempFilePath).toInstant()); // 2019-11-10T13:49:07Z
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\nManaging ownership");
    try {
      UserPrincipal owner1 = FileSystems.getDefault().getUserPrincipalLookupService()
          .lookupPrincipalByName("fredrik");
      UserPrincipal owner2 = relFilePath.getFileSystem().getUserPrincipalLookupService()
          .lookupPrincipalByName("fredrik");
      System.out.println(owner1); // fredrik

      System.out.println(Files.getOwner(relFilePath)); // fredrik
      Files.setOwner(relFilePath,owner1);
      System.out.println(Files.getOwner(relFilePath).getName()); // fredrik

    } catch (IOException e) {
      e.printStackTrace();
      // kan kasta UserPrincipalNotFoundException
    }

  }

}
