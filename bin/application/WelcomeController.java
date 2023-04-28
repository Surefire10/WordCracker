package application;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;


public class WelcomeController {

	
	@FXML TextField nameField;
	@FXML Button quickStartButton;
	@FXML Label username;
	@FXML Label alertMessageLabel;
	public Parent root;
	public Stage stage = new Stage();
	public Scene scene;
	public String name;

	

	
	
	

    public void triggerQuickStart(ActionEvent event) throws IOException {
	  
	  
    	QuickStartController controller = new QuickStartController();
    	controller.generateQuickStarter();
    	
    	
    	
    	
    	
    }
		
	

    public void triggerLogin() throws IOException {
    	
    	LoginController controller = new LoginController();
    	controller.generateLogIn();
    	
    	
    }





}






