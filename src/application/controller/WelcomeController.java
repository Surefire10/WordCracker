package application.controller;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.stage.Stage;


public class WelcomeController {



	public Parent root;
	public Stage stage = new Stage();
	public Scene scene;
	public String name;





	public void onStartClicked(ActionEvent actionEvent) {
		try {

			Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
			scene = new Scene(FXMLLoader.load(getClass().getResource("../view/MainPage.fxml")));
			stageTheEventSourceNodeBelongs.setTitle("WordCracker");
			stageTheEventSourceNodeBelongs.setResizable(false);
			stageTheEventSourceNodeBelongs.titleProperty();
			Image icon = new Image("file:resources/icon.png");
			stageTheEventSourceNodeBelongs.getIcons().add(icon);
			stageTheEventSourceNodeBelongs.setScene(scene);
			stageTheEventSourceNodeBelongs.show();

		} catch (Exception e) {

			Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot Load View");

			alert.show();
		}
	}

}














