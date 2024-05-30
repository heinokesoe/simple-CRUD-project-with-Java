package model;

public class Vehicle {
  private int id;
  private String name;
  private String model;
  private int numOfWheel;
  private VehicleType type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getNumOfWheel() {
    return numOfWheel;
  }

  public void setNumOfWheel(int numOfWheel) {
    this.numOfWheel = numOfWheel;
  }

  public VehicleType getType() {
    return type;
  }

  public void setType(VehicleType type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
