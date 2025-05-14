package QuestHero;

import java.time.LocalDate;

public class Task {
	private String title;
	private LocalDate date;
	//acıklama???
	private int durationMinutes;
	private boolean isCompleted;
	private int points;
	public Task(String title, int durationMinutes, LocalDate date,int point) {
        this.title =title;
        this.durationMinutes = durationMinutes;
        this.date = date;
        this.points=point;
        this.isCompleted = false;
    }
	public void completeTask() {
        isCompleted = true;
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
}
