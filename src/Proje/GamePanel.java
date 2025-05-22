package Proje;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GamePanel extends AbstractMapPanel {
	public GamePanel(User user) {
        super(user);
        drawMap();
    }
	 
	
	
	@Override
	public void drawMap() {
		this.getChildren().clear();
		
		int currentLevel = user.getLevel();

	    for (int i = 1; i <= 10; i++) {
	        Circle node = new Circle(25);
	        node.setFill(i <= user.getLevel() ? Color.LIGHTCORAL : Color.LIGHTPINK);

	        Label number = new Label(String.valueOf(i));
	        number.setTextFill(Color.WHITE);
	        number.setStyle("-fx-font-weight: bold;");

	        StackPane circleWithNumber = new StackPane(node, number);
	        double x = i * 70;
	        double y = 65;
	        
	        circleWithNumber.setLayoutX(x);
	        circleWithNumber.setLayoutY(y);

	        this.getChildren().add(circleWithNumber);
	        
	        if (i == currentLevel) {
	            try {
	                Image characterImage = new Image(getClass().getResource("/images/catt.png").toExternalForm());
	                ImageView characterView = new ImageView(characterImage);
	                characterView.setFitWidth(70); // boyut ayarı
	                characterView.setFitHeight(40);
	                characterView.setLayoutX(x - 15); // ortalamak için ayar
	                characterView.setLayoutY(y - 33); // dairenin üstüne koy

	                this.getChildren().add(characterView);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
		

}
