package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.stage.Window;

public class MainPageController implements Initializable{
	
	
	public Stage stage  = new Stage();
	
	@FXML Label username;
	String userNameString = "";
	@FXML TextArea translation;
	@FXML TextArea origin;
	@FXML Button switchLanguages;
	@FXML Button logInFeature;
	@FXML Label plzSignIn;
	@FXML Tooltip saveTooltip;
	@FXML ChoiceBox<String> pickBoxLeft;
	@FXML ChoiceBox<String> pickBoxRight;
	

	String[] languages = {"Arabic", "English","Español"};
	
	
	
	
public void initialize(URL location, ResourceBundle resources) {
		
	
		Image icon = new Image("file:resources/icon.png");
		stage.getIcons().add(icon);
		pickBoxLeft.getItems().addAll(languages);
		pickBoxRight.getItems().addAll(languages);
		
	}
	

	
	public String usernameGetterFromLogin() {
		
		String extractor = username.getText();
		
		String username = extractor.substring(9);
		
		return username;
	}
	
	
	public boolean userTypeGetter() {
		
		
		
		
		
		//grab the user type from the database
		
		String username = usernameGetterFromLogin();
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection con = dbConnection.getConnection();
		String query = "SELECT USER_TYPE FROM USER_INFO WHERE USERNAME = \"" + username + "\";";
		Statement statment = null;
		ResultSet result = null;
		Boolean logged_in = false;
		String userTypeDB = "";
		
		try {
			 statment = con.createStatement();
			 result = statment.executeQuery(query);
			 if(result.next()) {
				 
				 userTypeDB = result.getString("USER_TYPE");
				 
				 if(userTypeDB.equals("LOGGED_IN_USER")) {
					 
					logged_in = true; // used is logged in
					 
				 }
				 
			 }
			 
			 
			 
		} catch (SQLException e) {
			
		}
		
		return logged_in;
			
		
		
	}
	
	
	public void switchLanguages() {
		
		
		String leftLang = pickBoxLeft.getValue();
		String rightLang = pickBoxRight.getValue();
		
		pickBoxLeft.setValue(rightLang);
		pickBoxRight.setValue(leftLang);
		
		if(!origin.getText().isBlank() && !translation.getText().isBlank()) {
			
			
			String currentOrigin = origin.getText();
			String currentTrans = translation.getText();
			
			origin.setText(currentTrans);
			translation.setText(currentOrigin);
			
			
		}
		
	}
	
	
	
	public String chooseLanguageSource() {
		
		 
		
		String languageCodeSource = "";
		
		if(pickBoxLeft.getValue().equals("Arabic")) {
			
			languageCodeSource = "ar";
			
			
		} else if (pickBoxLeft.getValue().equals("English")) {    
			
			
			languageCodeSource = "en";
	    	

		}		

		else if(pickBoxLeft.getValue().equals("Español")){
			
			languageCodeSource = "es";
			

		}
		
		return languageCodeSource;
				
	}
		
	
	public String chooseLanguageTarget() {
		
		 
		
		String languageCodeTarget = "";
		
		if(pickBoxRight.getValue().equals("Arabic")) {
			
			languageCodeTarget = "ar";
			
		} else if (pickBoxRight.getValue().equals("English")) {    
			
			
			languageCodeTarget = "en";
	    	
			
		}		

		else if(pickBoxRight.getValue().equals("Español")){
			
			languageCodeTarget = "es";
			
		}
		
		if(pickBoxRight.getValue() == pickBoxLeft.getValue()) {
		
		}
		
		return languageCodeTarget;	
		
	}
	
	
	
	public void userStartsTyping(){
		
		try {
		String textToTranslate = origin.getText();
				HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
				.header("content-type", "application/x-www-form-urlencoded")
				.header("Accept-Encoding", "application/gzip")
				.header("X-RapidAPI-Key", "72305c54e4msh4f73e4da2534fe5p19172cjsn5490b39da571")
				.header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
				.method("POST", HttpRequest.BodyPublishers.ofString("q="+textToTranslate+"&target="+chooseLanguageTarget()+"&source="+chooseLanguageSource()+"")) 
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String responseString = response.body();
		JSONObject jsonObj = new JSONObject(responseString);
		JSONObject data = jsonObj.getJSONObject("data");
		JSONArray translations = (JSONArray) data.get("translations"); 
		String translatedText = translations.getJSONObject(0).getString("translatedText");
		translation.setText(translatedText);
		
		if(responseString.contains("quota")) {
			
			AlertWindowController cont = new AlertWindowController();
			cont.generateAlert("We Ran Out of Request Quota :c");
			
		}
		
		
		} catch(JSONException e2) {
			
			if(pickBoxLeft.getValue() == pickBoxRight.getValue()) {
				
				translation.setText("Can't Translate from and to The Same Language!");
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	
		
		
		if(chooseLanguageSource().equals("ar") ) {
			
			origin.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			
			
		} else {  origin.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT); } 
		
		
		if(chooseLanguageTarget().equals("ar") ) {
			
			translation.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			
			
		}	else {  translation.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT); }	
		
		 if(chooseLanguageTarget() == "error") {
			 
			 
			 plzSignIn.setText("Can't Translate The Same Language");
			 
		 }
		
	}

	
	public void copy() {
		
		
		if(userTypeGetter()) {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:MM:SS DD/MM/YYYY");
		Date date = new Date();
		String currentDate = format.format(date);
		
		
		String formattedText = "My Translation At " + currentDate + " \r \r Original Text: " 
								+ origin.getText() + "\r Translated Text: " + translation.getText()  + "  " ;
		
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(formattedText);
		clipboard.setContent(content);
 
		
		
		
		
		} else {
			
			plzSignIn.setText("Please Log In To Use Tools c:");
			logInFeature.setVisible(true);
		}
	}
	
	public void save() {
		
		if(userTypeGetter()) {
		SimpleDateFormat format = new SimpleDateFormat("HH:MM:SS DD/MM/YYYY");
		Date date = new Date();
		String currentDate = format.format(date);
		
		String formattedText = "My Translation At " + currentDate + " \r \r Original Text: " 
				+ origin.getText() + "\r Translated Text: " + translation.getText()  + "  " ;

		
		Window window =  username.getScene().getWindow();
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save As");
		chooser.getExtensionFilters().addAll((new ExtensionFilter("text", ".txt")));
		File file = chooser.showSaveDialog(window);
		
		
		
			
			PrintStream stream;
			try {
				stream = new PrintStream(file);
				stream.print(formattedText);
				stream.flush();
			} catch (FileNotFoundException e) {
			System.out.println("");
			
			}
			
			catch(NullPointerException e1) {
				
				
				System.out.println("");

			}
		
		} else {plzSignIn.setText("Please Log In To Use Tools c:");
				logInFeature.setVisible(true);}
	}
	
	public String setUsername(String givenName) {
			
	
		userNameString = "Welcome, " + givenName  ;
		username.setText(userNameString);
		
		return givenName;
	}

	public void login() {
		
		WelcomeController cont = new WelcomeController();
		try {
			cont.triggerLogin();
		} catch (IOException e1) {
			System.out.println("didn't find the resource");
		}
		
		
	}
	
	
}

