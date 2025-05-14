package Proje;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		//başlık
		Label label = new Label("Görev Listesi");
		
		//buton
		Button button = new Button("Görev Ekle");
		
		//dikey düzen
		VBox vBox= new VBox(10);
		vBox.getChildren().addAll(label, button);
		
        // Sahne 
        Scene scene = new Scene(vBox, 300, 200);

        // Pencere ayarları
        primaryStage.setTitle("Görev Takip Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
