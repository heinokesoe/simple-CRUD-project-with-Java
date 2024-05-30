package controller;

import dataprepare.CarDataPrepare;
import model.Car;
import service.impl.CarServiceImpl;
import java.io.IOException;
import java.util.List;
import static util.VehicleUtil.br;

public class CarController {
  Car car;
  CarServiceImpl carService;
  CarDataPrepare carDataPrepare;
  public CarController() {
    this.car = new Car();
    this.carService = new CarServiceImpl();
    this.carDataPrepare = new CarDataPrepare();
  }
  public void operateCar() throws IOException {
    List<Car> list = carService.list();
    int size = list.size();
    System.out.println("What would you like to do?" +
        "\n(1) Add" +
        "\n(2) Delete" +
        "\n(3) Update" +
        "\n(4) Search" +
        "\n(5) List");
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1:
        car = carService.add(carDataPrepare.prepareData());
        System.out.println(car);
        break;
      case 2:
        if (size > 0) {
          Boolean result = carService.delete(carDataPrepare.prepareDataDelete(list));
          if (result)
            System.out.println("Deleted successfully");
          else
            System.out.println("Failed to delete");
        } else
          System.out.println("There are no cars");
        break;
      case 3:
        if (size > 0) {
          car = carService.update(carDataPrepare.prepareDataUpdate(list));
          System.out.println(car);
        } else
          System.out.println("There are no cars");
        break;
      case 4:
        if (size > 0) {
          car = carService.search(carDataPrepare.prepareDataSearch(list));
          System.out.println(car);
        } else
          System.out.println("There are no cars");
        break;
      case 5:
        if (size > 0) {
          for (Car c : list)
            System.out.println(c);
        } else
          System.out.println("There are no cars");
        break;
    }
  }
}
