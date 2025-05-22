package Proje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Achievements{  //"Başarımlar"ın backend kısmı ve veri tabanı ile bağlantısı
	

	private String achievementTitle;
	private boolean achieved;
    private LocalDate date;
  
	public Achievements(String title, boolean achieved, LocalDate date) {
        this.achievementTitle = title;
        this.achieved = achieved;
        this.date = date;
    }

	public void saveToDatabase(int userID) {  //Veri Tabanı bağlantısı
        String sql = "INSERT INTO achievements (userID, achievementTitle) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, achievementTitle);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public boolean isAchieved() {
		return achieved;
	}
	public void setAchieved(boolean achieved) {
		this.achieved = achieved;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAchievementTitle() {
		return achievementTitle;
	}
	public void setAchievementTitle(String achievementTitle) {
		this.achievementTitle = achievementTitle;
	}
}
