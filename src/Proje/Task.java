package Proje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
	private String title;
	private LocalDate date;
	private int durationMinutes;
	private boolean isCompleted;
	private int points;
	private LocalDateTime createdTime;
	
	public Task(String title, int durationMinutes, LocalDate date,int point) {
        this.title =title;
        this.durationMinutes = durationMinutes;
        this.setDate(date);
        this.points=point;
        this.isCompleted = false;
        this.createdTime = LocalDateTime.now();
    }
	public void completeTask() {
        isCompleted = true;
        updateTaskStatus();
    }

	public void saveToDatabase(int userID) {
	    String sql = "INSERT INTO tasks "
	               + "(title, duration, date, point, isCompleted, createdTime, userID) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = DataBaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, title);
	        stmt.setInt(2, durationMinutes);    // duration
	        stmt.setString(3, date.toString());
	        stmt.setInt(4, points);             // point
	        stmt.setBoolean(5, isCompleted);
	        stmt.setString(6, createdTime.toString());
	        stmt.setInt(7, userID);             // userID

	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void updateTaskStatus() {
	        String sql = "UPDATE tasks SET isCompleted = ? WHERE title = ?";
	        try (Connection conn = DataBaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setBoolean(1, isCompleted);
	            pstmt.setString(2, title);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static List<Task> loadFromDatabase(int userID) {
		    List<Task> tasks = new ArrayList<>();
		    String sql = "SELECT title, duration, date, point, isCompleted, createdTime "
		               + "FROM tasks WHERE userID = ?";

		    try (Connection conn = DataBaseConnection.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setInt(1, userID);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                String title    = rs.getString("title");
		                int duration    = rs.getInt("duration");
		                LocalDate date  = LocalDate.parse(rs.getString("date"));
		                int point       = rs.getInt("point");
		                boolean done    = rs.getBoolean("isCompleted");
		                LocalDateTime ct= LocalDateTime.parse(rs.getString("createdTime"));

		                Task task = new Task(title, duration, date, point);
		                task.setIsCompleted(done);
		                task.setCreatedTime(ct);
		                tasks.add(task);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return tasks;
		}




	public String toString() {
	    return title + " - " + durationMinutes + " dk - " + (isCompleted ? "Tamamlandı" : "Bekliyor");
	}
	
	public String getTitle() {
    	return title;
    }
    public int getPoints() {
    	return points;
    }
    public int getdurationMinutes() {
    	return durationMinutes;
    }
    public boolean getIsCompleted() {
    	return isCompleted;
    }
    public void setTitle(String title) {
    	this.title=title;
    }
    public void setPoints(int point) {
    	if(point==10||point==30||point==50) {
    	this.points=point;}
    }
    public void setdurationMinutes(int durationMinutes) {
    	this.durationMinutes=durationMinutes;
    }
    public void setIsCompleted(boolean isCompleted) {
    	this.isCompleted=isCompleted;
    }
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
}
