package Proje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private int userID;
	private int point;
	private int level;
	public List<Task> completedTasks = new ArrayList<>();
	public List<Task> missedTasks = new ArrayList<>();
	private List<String> achievements;

	
	public User(String name,int userID) {
        this.userName = name;
        this.userID=userID;
        this.achievements = new ArrayList<>();
        
    } 
	public static User loadFromDatabase(String name, int userID) {
	    // 1) DB’den o userID’ye ait satırı çek
	    String sql = "SELECT username, point, level FROM users WHERE userID = ?";
	    try (Connection conn = DataBaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, userID);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            // A) Kayıt varsa: bu bilgileri al, yeni User nesnesini güncelle
	            User user = new User(name, userID);
	            user.setPoint( rs.getInt("point") );
	            user.setLevel( rs.getInt("level") );
	            return user;
	        } else {
	            // B) Kayıt yoksa: yeni kullanıcı, DB’ye ekle, puan/level 0/1
	            User user = new User(name, userID);
	            user.saveToDatabase();   // bunu sadece ilk kayıt için bırakmıştık
	            return user;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Hata olursa bile bir User döndür
	        return new User(name, userID);
	    }
	}

        
        public void saveToDatabase() {
            String sql = "INSERT INTO users (username, userID, point, level) VALUES (?, ?, ?, ?)";
            try (Connection conn = DataBaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, userName);
                pstmt.setInt(2, userID);
                pstmt.setInt(3, point);
                pstmt.setInt(4, level);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        
    } 
      
      
	public void completeTask(Task task) {
		task.completeTask();
        completedTasks.add(task);
        point +=task.getPoints();
        levelUp();
        
        String sql = "UPDATE users SET point = ?, level = ? WHERE userID = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, point);
            pstmt.setInt(2, level);
            pstmt.setInt(3, userID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void levelUp() {
		if(point>=level*100) {
			level++;
		}
	}
    public void missTask(Task task) {
        missedTasks.add(task);
        point -=task.getPoints();
        
        String sql = "UPDATE users SET point = ?, level = ? WHERE userID = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, point);
            pstmt.setInt(2, level);
            pstmt.setInt(3, userID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTask(Task task) {
        if (completedTasks.contains(task)) {
            completedTasks.remove(task);
        } 
        else if(missedTasks.contains(task)) {
            missedTasks.remove(task);
        }
        else {
            System.out.println("Görev bulunamadı.");
        }
    }
    public void addAchievements(String achievement) {
        achievements.add(achievement);
    }
    
    public String toString() {
        return userName + " - " +userID + " - "+level + " .lv  "+"point:"+point +",("+(level*100-point) + " points for next level)";
    }
    
    public String getUserName() {
    	return userName;
    }
    public int getUserID() {
    	return userID;
    }
    public int getPoint() {
    	return point;
    }
    public int getLevel() {
    	return level;
    }
    public void setUserName(String name) {
    	this.userName=name;
    }
    public void setUserID(int userID) {
    	this.userID=userID;
    }
    public void setPoint(int point) {
    	this.point=point;
    }
    public void setLevel(int level) {
    	this.level=level;
    }
}
