package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Veritabanına bağlanma
            String url = "jdbc:mysql://localhost:3306/veritabani_adi"; // Bağlanmak istediğiniz veritabanı adı
            String user = "kullanici_adiniz"; // Veritabanı kullanıcı adı
            String password = "parolaniz"; // Veritabanı şifresi

            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Veritabanı bağlantısı sağlanamadı.");
        }
    }
}
