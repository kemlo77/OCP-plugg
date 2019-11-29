package chapter9;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTester {

  public static void main(String[] args) {

    // Creating
    Path relDirPath = Paths.get("src/main/");
    Path absDirPath1 = Paths.get("/home/fredrik/IdeaProjects/OCP-plugg");
    Path absDirPath2 = Paths.get("/home/fredrik/Documents");

    Path relFilePath1 = Paths.get("./build.gradle");
    Path relFilePath2 = Paths.get("../OCP-plugg/build.gradle");
    Path absFilePath = Paths.get("/home/fredrik/IdeaProjects/OCP-plugg/build.gradle");

    FileSystem fs = FileSystems.getDefault();
    Path absFilePath3 = fs.getPath("./ build.gradle");
    System.out.println(absFilePath3.toString());

    System.out.println("\n_Viewing the Path_");
    // viewing the path
    System.out.println(relDirPath.toString()); // src/main
    System.out.println(absFilePath.toString());      // /home/fredrik/IdeaProjects/OCP-plugg/build.gradle
    System.out.println(absFilePath.getNameCount());  // 5
    System.out.println(absFilePath.getName(0));  // home
    System.out.println(absFilePath.getName(4));  // build.gradle


    System.out.println("\n_Accessing Path compontents_");
    // accessing path components
    System.out.println(absDirPath1.getFileName());   // main
    System.out.println(relFilePath1.getFileName()); // build.gradle

    System.out.println(absDirPath1.getParent());     // /home/fredrik/IdeaProjects
    System.out.println(relFilePath1.getParent());   // .
    System.out.println(Paths.get(".").getParent()); // null  (no parent)

    System.out.println(absDirPath1.getRoot());       // /
    System.out.println(relFilePath1.getRoot());     // null (when the path is relative)


    System.out.println("\n_Checking Path type_");
    // Checking Path type
    System.out.println(relFilePath2.isAbsolute()); // false
    System.out.println(absFilePath.isAbsolute());  // true

    System.out.println(relFilePath1.toAbsolutePath()); // /home/fredrik/IdeaProjects/OCP-plugg/./build.gradle
    System.out.println(absDirPath1.toAbsolutePath());   // /home/fredrik/IdeaProjects/OCP-plugg
    System.out.println(Paths.get("asdf").toAbsolutePath()); // /home/fredrik/IdeaProjects/OCP-plugg/asdf


    System.out.println("\n_Creating a new Path_");
    // Creating a new Path
    System.out.println(absDirPath1);              // /home/fredrik/IdeaProjects/OCP-plugg
    System.out.println(absDirPath1.subpath(0,2)); // home/fredrik
    System.out.println(absDirPath1.subpath(1,3)); // fredrik/IdeaProjects


    System.out.println("\n_Deriving a Path_");
    // Deriving a Path
    //System.out.println(relDirPath.relativize(absFilePath)); // Throws IllegalArgumentException
    System.out.println(relDirPath);   // src/main
    System.out.println(relFilePath1); // .build.gradle
    System.out.println(relDirPath.relativize(relFilePath1)); // ../.././build.gradle

    System.out.println(absDirPath2); // /home/fredrik/Documents
    System.out.println(absDirPath1); // /home/fredrik/IdeaProjects/OCP-plugg
    System.out.println(absDirPath2.relativize(absDirPath1)); // ../IdeaProjects/OCP-plugg
    System.out.println(absDirPath1.relativize(absDirPath2)); // ../../Documents


    System.out.println("\n_Joining Path Objects_");
    // Joining Path Objects
    System.out.println(Paths.get("arne-banarne").resolve(Paths.get("lingondricka"))); // arne-banarne/lingondricka
    System.out.println(relFilePath1.resolve(relFilePath1)); // ./build.gradle/./build.gradle
    System.out.println(absDirPath1);                     // /home/fredrik/IdeaProjects/OCP-plugg
    System.out.println(relDirPath.resolve(absDirPath1)); // /home/fredrik/IdeaProjects/OCP-plugg


    System.out.println("\n_Cleaning up a Path_");
    // Cleaning up a Path
    System.out.println(relFilePath1.resolve(relFilePath1)); // ./build.gradle/./build.gradle
    System.out.println(relFilePath1.resolve(relFilePath1).normalize()); // build.gradle/build.gradle
    System.out.println(relDirPath.relativize(relFilePath1)); // ../.././build.gradle
    System.out.println(relDirPath.relativize(relFilePath1).normalize()); // ../../build.gradle

    Path path1 = Paths.get("/home/fredrik/IdeaProjects");
    Path path2 = Paths.get("/home/fredrik/Documents");
    Path relativePath = path1.relativize(path2);
    System.out.println(relativePath);                            // ../Documents

    Path resolvedPath = path1.resolve(relativePath);
    System.out.println(resolvedPath);             // /home/fredrik/IdeaProjects/../Documents
    System.out.println(resolvedPath.normalize()); // /home/fredrik/Documents


    System.out.println("\n_Checking for file existence_");
    // Checking for file existence
    try {
      System.out.println(absDirPath1.toRealPath()); // /home/fredrik/IdeaProjects/OCP-plugg

      System.out.println(Paths.get("/vmlinuz").toRealPath()); // /boot/vmlinuz-5.0.0-29-generic
      System.out.println(Paths.get("/vmlinuz").toRealPath(LinkOption.NOFOLLOW_LINKS)); // /vmlinuz

      //note that it removes redundant path elements. It implicitly calls normalize()
      System.out.println(resolvedPath);              // /home/fredrik/IdeaProjects/../Documents
      System.out.println(resolvedPath.toRealPath()); // /home/fredrik/Documents

      //getting the current working directory
      System.out.println(Paths.get(".").toRealPath()); // /home/fredrik/IdeaProjects/OCP-plugg

      // a file that does not exist
      System.out.println(Paths.get("arne-banarne").toRealPath());
    } catch (IOException e) {
      // throws a NoSuchFileException if the file cannot be located
      e.printStackTrace();
    }


  }

}
