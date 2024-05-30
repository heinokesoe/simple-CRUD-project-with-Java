package service;

import model.Car;
import model.VehicleType;
import java.util.List;

public interface CarService {
  Car add(Car car);
  Boolean delete(int i);
  Car update(List<Object> list);
  Car search(int i);
  List<Car> list();
  List<VehicleType> listCarTypes();
}
