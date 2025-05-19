package Proje;

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
	}
	
}
