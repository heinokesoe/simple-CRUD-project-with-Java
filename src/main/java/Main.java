import controller.CarController;
import controller.MotorcycleController;
import controller.TruckController;
import dataprepare.VehicleDataPrepare;
import util.DatabaseUtil;
import java.io.IOException;
import static util.VehicleUtil.br;

public class Main {
  public static void main(String[] args) throws IOException {
    DatabaseUtil.initDatabase();
    int vehicleType = 0;
    MotorcycleController motorcycleController = new MotorcycleController();
    CarController carController = new CarController();
    TruckController truckController = new TruckController();
    VehicleDataPrepare vehicleDataPrepare = new VehicleDataPrepare();
    while (vehicleType != 5) {
      System.out.println("\nChoose the type of vehicle: " +
          "\n(1) Motorcycle" +
          "\n(2) Car" +
          "\n(3) Truck" +
          "\n(4) Show All Vehicles" +
          "\n(5) Exit");
      vehicleType = Integer.parseInt(br.readLine());
      switch (vehicleType) {
        case 1:
          motorcycleController.operateMotorcycle();
          break;
        case 2:
          carController.operateCar();
          break;
        case 3:
          truckController.operateTruck();
          break;
        case 4:
          vehicleDataPrepare.showAllVehicles();
          break;
        case 5: break;
      }
    }
  }
}
