package Proje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Achievements implements Reward{
	private String achievementTitle;
	
	public String getAchievementTitle() {
		return achievementTitle;
	}
	public void setAchievementTitle(String achievementTitle) {
		this.achievementTitle = achievementTitle;
	}
	public Achievements(String achievementTitle) {
		this.achievementTitle=achievementTitle;
	}
	public void getsReward(User user) {
		user.addAchievements(achievementTitle);
		System.out.println("Yeni başarım açtınız : "+getAchievementTitle());
		saveToDatabase(user.getUserID());
	}
	public void saveToDatabase(int userID) {
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
}
