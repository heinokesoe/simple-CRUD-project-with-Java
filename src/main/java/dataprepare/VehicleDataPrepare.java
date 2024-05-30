package dataprepare;

import model.*;
import service.impl.CarServiceImpl;
import service.impl.MotorcycleServiceImpl;
import service.impl.TruckServiceImpl;
import java.io.IOException;
import java.util.List;
import static util.VehicleUtil.br;

public class VehicleDataPrepare {
  public void showAllVehicles() {
    CarServiceImpl carService = new CarServiceImpl();
    MotorcycleServiceImpl motorcycleService = new MotorcycleServiceImpl();
    TruckServiceImpl truckService = new TruckServiceImpl();
    List<Car> cars = carService.list();
    List<Motorcycle> motorcycles = motorcycleService.list();
    List<Truck> trucks = truckService.list();
    System.out.println("\nMotorcycles: ");
    for (Motorcycle motorcycle : motorcycles) {
      System.out.println(motorcycle);
    }
    System.out.println("\nCars: ");
    for (Car car : cars) {
      System.out.println(car);
    }
    System.out.println("\nTrucks: ");
    for (Truck truck : trucks) {
      System.out.println(truck);
    }
  }

  public static Vehicle getCommonInput(Vehicle vehicle) throws IOException {
    System.out.print("Enter the name: ");
    vehicle.setName(br.readLine());
    System.out.print("Enter the model: ");
    vehicle.setModel(br.readLine());
    System.out.print("Enter the number of wheel: ");
    vehicle.setNumOfWheel(Integer.parseInt(br.readLine()));
    return vehicle;
  }
}
