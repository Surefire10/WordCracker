package application;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuickStartController extends WelcomeController{

	public Stage stage  = new Stage();
	public AnchorPane root; 
	final String CONNECTOR_URL = "https://www.google.com/";
	@FXML TextField nameField;
	@FXML Button quickStartButton;
	@FXML Label greetingLabel; 
	public Scene scene;
	public String name;


	
	
	public void generateQuickStarter() throws IOException {
	
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("QuickStart.fxml"));
		scene = new Scene(root);
		String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene
		scene.getStylesheets().add(stylesheet);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(super.stage);
		Image icon = new Image("file:resources/icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("WordCracker");
		stage.setResizable(false);
		stage.setScene(scene);
		root.requestFocus();
		stage.showAndWait();
		
		
		
		
	}
	

	public boolean checkInternet() throws  IOException {
		

		try {
			
			
			URL url = new URL(CONNECTOR_URL);
			URLConnection urlConnect = url.openConnection();
			urlConnect.connect();
			
			return true;
			
			}
		
			catch(IOException e) {
				
				
				
				AlertWindowController controller = new AlertWindowController();
				controller.generateAlert("No Internet Connection. \r  Please Try Again Later.");
				return false;
				
			}
			
	}
	
	
	
	
	
	public String greeting() {
		
		
		name = nameField.getText();
		String hello = "Nice to Meet You " + name + "!";   	
		greetingLabel.setText(hello);
		return name;
	}
	
	
	
	
	public void continueButton(ActionEvent event) throws IOException {
		
			
			if (checkInternet()) {
			
			 name = nameField.getText();// grab whatever is in the text field at welcome
			 
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml")); // load up the other scene
			 root = loader.load(); 
			 
			 MainPageController controller = loader.getController();
			 
			 if(name.equalsIgnoreCase("")) {
				 
				 controller.setUsername("QuickUser");
				 
			 } else {controller.setUsername(name);}
			 
			 
			 stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene instance
			 scene.getStylesheets().add(stylesheet);
			 stage.setTitle("WordCracker");
			 stage.getIcons();
			 stage.setResizable(false);
			 stage.setScene(scene);
			 stage.titleProperty();
			 stage.show();
			 
			 
			UserTypes userTypes = null;
			String nameInDB = nameField.getText();
			Random random = new Random(900);
			int randomInt = random.nextInt();	
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection con = dbConnection.getConnection();
			String query = "INSERT INTO USER_INFO(USERNAME ,EMAIL,PASSWORD,USER_TYPE) VALUES (\""
					+ nameInDB + randomInt + "\", \"---" 
					+ randomInt + "\","
					+ " \"  ---" +randomInt+ "\", \"" + userTypes.QUICKSTART_USER +"\" );";  
				
				try {
					
					Statement statment = con.createStatement();
					int	 result = statment.executeUpdate(query); 	//returns rows affected
								
					 
				} catch (SQLException e) {
					
					System.out.println("nah idk fam");
					
				}
			 
			
			}
			
			
		
	}
		

		
	
		public void switchByEnter(ActionEvent event) throws IOException {
			
			

			if (checkInternet()) {
			
			 name = nameField.getText();// grab whatever is in the text field at welcome
			 
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml")); // load up the other scene
			 root = loader.load(); 
			 
			 MainPageController controller = loader.getController();
			 
			 if(name.equalsIgnoreCase("")) {
				 
				 controller.setUsername("QuickUser");
				 
			 } else {controller.setUsername(name);}
			 
			 
			 stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene instance
			 scene.getStylesheets().add(stylesheet);
			 stage.setTitle("WordCracker");
			 stage.getIcons();
			 Image icon = new Image("file:resources/icon.png");
			 stage.getIcons().add(icon);
			 stage.setResizable(false);
			 stage.setScene(scene);
			 stage.titleProperty();
			 stage.show();
			 
			 
			UserTypes userTypes = null;
			String nameInDB = nameField.getText();
			Random random = new Random(900);
			int randomInt = random.nextInt();	
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection con = dbConnection.getConnection();
			String query = "INSERT INTO USER_INFO(USERNAME ,EMAIL,PASSWORD,USER_TYPE) VALUES (\""
					+ nameInDB + randomInt +"\", \"---" 
					+ randomInt + "\","
					+ " \"  ---" +randomInt+ "\", \"" + userTypes.QUICKSTART_USER +"\" );";  
				
				try {
					
					Statement statment = con.createStatement();
					int	 result = statment.executeUpdate(query); 	//returns rows affected
								
					 
				} catch (SQLException e) {
					
					
				}
			 
			
			
			
			
	
		
			}
	
	
	
	
	
	
	
	
	
		}
		
}