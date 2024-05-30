package model;

public class Motorcycle extends Vehicle {
  private String license;

  public String getLicense() {
    return license;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  @Override
  public String toString() {
    return "{id: " + getId() + ", name: " + getName() + ", model: " + getModel() + ", NoOfWheel: " + getNumOfWheel() + ", license: " + license + ", type: " + getType().getName() + "}";
  }
}
