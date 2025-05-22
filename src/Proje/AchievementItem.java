package Proje;

import javafx.scene.paint.Color;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
//"Başarımlar"ın arayüz kısmı
public class AchievementItem extends HBox{
	public AchievementItem(Achievements achievement) {
        super(10);//elementler arasındaki boşluk
        
        //yuvarlaklar , boyutları ve renkleri
        Circle circle = new Circle(12);
        circle.setStroke(Color.GRAY);
        circle.setFill(achievement.isAchieved() ? Color.LIGHTCORAL : Color.LIGHTPINK);
        
        //başarımların isimleri , yazı boyutu ve fontu
        Label titleLabel = new Label(achievement.getAchievementTitle());
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        //başarım açılma tarihi , boyutu ve rengi
        Label dateLabel = new Label(achievement.isAchieved() && achievement.getDate() != null ? 
            achievement.getDate().toString() : "");
        dateLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
        
        VBox textBox = new VBox(titleLabel, dateLabel);
        textBox.setSpacing(2);

        this.getChildren().addAll(circle, textBox);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
