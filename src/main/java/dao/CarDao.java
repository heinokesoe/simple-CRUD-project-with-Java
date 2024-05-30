package dao;

import model.Car;
import model.VehicleType;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarDao extends DatabaseOperation<Car, VehicleType>{
  static Connection con = DatabaseUtil.getConnection();
  List<VehicleType> types = listCarTypes();
  public Car saveCar(Car car) {
    Integer id = (Integer) save("INSERT INTO vehicles (name, model, noofwheels, color, type_id, category_id) VALUES (?,?,?,?,?,?) RETURNING id",
        car.getName(), car.getModel(), car.getNumOfWheel(), car.getColor(), car.getType().getId(), 2);
    if (id != null) {
      car.setId(id);
      return car;
    } else
      return null;
  }

  public Boolean deleteCar(int id) {
    return delete("DELETE FROM vehicles WHERE id = ?", id);
  }

  public Car updateCar(List<Object> list) {
    Car car = (Car) list.get(1);
    Boolean result = update("UPDATE vehicles SET name = ?, model = ?, noofwheels = ?, color = ?, type_id = ?, category_id = ? WHERE id = ?",
        car.getName(), car.getModel(), car.getNumOfWheel(), car.getColor(), car.getType().getId(), 2, list.get(0));
    if (result) {
      return car;
    } else
      return null;
  }

  public Car search(int i) {
    Car car = new Car();
    car = searchById(car, i);
    return car;
  }

  public List<Car> listCar() {
    return list();
  }

  public List<VehicleType> listCarTypes() {
    return listTypes();
  }

  @Override
  public Car setSearchResult(Car car,ResultSet rs) throws SQLException {
    car.setId(rs.getInt("id"));
    car.setName(rs.getString("name"));
    car.setModel(rs.getString("model"));
    car.setNumOfWheel(rs.getInt("noofwheels"));
    car.setColor(rs.getString("color"));
    VehicleType vehicleType = new VehicleType();
    vehicleType.setName(types.get(rs.getInt("type_id")-6).getName());
    car.setType(vehicleType);
    return car;
  }

  @Override
  public String listQuery() {
    return "SELECT * FROM vehicles WHERE category_id = 2";
  }

  @Override
  public void listLoop(ResultSet rs, List<Car> list) throws SQLException {
    while (rs.next()) {
      Car car = new Car();
      car.setId(rs.getInt("id"));
      car.setName(rs.getString("name"));
      car.setModel(rs.getString("model"));
      car.setNumOfWheel(rs.getInt("noofwheels"));
      car.setColor(rs.getString("color"));
      VehicleType vehicleType = new VehicleType();
      vehicleType.setName(types.get(rs.getInt("type_id")-6).getName());
      car.setType(vehicleType);
      list.add(car);
    }
  }

  @Override
  public String listTypeQuery() {
    return "SELECT * FROM types WHERE category_id = 2";
  }

  @Override
  public void listTypeLoop(ResultSet rs, List<VehicleType> types) throws SQLException {
    while (rs.next()) {
      VehicleType vehicleType = new VehicleType();
      vehicleType.setId(rs.getInt("id"));
      vehicleType.setName(rs.getString("name"));
      vehicleType.setDescription(rs.getString("description"));
      types.add(vehicleType);
    }
  }
}
