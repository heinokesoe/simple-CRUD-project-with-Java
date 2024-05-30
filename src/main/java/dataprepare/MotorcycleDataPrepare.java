package dataprepare;

import model.Motorcycle;
import model.VehicleType;
import service.impl.MotorcycleServiceImpl;
import static util.VehicleUtil.br;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MotorcycleDataPrepare {
  public Motorcycle prepareData() throws IOException {
    MotorcycleServiceImpl motorcycleService = new MotorcycleServiceImpl();
    List<VehicleType> vehicleTypes = motorcycleService.listMotorcycleTypes();
    Motorcycle motorcycle = (Motorcycle) VehicleDataPrepare.getCommonInput(new Motorcycle());
    System.out.print("Enter the license: ");
    motorcycle.setLicense(br.readLine());
    System.out.println("Select the type of vehicle: ");
    for (int i = 0; i < vehicleTypes.size(); i++)
      System.out.println(i+1 + ": " + vehicleTypes.get(i).getName());
    motorcycle.setType(vehicleTypes.get(Integer.parseInt(br.readLine())-1));
    return motorcycle;
  }

  public int prepareDataDelete(List<Motorcycle> motorcycles) throws IOException {
    System.out.println("There are " + motorcycles.size() + " motorcycles");
    for (int i = 0; i < motorcycles.size(); i++) {
      System.out.println(motorcycles.get(i));
    }
    System.out.print("Which id would you like to delete? ");
    return Integer.parseInt(br.readLine());
  }

  public List<Object> prepareDataUpdate(List<Motorcycle> motorcycles) throws IOException {
    System.out.println("There are " + motorcycles.size() + " motorcycles");
    for (int i = 0; i < motorcycles.size(); i++) {
      System.out.println(motorcycles.get(i));
    }
    System.out.print("Which id would you like to update? ");
    int id = Integer.parseInt(br.readLine());
    Motorcycle motorcycle = prepareData();
    motorcycle.setId(id);
    return Arrays.asList(id, motorcycle);
  }

  public int prepareDataSearch(List<Motorcycle> motorcycles) throws IOException {
    int[] ids = new int[motorcycles.size()];
    for (int i = 0; i < motorcycles.size(); i++) {
      ids[i] = motorcycles.get(i).getId();
    }
    System.out.print("Which id would you like to search? " + Arrays.toString(ids) + " ");
    return Integer.parseInt(br.readLine());
  }
}
