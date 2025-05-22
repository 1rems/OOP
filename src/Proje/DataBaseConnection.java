package Proje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {  //Veri tabanı bağlantısı
    private static final String URL = "jdbc:sqlite:QuestHero.db"; 
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Veritabanı bağlantısı başarılı.");
            }
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantısı başarısız: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Veritabanı bağlantısı kapandı.");
            } catch (SQLException e) {
                System.out.println("Bağlantı kapatılamadı: " + e.getMessage());
            }
        }
    }
}



