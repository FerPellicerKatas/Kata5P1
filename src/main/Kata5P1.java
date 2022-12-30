package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Kata5P1 {

    public static void main(String[] args) throws IOException, SQLException {
        Kata5P1 app = new Kata5P1();
        app.selectAll();
    }
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t" +
                                       rs.getString("Name") + "\t" +
                                       rs.getString("Apellidos") + "\t" +
                                       rs.getString("Departamento") + "\t");
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}