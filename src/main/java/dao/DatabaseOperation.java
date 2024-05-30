package dao;

import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseOperation<GenericType,TypeList> {
  static Connection con = DatabaseUtil.getConnection();
  public Object save(String query,Object ...values) {
    int id = 1;
    try (PreparedStatement ps = con.prepareStatement(query)) {
      for (Object object : values) {
        ps.setObject(id++, object);
      }
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          id = rs.getInt("id");
        }
      }
      return id;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean delete(String query, int id) {
    try (PreparedStatement ps = con.prepareStatement(query)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean update(String query,Object ...values) {
    int id = 1;
    try (PreparedStatement ps = con.prepareStatement(query)) {
      for (Object object : values) {
        ps.setObject(id++, object);
      }
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public GenericType searchById(GenericType  genericType,int id) {
    String sql = "SELECT * FROM vehicles WHERE id = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          setSearchResult(genericType, rs);
        }
      }
      return genericType;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public abstract GenericType setSearchResult(GenericType genericType,ResultSet rs) throws SQLException;

  public List<GenericType> list() {
    List<GenericType> list = new ArrayList<>();
    try (PreparedStatement ps = con.prepareStatement(listQuery());
         ResultSet rs = ps.executeQuery()) {
      listLoop(rs, list);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  public List<TypeList> listTypes() {
    List<TypeList> types = new ArrayList<>();
    try (PreparedStatement ps = con.prepareStatement(listTypeQuery());
         ResultSet rs = ps.executeQuery()) {
      listTypeLoop(rs, types);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return types;
  }

  public abstract String listQuery();
  public abstract void listLoop(ResultSet rs, List<GenericType> list) throws SQLException;
  public abstract String listTypeQuery();
  public abstract void listTypeLoop(ResultSet rs, List<TypeList> types) throws SQLException;
}
