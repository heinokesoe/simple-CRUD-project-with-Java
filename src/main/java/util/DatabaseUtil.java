package util;

import java.sql.*;

public class DatabaseUtil {
  private static final String URL = "jdbc:postgresql://localhost:5432/db";
  private static final String USER = "user";
  private static final String PASSWORD = "password";
  public static Connection getConnection() {
    Connection con;
    try {
      con = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return con;
  }

  public static void initDatabase() {
    String categorySQL = "CREATE TABLE categories(id serial PRIMARY KEY, name VARCHAR(50), code int)";
    String typeSQL = "CREATE TABLE types(id serial PRIMARY KEY, name VARCHAR(50), description VARCHAR(255), code INT, category_id INT, FOREIGN KEY (category_id) REFERENCES categories(id))";
    String vehicleSQL = "CREATE TABLE vehicles(id serial PRIMARY KEY, name VARCHAR(50), model VARCHAR(50), noofwheels INT, color VARCHAR(50), license VARCHAR(50), type_id INT, category_id INT, FOREIGN KEY (type_id) REFERENCES types(id), FOREIGN KEY (category_id) REFERENCES categories(id))";

    String[] categoryInserts = {
        "INSERT INTO categories(name, code) VALUES ('Motorcycle', 2001)",
        "INSERT INTO categories(name, code) VALUES ('Car', 2002)",
        "INSERT INTO categories(name, code) VALUES ('Truck', 2003)"
    };

    String[] typeInserts = {
        "INSERT INTO types(name, description, code, category_id) VALUES ('SportMotorcycle', 'Built for speed, agility, and performance', 1001, 1)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('Scooter', 'Small, lightweight bikes with a step-through frame and automatic transmission', 1002, 1)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('StandardMotorcycle', 'These are versatile and straightforward bikes designed for general use', 1003, 1)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('DirtBike', 'Specifically designed for off-road use with lightweight construction, knobby tires, and long suspension travel', 1004, 1)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('ElectricMotorcycle', 'Powered by electric motors instead of internal combustion engines', 1005, 1)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('SportCar', 'This car type is for diverse travelling and demade', 1006, 2)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('Sedan', 'This is a passenger car in a three-box configuration with separate compartments for the engine, passengers, and cargo', 1007, 2)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('SUV', 'SUVs are larger vehicles designed for more passengers and cargo', 1008, 2)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('Minivan', 'Minivans are designed for maximum passenger and cargo space', 1009, 2)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('ElectricCar', 'Powered entirely by electricity, offering zero emissions and often advanced technology features', 1010, 2)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('PickupTruck', 'Versatile vehicles with an open cargo bed at the back', 1011, 3)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('BoxTruck', 'Feature a large, enclosed cargo area mounted on a chassis', 1012, 3)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('TowTruck', 'Used for towing and recovering disabled vehicles', 1013, 3)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('TankerTruck', 'Designed to transport liquids or gases', 1014, 3)",
        "INSERT INTO types(name, description, code, category_id) VALUES ('GarbageTruck', 'Specifically designed for waste collection and disposal', 1015, 3)"
    };

    try (Connection con = getConnection()) {
      DatabaseMetaData dbm = con.getMetaData();
      ResultSet tables = dbm.getTables(null, null, "vehicles", null);

      if (!tables.next()) {
        try (Statement stmt = con.createStatement()) {
          stmt.execute(categorySQL);
          stmt.execute(typeSQL);
          stmt.execute(vehicleSQL);
        }

        try (Statement stmt = con.createStatement()) {
          for (String categoryInsert : categoryInserts) {
            stmt.addBatch(categoryInsert);
          }
          stmt.executeBatch();
        }

        try (Statement stmt = con.createStatement()) {
          for (String typeInsert : typeInserts) {
            stmt.addBatch(typeInsert);
          }
          stmt.executeBatch();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
