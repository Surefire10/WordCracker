package application;
	
import java.io.IOException;
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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			String stylesheet = this.getClass().getResource("C:\\Users\\Yara\\eclipse-workspace\\WordCracker\\src\\application.css").toExternalForm(); // for this scene
			scene.getStylesheets().add(stylesheet);
			primaryStage.setTitle("WordCracker");
			primaryStage.getIcons();
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