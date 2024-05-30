package controller;

import dataprepare.MotorcycleDataPrepare;
import model.Motorcycle;
import service.impl.MotorcycleServiceImpl;
import java.io.IOException;
import java.util.List;
import static util.VehicleUtil.br;

public class MotorcycleController {
  Motorcycle motorcycle;
  MotorcycleServiceImpl motorcycleService;
  MotorcycleDataPrepare motorcycleDataPrepare;
  public MotorcycleController() {
    this.motorcycle = new Motorcycle();
    this.motorcycleService = new MotorcycleServiceImpl();
    this.motorcycleDataPrepare = new MotorcycleDataPrepare();
  }
  public void operateMotorcycle() throws IOException {
    List<Motorcycle> list = motorcycleService.list();
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
        motorcycle = motorcycleService.add(motorcycleDataPrepare.prepareData());
        System.out.println(motorcycle);
        break;
      case 2:
        if (size > 0) {
          Boolean result = motorcycleService.delete(motorcycleDataPrepare.prepareDataDelete(list));
          if (result)
            System.out.println("Successfully deleted");
          else
            System.out.println("Failed to delete");
        } else
          System.out.println("There are no motorcycles");
        break;
      case 3:
        if (size > 0) {
          motorcycle = motorcycleService.update(motorcycleDataPrepare.prepareDataUpdate(list));
          System.out.println(motorcycle);
        } else
          System.out.println("There are no motorcycles");
        break;
      case 4:
        if (size > 0) {
          motorcycle = motorcycleService.search(motorcycleDataPrepare.prepareDataSearch(list));
          System.out.println(motorcycle);
        } else
          System.out.println("There are no motorcycles");
        break;
      case 5:
        if (size > 0)
          for (Motorcycle m : list)
            System.out.println(m);
        else
          System.out.println("There are no motorcycles");
        break;
    }
  }

}
