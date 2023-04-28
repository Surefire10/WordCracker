package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController extends WelcomeController implements Initializable{

	public Stage stage  = new Stage();
	public Scene scene;
	public String name;
	@FXML public TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Label labelUnderPassword;
	String LabelStyle = "-fx-font-family: \"Helvetica\";\r\n"
			+ "    -fx-alignment: CENTER;\r\n"
			+ "    -fx-text-fill: BLACK;\r\n"
			+ "    -fx-font-size: 12px;\r\n"
			+ "    -fx-font-weight: bold;";

	
	
	
		public void generateLogIn() throws IOException {
		
		
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
		scene = new Scene(root);
		String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene
		scene.getStylesheets().add(stylesheet);
		Image icon = new Image("file:resources/icon.png");
		stage.getIcons().add(icon);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(super.stage);
		root.requestFocus();
		stage.setTitle("Log in To WordCracker");
		stage.setResizable(false);
		stage.setScene(scene);
		root.requestFocus();
		stage.showAndWait();
	
		
	}
	
	
		
		public void logIn(ActionEvent event) {
			
			
			String username = usernameField.getText();
			String password = passwordField.getText();

			
			
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connect = dbConnection.getConnection();
			String query = "SELECT USERNAME,PASSWORD FROM USER_INFO WHERE USERNAME =\"" + username + "\"  AND PASSWORD = \"" +password +"\"";         
			
			Statement statement = null;
			ResultSet result = null; 
			try {
				 statement = connect.createStatement();
				 result = statement.executeQuery(query);
				 
				 if (result.next()) {
					 
					 
					 String usernameInDB = result.getString("USERNAME");
					 
					 
					 
						 if (usernameInDB.equals(usernameField.getText())) {
							 
							 
							 FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml")); // load up the other scene
							 try {
								 
								 
								root = loader.load();
								
							} catch (IOException e) {
	
								System.out.println("resource not found");
								
							} 
							 
							 MainPageController maincontroller = loader.getController();
							 
							 maincontroller.setUsername(usernameField.getText());
							 
							 
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
						
						 
						 
						 } else {
							 
							 

							 labelUnderPassword.setStyle(LabelStyle);
							 labelUnderPassword.setStyle(LabelStyle + "-fx-text-fill: \"#ba1a1a\";");
							 labelUnderPassword.setText("Username or Password is Incorrect!");
							 usernameField.setStyle("-fx-focus-color: #ba1a1a;");
							 
						 
					 }
					 
				 } else { }

				 
				 
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
		public void signUp() throws IOException {
			
			SignUpController controller = new SignUpController();
			
			controller.generatesignUp();
			
		}



		public void initialize(URL arg0, ResourceBundle arg1) {
			
			Image icon = new Image("file:resources/icon.png");
			stage.getIcons().add(icon);
					
			
		}
	
	
	
	
}
