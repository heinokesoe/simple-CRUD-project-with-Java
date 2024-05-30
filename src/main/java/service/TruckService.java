package service;

import model.Truck;
import model.VehicleType;
import java.util.List;

public interface TruckService {
  Truck add(Truck truck);
  Boolean delete(int i);
  Truck update(List<Object> list);
  Truck search(int i);
  List<Truck> list();
  List<VehicleType> listTruckTypes();
}
