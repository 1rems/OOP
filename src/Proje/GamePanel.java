package Proje;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GamePanel extends AbstractMapPanel {  //"Seviye"nin arayüz kısmı
	public GamePanel(User user) {
        super(user);
        drawMap();
    }
	@Override
	public void drawMap() {
		this.getChildren().clear();

	    for (int i = 1; i <= 10; i++) {  //1'den 10'a kadar seviyeler

	        Circle node = new Circle(25);  //yuvarlakların boyutları ve renkleri
	        node.setFill(i <= user.getLevel() ? Color.LIGHTCORAL : Color.LIGHTPINK);

	        Label number = new Label(String.valueOf(i));  //yuvarlak içindeki seviye sayıları
	        number.setTextFill(Color.WHITE);
	        number.setStyle("-fx-font-weight: bold;");

	        StackPane circleWithNumber = new StackPane();  //yuvarlak konumları
	        circleWithNumber.getChildren().addAll(node, number);
	        circleWithNumber.setLayoutX(i * 70);
	        circleWithNumber.setLayoutY(65);

	        this.getChildren().add(circleWithNumber);
	    }
	}
		

}
