package Proje;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	public void start(Stage primaryStage) {
		Label label= new Label("Merhaba javaFX!");
		StackPane root = new StackPane(label);
		Scene scene = new Scene(root,300,200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("First");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
