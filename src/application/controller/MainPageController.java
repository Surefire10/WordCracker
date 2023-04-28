package application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control. *;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainPageController implements Initializable{
	
	
	public Stage stage  = new Stage();


	@FXML TextArea translation;
	@FXML TextArea origin;
	@FXML Button switchLanguages;
	@FXML Button copyButton;
	@FXML ChoiceBox<String> translationTo;
	@FXML ChoiceBox<String> translationFrom;



	String[] languages = {"Arabic", "English","Espanol"};
	
	
	
	
public void initialize(URL location, ResourceBundle resources) {

		Image icon = new Image("file:resources/icon.png");
		stage.getIcons().add(icon);
		translationTo.getItems().addAll(languages);
		translationFrom.getItems().addAll(languages);



}
	

	

	public void switchLanguages() {
		
		
		String leftLang = translationTo.getValue();
		String rightLang = translationFrom.getValue();

		translationTo.setValue(rightLang);
		translationFrom.setValue(leftLang);
		
		if(!origin.getText().isBlank() && !translation.getText().isBlank()) {
			
			
			String currentOrigin = origin.getText();
			String currentTrans = translation.getText();
			
			origin.setText(currentTrans);
			translation.setText(currentOrigin);
			
			
		}
		
	}
	
	
	
	public String chooseLanguageSource() {
		
		 
		
		String languageCodeSource = "";
		
		if(translationFrom.getValue().equals("Arabic")) {
			
			languageCodeSource = "ar";


		} else if (translationFrom.getValue().equals("English")) {

			
			languageCodeSource = "en";
	    	

		}		

		else if(translationFrom.getValue().equals("Espanol")){
			
			languageCodeSource = "es";
			

		}
		
		return languageCodeSource;
				
	}
		
	
	public String chooseLanguageTarget() {
		
		 
		
		String languageCodeTarget = "";
		
		if(translationTo.getValue().equals("Arabic")) {
			
			languageCodeTarget = "ar";
			
		} else if (translationTo.getValue().equals("English")) {
			
			
			languageCodeTarget = "en";
	    	
			
		}		

		else if(translationTo.getValue().equals("Espanol")){
			
			languageCodeTarget = "es";
			
		}
		

		
		return languageCodeTarget;	
		
	}


	public boolean isInternetWorking(){

		try {
			URL url = new URL("http://www.google.com");

			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (IOException e) {
			return false;
		}

	}

	public void onTranslationRequested() {

		if (isInternetWorking()) {
			String textToTranslate = origin.getText();

			try {
				// build Http request;
				// this is what worked for my API provider;
				// each provider is different, look up how to connect to yours :)
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
						.header("content-type", "application/x-www-form-urlencoded")
						.header("Accept-Encoding", "application/gzip")
						.header("X-RapidAPI-Key", "your API Key goes here")
						.header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
						.method("POST", HttpRequest.BodyPublishers.ofString("q=" + textToTranslate + "&target="
								+ chooseLanguageTarget() + "&source=" + chooseLanguageSource() + ""))
						.build();
				//receive response from API and parse it;
				HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				String responseString = response.body();
				System.out.println(responseString);
				JSONObject jsonObj = new JSONObject(responseString);
				JSONObject data = jsonObj.getJSONObject("data");
				JSONArray translations = (JSONArray) data.get("translations");
				//this is the end result
				String translatedText = translations.getJSONObject(0).getString("translatedText");
				translation.setText(translatedText);


			} catch (JSONException | IOException | InterruptedException e) {

				e.printStackTrace();


			}

			if (chooseLanguageSource().equals("ar")) {

				origin.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				translation.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);


			} else {

				origin.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
				translation.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			}


		} else {

				Alert alert = new Alert(Alert.AlertType.ERROR, "Check Internet Connection And Try Again");

				alert.show();
		}
	}




	
	public void copy() {


		System.out.println("copy");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Date date = new Date();
		String currentDate = format.format(date);
		
		
		String formattedText =  " Original Text: "
								+ origin.getText() + "\r Translated Text: " + translation.getText()  + "  " +
								"\r At " + currentDate ;
		
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(formattedText);
		clipboard.setContent(content);

		
		

	}
	




	
	
}

