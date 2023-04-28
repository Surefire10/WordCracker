package application;
	
import java.io.IOException;

import application.controller.WelcomeController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("application/view/WelcomePage.fxml"));
			Scene scene = new Scene(root);
			String stylesheet = this.getClass().getResource("./static/application.css").toExternalForm(); // for this scene
			scene.getStylesheets().add(stylesheet);
			primaryStage.setTitle("WordCracker");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.titleProperty();
			Image icon = new Image("file:resources/icon.png");
			primaryStage.getIcons().add(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
	
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Application.launch(args);


  
			
		
	} 
	
}