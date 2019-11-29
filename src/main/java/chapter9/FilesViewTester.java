package chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FilesViewTester {

  public static void main(String[] args) {

    Path relDirPath = Paths.get("src/main/");
    Path absDirPath = Paths.get("/home/fredrik/Documents");

    Path relFilePath = Paths.get("../OCP-plugg/README.md");
    Path absFilePath = Paths.get("/home/fredrik/IdeaProjects/OCP-plugg/README.md");

    Path symbolicLinkDir = Paths.get("/var/lock");
    Path symbolicLinkFile = Paths.get("/initrd.img");

    System.out.println("\nReading attributes");
    try {
      BasicFileAttributes data = Files.readAttributes(absFilePath,BasicFileAttributes.class);
      System.out.println(data.isDirectory());      // false
      System.out.println(data.isRegularFile());    // true
      System.out.println(data.isSymbolicLink());   // false
      System.out.println(data.isOther());          // false (not file/directory/symlink)
      System.out.println(data.size());             // 63
      System.out.println(data.creationTime());     // 2019-08-31T20:03:50Z (FileTime)
      System.out.println(data.lastModifiedTime()); // 2019-08-31T20:03:50Z (FileTime)
      System.out.println(data.lastAccessTime());   // 2019-11-10T15:16:08Z (FileTime)
      System.out.println(data.fileKey());          // (dev=802,ino=10093498) (unique file identifier)

    } catch (IOException e) {
      e.printStackTrace();
    }

    BasicFileAttributeView view = Files.getFileAttributeView(absFilePath,BasicFileAttributeView.class);

    try {
      BasicFileAttributes data = view.readAttributes();
      System.out.println(data.lastModifiedTime());
      FileTime currentTime = FileTime.fromMillis(System.currentTimeMillis());
      //setTimes(lastModifiedTime, lastAccessTime, createTime)
      view.setTimes(currentTime,null,null);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
