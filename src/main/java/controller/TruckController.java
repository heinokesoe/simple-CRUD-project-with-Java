package controller;

import dataprepare.TruckDataPrepare;
import model.Truck;
import service.impl.TruckServiceImpl;
import java.io.IOException;
import java.util.List;
import static util.VehicleUtil.br;

public class TruckController {
  Truck truck;
  TruckServiceImpl truckService;
  TruckDataPrepare truckDataPrepare;
  public TruckController() {
    this.truck = new Truck();
    this.truckService = new TruckServiceImpl();
    this.truckDataPrepare = new TruckDataPrepare();
  }
  public void operateTruck() throws IOException {
    List<Truck> list = truckService.list();
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
        truck = truckService.add(truckDataPrepare.prepareData());
        System.out.println(truck);
        break;
      case 2:
        if (size > 0) {
          Boolean result = truckService.delete(truckDataPrepare.prepareDataDelete(list));
          if (result)
            System.out.println("Deleted successfully");
          else
            System.out.println("Failed to delete");
        } else
          System.out.println("There are no trucks");
        break;
      case 3:
        if (size > 0) {
          truck = truckService.update(truckDataPrepare.prepareDataUpdate(list));
          System.out.println(truck);
        } else
          System.out.println("There are no trucks");
        break;
      case 4:
        if (size > 0) {
          truck = truckService.search(truckDataPrepare.prepareDataSearch(list));
          System.out.println(truck);
        } else
          System.out.println("There are no trucks");
        break;
      case 5:
        if (size > 0)
          for (Truck t : list)
            System.out.println(t);
        else
          System.out.println("There are no trucks");
        break;
    }
  }
}
