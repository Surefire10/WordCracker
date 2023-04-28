package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class SignUpController extends WelcomeController {

	public Stage stage  = new Stage();
	public Scene scene;
	public String name;
	@FXML private Button continueButton;
	@FXML private TextField usernameField;
	@FXML private TextField emailField;
	@FXML private PasswordField password;
	@FXML private PasswordField passwordConfirm;
	@FXML private Label usernameConfirmation;
	@FXML private Label passwordConfirmation;
	String LabelStyle = "-fx-font-family: \"Helvetica\";\r\n"
			+ "    -fx-alignment: CENTER;\r\n"
			+ "    -fx-text-fill: BLACK;\r\n"
			+ "    -fx-font-size: 12px;\r\n"
			+ "    -fx-font-weight: bold;";
	
	
	
	
	@FXML 
	
	
	public void generatesignUp() throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		scene = new Scene(root); 
		String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene
		scene.getStylesheets().add(stylesheet);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(super.stage);
		stage.setTitle("Sign up To WordCracker");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();
		root.requestFocus();
		
		
	
		
		
	}
	
	
	

	 public boolean isMatching() {
		
		String passwordEntered = password.getText();
		String passwordConfirmed = passwordConfirm.getText();
		
		 LabelStyle = "-fx-font-family: \"Helvetica\";\r\n"
				+ "    -fx-alignment: CENTER;\r\n"
				+ "    -fx-text-fill: BLACK;\r\n"
				+ "    -fx-font-size: 12px;\r\n"
				+ "    -fx-font-weight: bold;";
		
	
		//check if passwords are matched or not;
		 
		 if(passwordEntered.equals(passwordConfirmed)  ) {
			 
			 
			 passwordConfirmation.setText("Passwords are Matching");
			 passwordConfirmation.setStyle(LabelStyle + "-fx-text-fill: \"#16c213\";");
			 password.setStyle("-fx-focus-color: #16c213;");
			 passwordConfirm.setStyle("-fx-focus-color: #16c213;");
			 
			 return true;
			 
			 
		 } else { 
			 
			 passwordConfirmation.setText("Passwords are not Matching");
			 passwordConfirmation.setStyle(LabelStyle + "	-fx-text-fill: \"#ba1a1a\";");
			 password.setStyle("-fx-focus-color: #ba1a1a;");
			 passwordConfirm.setStyle("-fx-focus-color: #ba1a1a;");
		 
			 return false;
		 }
		 
		 
	 }
	
	 /**
	 public boolean emailValidation(ActionEvent event) {
		 

		 String validEmailSequences = "@gmail.com @hotmail.com @outlook.com";
		 String email = emailField.getText();
		 
		 if (!email.contains(validEmailSequences)){ 
				
			 	usernameConfirmation.setText("Invalid Email!");
				emailField.requestFocus();
				emailField.setStyle("-fx-focus-color: #ba1a1a;");
				emailField.setStyle("-fx-focus-color: #ba1a1a;");
				usernameConfirmation.setStyle(LabelStyle + "-fx-text-fill: #ba1a1a;");
				
				
				return false;
				
			 } else  {  
				 
					usernameConfirmation.setText("Valid Email!");
					emailField.requestFocus();
					emailField.setStyle("-fx-focus-color: #16c213;");
					emailField.setStyle("-fx-focus-color: #16c213;");
					usernameConfirmation.setStyle(LabelStyle + "-fx-text-fill: #16c213;");
				 
			 return true;}

			 
	 }

**/
	 
	public void continueButton(ActionEvent event)  {
	
		 String passwordGetter = password.getText();
		 String username = usernameField.getText();
		 String email = emailField.getText();
		 
		 UserTypes type ;
		 
		 
		 
		 if (isMatching()) {
			 
			 
					try {
						
						DatabaseConnection connect = new DatabaseConnection();
						Connection connectionDB = connect.getConnection();
						String query ="INSERT INTO USER_INFO(USERNAME ,EMAIL,PASSWORD,USER_TYPE) VALUES (\""
						+ username +"\", \"" + email + "\", \"" 
						+passwordGetter+ "\", \"" + UserTypes.LOGGED_IN_USER +"\" );";    
						Statement statement = null;
						try {
							 statement = connectionDB.createStatement();
							
						} catch (SQLException e) {
							
							System.out.println("cannot connect to Database");
						}
						
						int queryResult = statement.executeUpdate(query);
						usernameConfirmation.setText("Username is Available!");
						usernameField.setStyle("-fx-focus-color: #16c213;");
						usernameConfirmation.setStyle(LabelStyle + "-fx-text-fill: #16c213;");
						 System.out.println("good");
							
						
						
					} catch (java.sql.SQLIntegrityConstraintViolationException e) {
						
						usernameConfirmation.setText("Username or Email Already Exists!");
						usernameField.requestFocus();
						usernameField.setStyle("-fx-focus-color: #ba1a1a;");
						emailField.setStyle("-fx-focus-color: #ba1a1a;");
						usernameConfirmation.setStyle(LabelStyle + "-fx-text-fill: #ba1a1a;");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
						
				
					
					QuickStartController controller = new QuickStartController();
					
					boolean isOnline = false;
					
						try {
							
							isOnline = controller.checkInternet();
							
						} catch (IOException e) {
							
							AlertWindowController alert = new AlertWindowController();
							alert.generateAlert("No Internet Connection /r Please Try Again");
							
						}
						
		
						
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
						 
						
						}
		 		
		 
		 			
		 			
			
	
			}


		
			
		}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
	
	
	
	
	
