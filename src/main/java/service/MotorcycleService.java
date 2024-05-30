package service;

import model.Motorcycle;
import model.VehicleType;
import java.util.List;

public interface MotorcycleService {
  Motorcycle add(Motorcycle motorcycle);
  Boolean delete(int i);
  Motorcycle update(List<Object> list);
  Motorcycle search(int i);
  List<Motorcycle> list();
  List<VehicleType> listMotorcycleTypes();
}
