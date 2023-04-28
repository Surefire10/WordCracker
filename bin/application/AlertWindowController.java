package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindowController extends WelcomeController{
	
	@FXML Label message;
	@FXML TextField nameField;
	@FXML Button okButton;
	public String name;
	public Stage stage  = new Stage();
	public AnchorPane root; 


	
	
	public void generateAlert(String errorMessage) {
		
		
		
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("AlertWindow.fxml"));
			
		} catch (IOException e) {

		System.out.println("resource not found");	
			
		}
		
		Scene scene = new Scene(root);
		Label message = (Label) scene.lookup("#message");
		message.setText(errorMessage);
		String stylesheet = this.getClass().getResource("application.css").toExternalForm(); // for this scene
		scene.getStylesheets().add(stylesheet);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(super.stage);
		stage.setTitle("WordCracker");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();
		
		
		
	}
	
	
	
	
		public void okButton(ActionEvent event) {
		
			stage = (Stage) okButton.getScene().getWindow();
			
			stage.close();
		
		
		
		
		}
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	


