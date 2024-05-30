package model;

public class Car extends Vehicle {
  private String color;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "{id: " + getId() + ", name: " + getName() + ", model: " + getModel() + ", NoOfWheel: " + getNumOfWheel() + ", color: " + color + ", type: " + getType().getName() + "}";
  }
}
