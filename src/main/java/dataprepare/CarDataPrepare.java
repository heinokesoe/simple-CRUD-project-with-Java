package dataprepare;

import model.Car;
import model.VehicleType;
import service.impl.CarServiceImpl;
import static util.VehicleUtil.br;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CarDataPrepare {
  public Car prepareData() throws IOException {
    CarServiceImpl carService = new CarServiceImpl();
    List<VehicleType> vehicleTypes = carService.listCarTypes();
    Car car = (Car) VehicleDataPrepare.getCommonInput(new Car());
    System.out.print("Enter the color: ");
    car.setColor(br.readLine());
    System.out.println("Select the type of vehicle: ");
    for (int i = 0; i < vehicleTypes.size(); i++)
      System.out.println(i+6 + ": " + vehicleTypes.get(i).getName());
    car.setType(vehicleTypes.get(Integer.parseInt(br.readLine())-6));
    return car;
  }

  public int prepareDataDelete(List<Car> cars) throws IOException {
    System.out.println("There are " + cars.size() + " cars");
    for (Car car : cars) {
      System.out.println(car);
    }
    System.out.print("Which id would you like to delete? ");
    return Integer.parseInt(br.readLine());
  }

  public List<Object> prepareDataUpdate(List<Car> cars) throws IOException {
    System.out.println("There are " + cars.size() + " cars");
    for (Car value : cars) {
      System.out.println(value);
    }
    System.out.print("Which id would you like to update? ");
    int id = Integer.parseInt(br.readLine());
    Car car = prepareData();
    car.setId(id);
    return Arrays.asList(id, car);
  }

  public int prepareDataSearch(List<Car> cars) throws IOException {
    int[] ids = new int[cars.size()];
    for (int i = 0; i < cars.size(); i++) {
      ids[i] = cars.get(i).getId();
    }
    System.out.print("Which id would you like to search? " + Arrays.toString(ids) + " ");
    return Integer.parseInt(br.readLine());
  }
}
