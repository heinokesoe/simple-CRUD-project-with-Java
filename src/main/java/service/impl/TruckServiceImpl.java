package service.impl;

import dao.TruckDao;
import model.Truck;
import model.VehicleType;
import service.TruckService;
import java.util.List;

public class TruckServiceImpl implements TruckService {
  private final TruckDao truckDao;

  public TruckServiceImpl() {
    this.truckDao = new TruckDao();
  }

  @Override
  public Truck add(Truck truck) {
    return truckDao.saveTruck(truck);
  }

  @Override
  public Boolean delete(int i) {
    return truckDao.deleteTruck(i);
  }

  @Override
  public Truck update(List<Object> list) {
    return truckDao.updateTruck(list);
  }

  @Override
  public Truck search(int i) {
    return truckDao.search(i);
  }

  @Override
  public List<Truck> list() {
    return truckDao.list();
  }

  @Override
  public List<VehicleType> listTruckTypes() {
    return truckDao.listTruckTypes();
  }
}
