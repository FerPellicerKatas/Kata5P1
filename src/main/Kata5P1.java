package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static main.MailListReader.read;

public class Kata5P1 {
    
    public static void main(String[] args) throws IOException, SQLException {
        //Kata5P1 app = new Kata5P1();
        //app.selectAll();
        createNewTable();
        List<String> mailList = read("C:/Users/ferna/Documents/NetBeansProjects/Kata5P1/email.txt");
        Kata5P1 idt = new Kata5P1();
        
        for (String mail: mailList){
            idt.insert(mail);
        }
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
    
    public static void createNewTable() {
        String url = "jdbc:sqlite:KATA5.db";
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                      + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                      + " Mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Método para insertar datos en la tabla direcc_email
    public void insert(String email) {
        String sql = "INSERT INTO email(mail) VALUES(?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}