package dataprepare;

import model.Truck;
import model.VehicleType;
import service.impl.TruckServiceImpl;
import static util.VehicleUtil.br;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TruckDataPrepare {
  public Truck prepareData() throws IOException {
    TruckServiceImpl truckService = new TruckServiceImpl();
    List<VehicleType> vehicleTypes = truckService.listTruckTypes();
    Truck truck = (Truck) VehicleDataPrepare.getCommonInput(new Truck());
    System.out.println("Select the type of vehicle: ");
    for (int i = 0; i < vehicleTypes.size(); i++)
      System.out.println(i+11 + ": " + vehicleTypes.get(i).getName());
    truck.setType(vehicleTypes.get(Integer.parseInt(br.readLine())-11));
    return truck;
  }

  public int prepareDataDelete(List<Truck> trucks) throws IOException {
    System.out.println("There are " + trucks.size() + " trucks");
    for (Truck truck : trucks) {
      System.out.println(truck);
    }
    System.out.print("Which id would you like to delete? ");
    return Integer.parseInt(br.readLine());
  }

  public List<Object> prepareDataUpdate(List<Truck> trucks) throws IOException {
    System.out.println("There are " + trucks.size() + " trucks");
    for (Truck value : trucks) {
      System.out.println(value);
    }
    System.out.print("Which id would you like to update? ");
    int id = Integer.parseInt(br.readLine());
    Truck truck = prepareData();
    truck.setId(id);
    return Arrays.asList(id, truck);
  }

  public int prepareDataSearch(List<Truck> trucks) throws IOException {
    int[] ids = new int[trucks.size()];
    for (int i = 0; i < trucks.size(); i++) {
      ids[i] = trucks.get(i).getId();
    }
    System.out.print("Which id would you like to search? " + Arrays.toString(ids) + " ");
    return Integer.parseInt(br.readLine());
  }
}
