package Proje;

import javafx.scene.paint.Color;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class AchievementItem extends HBox{  //"Başarımlar"ın arayüz kısmı
	public AchievementItem(Achievements achievement) {
        super(10);  //elementler arasındaki boşluk
        
        Circle circle = new Circle(12);  //yuvarlaklar , boyutları ve renkleri
        circle.setStroke(Color.GRAY);
        circle.setFill(achievement.isAchieved() ? Color.LIGHTCORAL : Color.LIGHTPINK);
        

        Label titleLabel = new Label(achievement.getAchievementTitle());  //başarımların isimleri , yazı boyutu ve fontu
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label dateLabel = new Label(achievement.isAchieved() && achievement.getDate() != null ? 
            achievement.getDate().toString() : "");  //başarım açılma tarihi , boyutu ve rengi
        dateLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");
        
        VBox textBox = new VBox(titleLabel, dateLabel);
        textBox.setSpacing(2);

        this.getChildren().addAll(circle, textBox);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
