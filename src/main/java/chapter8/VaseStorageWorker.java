package kap8;

import java.io.*;

public class VaseStorageWorker {

  public static void main(String[] args) throws IOException {
    Vase vase = new Vase(15, 35, "Waynes-vas");

    File vaseFile = new File("vaseFile.txt");

    try (
        ObjectOutputStream out =
            new ObjectOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(vaseFile)))) { //kastar FileNotFoundException
      out.writeObject(vase); //kastar IOException
    }

    try (ObjectInputStream in =
        new ObjectInputStream(
            new BufferedInputStream(
                new FileInputStream(vaseFile)))) { //kastar FileNotFoundException
      while (true) {
        Object object = in.readObject(); //kastar IOException
        if (object instanceof Vase) {
          System.out.println((Vase) object);
        }
      }
    } catch (EOFException e) {
      //
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}
