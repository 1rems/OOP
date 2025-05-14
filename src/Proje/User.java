package QuestHero;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	//user ıd
	private int point;
	private int level;
	public List<Task> completedTasks = new ArrayList<>();
	public List<Task> missedTasks = new ArrayList<>();
	
	public User(String name) {
        this.userName = name;
        this.point = 0;
        this.level = 1;
    }
	public void completeTask(Task task) {
		task.completeTask();
        completedTasks.add(task);
        point +=task.getPoints();
        levelUp();
    }
	public void levelUp() {
		if(point>level*100) {
			level++;
		}
	}
    public void missTask(Task task) {
        missedTasks.add(task);
        point -=task.getPoints();
    }
    public String toString() {
        return userName + " - " +level + " .lv  "+"point:"+point +",("+(level*100-point) + " points for next level)";
    }
    
    public String getUserName() {
    	return userName;
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
    public void setPoint(int point) {
    	this.point=point;
    }
    public void setLevel(int level) {
    	this.level=level;
    }
}
