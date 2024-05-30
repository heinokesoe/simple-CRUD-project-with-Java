package dao;

import model.Truck;
import model.VehicleType;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TruckDao extends DatabaseOperation<Truck, VehicleType> {
  static Connection con = DatabaseUtil.getConnection();
  List<VehicleType> types = listTruckTypes();
  public Truck saveTruck(Truck truck) {
    Integer id = (Integer) save("INSERT INTO vehicles (name, model, noofwheels, type_id, category_id) VALUES (?,?,?,?,?) RETURNING id",
        truck.getName(), truck.getModel(), truck.getNumOfWheel(), truck.getType().getId(), 3);
    if (id != null) {
      truck.setId(id);
      return truck;
    } else
      return null;
  }

  public Boolean deleteTruck(int id) {
    return delete("DELETE FROM vehicles WHERE id = ?", id);
  }

  public Truck updateTruck(List<Object> list) {
    Truck truck = (Truck) list.get(1);
    Boolean result = update("UPDATE vehicles SET name = ?, model = ?, noofwheels = ?, type_id = ?, category_id = ? WHERE id = ?",
        truck.getName(), truck.getModel(), truck.getNumOfWheel(), truck.getType().getId(), 3, list.get(0));
    if (result)
      return truck;
    else
      return null;
  }

  public Truck search(int i) {
    Truck truck = new Truck();
    truck = searchById(truck, i);
    return truck;
  }

  public List<Truck> listTruck() {
    List<Truck> trucks = list();
    return trucks;
  }

  public List<VehicleType> listTruckTypes() {
    List<VehicleType> truckTypes = listTypes();
    return truckTypes;
  }

  @Override
  public Truck setSearchResult(Truck truck, ResultSet rs) throws SQLException {
    truck.setId(rs.getInt("id"));
    truck.setName(rs.getString("name"));
    truck.setModel(rs.getString("model"));
    truck.setNumOfWheel(rs.getInt("noofwheels"));
    VehicleType vehicleType = new VehicleType();
    vehicleType.setName(types.get(rs.getInt("type_id")-11).getName());
    truck.setType(vehicleType);
    return truck;
  }

  @Override
  public String listQuery() {
    return "SELECT * FROM vehicles WHERE category_id = 3";
  }

  @Override
  public void listLoop(ResultSet rs, List<Truck> list) throws SQLException {
    while (rs.next()) {
      Truck truck = new Truck();
      truck.setId(rs.getInt("id"));
      truck.setName(rs.getString("name"));
      truck.setModel(rs.getString("model"));
      truck.setNumOfWheel(rs.getInt("noofwheels"));
      VehicleType vehicleType = new VehicleType();
      vehicleType.setName(types.get(rs.getInt("type_id")-11).getName());
      truck.setType(vehicleType);
      list.add(truck);
    }
  }

  @Override
  public String listTypeQuery() {
    return "SELECT * FROM types WHERE category_id = 3";
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
