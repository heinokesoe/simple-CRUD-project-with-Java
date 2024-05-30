package dao;

import model.Motorcycle;
import model.VehicleType;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MotorcycleDao extends DatabaseOperation<Motorcycle,VehicleType> {
  static Connection con = DatabaseUtil.getConnection();
  List<VehicleType> types = listMotorcycleTypes();
  public Motorcycle saveMotorcycle(Motorcycle motorcycle) {
    Integer id = (Integer) save("INSERT INTO vehicles (name, model, noofwheels, license, type_id, category_id) VALUES (?,?,?,?,?,?) RETURNING id",
        motorcycle.getName(), motorcycle.getModel(), motorcycle.getNumOfWheel(), motorcycle.getLicense(), motorcycle.getType().getId(), 1);
    if (id != null) {
      motorcycle.setId(id);
      return motorcycle;
    } else
      return null;
  }

  public Boolean deleteMotorcycle(int id) {
    return delete("DELETE FROM vehicles WHERE id = ?", id);
  }

  public Motorcycle updateMotorcycle(List<Object> list) {
    Motorcycle motorcycle = (Motorcycle) list.get(1);
    Boolean result = update("UPDATE vehicles SET name = ?, model = ?, noofwheels = ?, license = ?, type_id = ?, category_id = ? WHERE id = ?",
        motorcycle.getName(), motorcycle.getModel(), motorcycle.getNumOfWheel(), motorcycle.getLicense(), motorcycle.getType().getId(), 1, list.get(0));
    if (result)
      return motorcycle;
    else
      return null;
  }

  public Motorcycle search(int i) {
    Motorcycle motorcycle = new Motorcycle();
    motorcycle = searchById(motorcycle, i);
    return motorcycle;
  }

  public List<Motorcycle> listMotorcycles() {
    List<Motorcycle> motorcycles = list();
    return motorcycles;
  }

  public List<VehicleType> listMotorcycleTypes() {
    List<VehicleType> motorcycleTypes = listTypes();
    return motorcycleTypes;
  }

  @Override
  public Motorcycle setSearchResult(Motorcycle motorcycle, ResultSet rs) throws SQLException {
    motorcycle.setId(rs.getInt("id"));
    motorcycle.setName(rs.getString("name"));
    motorcycle.setModel(rs.getString("model"));
    motorcycle.setNumOfWheel(rs.getInt("noofwheels"));
    motorcycle.setLicense(rs.getString("license"));
    VehicleType vehicleType = new VehicleType();
    vehicleType.setName(types.get(rs.getInt("type_id")-1).getName());
    motorcycle.setType(vehicleType);
    return motorcycle;
  }

  @Override
  public String listQuery() {
    return "SELECT * FROM vehicles WHERE category_id = 1";
  }

  @Override
  public void listLoop(ResultSet rs, List<Motorcycle> list) throws SQLException {
    while (rs.next()) {
      Motorcycle motorcycle = new Motorcycle();
      motorcycle.setId(rs.getInt("id"));
      motorcycle.setName(rs.getString("name"));
      motorcycle.setModel(rs.getString("model"));
      motorcycle.setNumOfWheel(rs.getInt("noofwheels"));
      motorcycle.setLicense(rs.getString("license"));
      VehicleType vehicleType = new VehicleType();
      vehicleType.setName(types.get(rs.getInt("type_id")-1).getName());
      motorcycle.setType(vehicleType);
      list.add(motorcycle);
    }
  }

  @Override
  public String listTypeQuery() {
    return "SELECT * FROM types WHERE category_id = 1";
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
