package model;

public class Truck extends Vehicle {

  @Override
  public String toString() {
    return "{id: " + getId() + ", name: " + getName() + ", model: " + getModel() + ", NoOfWheel: " + getNumOfWheel() + ", type: " + getType().getName() + "}";
  }
}
