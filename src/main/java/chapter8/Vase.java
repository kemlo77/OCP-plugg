package chapter8;


import java.io.Serializable;

public class Vase implements Serializable {


  public static final long serialVersionUID = 2L;
  private int diameter;
  private int height;
  private transient String description;

  public Vase(int diameter, int height, String description) {
    this.diameter = diameter;
    this.height = height;
    this.description = description;
  }

  public int getDiameter() {
    return diameter;
  }

  public int getHeight() {
    return height;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Vase{" +
        "diameter=" + diameter +
        ", height=" + height +
        ", description='" + description + '\'' +
        '}';
  }
}
