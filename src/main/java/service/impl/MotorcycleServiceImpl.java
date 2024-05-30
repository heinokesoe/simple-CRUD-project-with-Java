package service.impl;

import dao.MotorcycleDao;
import model.Motorcycle;
import model.VehicleType;
import service.MotorcycleService;
import java.util.List;

public class MotorcycleServiceImpl implements MotorcycleService {
  private final MotorcycleDao motorcycleDao;

  public MotorcycleServiceImpl() {
    this.motorcycleDao = new MotorcycleDao();
  }

  @Override
  public Motorcycle add(Motorcycle motorcycle) {
    return motorcycleDao.saveMotorcycle(motorcycle);
  }

  @Override
  public Boolean delete(int i) {
    return motorcycleDao.deleteMotorcycle(i);
  }

  @Override
  public Motorcycle update(List<Object> list) {
    return motorcycleDao.updateMotorcycle(list);
  }

  @Override
  public Motorcycle search(int i) {
    return motorcycleDao.search(i);
  }

  @Override
  public List<Motorcycle> list() {
    return motorcycleDao.list();
  }

  @Override
  public List<VehicleType> listMotorcycleTypes() {
    return motorcycleDao.listMotorcycleTypes();
  }
}
