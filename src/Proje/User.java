package Proje;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private String userID;
	private int point;
	private int level;
	public List<Task> completedTasks = new ArrayList<>();
	public List<Task> missedTasks = new ArrayList<>();
	private List<String> achievements;

	
	public User(String name,String userID) {
        this.userName = name;
        this.userID=userID;
        this.point = 0;
        this.level = 1;
        this.achievements = new ArrayList<>();
    }
	public void completeTask(Task task) {
		task.completeTask();
        completedTasks.add(task);
        point +=task.getPoints();
        levelUp();
    }
	public void levelUp() {
		if(point>=level*100) {
			level++;
		}
	}
    public void missTask(Task task) {
        missedTasks.add(task);
        point -=task.getPoints();
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
    public String getUserID() {
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
    public void setUserID(String userID) {
    	this.userID=userID;
    }
    public void setPoint(int point) {
    	this.point=point;
    }
    public void setLevel(int level) {
    	this.level=level;
    }
}
