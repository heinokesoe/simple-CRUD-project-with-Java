package service.impl;

import dao.CarDao;
import model.Car;
import model.VehicleType;
import service.CarService;
import java.util.List;

public class CarServiceImpl implements CarService {
  private final CarDao carDao;

  public CarServiceImpl() {
    this.carDao = new CarDao();
  }
  @Override
  public Car add(Car car) {
    return carDao.saveCar(car);
  }

  @Override
  public Boolean delete(int i) {
    return carDao.deleteCar(i);
  }

  @Override
  public Car update(List<Object> list) {
    return carDao.updateCar(list);
  }

  @Override
  public Car search(int i) {
    return carDao.search(i);
  }

  @Override
  public List<Car> list() {
    return carDao.list();
  }

  @Override
  public List<VehicleType> listCarTypes() {
    return carDao.listCarTypes();
  }
}
