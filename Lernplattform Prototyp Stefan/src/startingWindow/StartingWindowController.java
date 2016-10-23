package startingWindow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.TempData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ApplicationModel;

public class StartingWindowController implements Initializable {

	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private ComboBox<String> subjectCombobox;
	@FXML
	private ImageView logoImage;

	List<String> subjectList = FXCollections.observableArrayList();

	private ApplicationModel model = new ApplicationModel();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setLogo();
		setAndCheckDBConnection();
	}
	
	public void setLogo(){
		Image image = new Image("/logo/logo.PNG");
		logoImage.setImage(image);
	}
	
	public void setAndCheckDBConnection(){
		System.out.println("Erstellen eines neuen ApplicationModels");
		System.out.print("Überprüfe ob DB Verbindung aktiv: ");
		if (model.isConnected()) {
			System.out.println("Verbindung steht");
		}
		subjectList = model.getSubjects();
		for (int i = 0; i < subjectList.size(); i++) {
				subjectCombobox.getItems().add(subjectList.get(i));
				System.out.println("Die Combobox enthält " + subjectList.get(i));
		}
	}

	
	public void setChosenSubject(ActionEvent e) { // Überweist Fach an TempData
		TempData.currentSubject = subjectCombobox.getValue();
		System.out.println(TempData.currentSubject + " ausgewählt");
	}

	
	public void button1Clicked(ActionEvent e) { // Übungsbutton geklickt
		if ( isSubjectSelected() ) {
			TempData.currentFragenart = model.getFirstFragenArt(TempData.currentSubject);
			TempData.currentChapterNumber = 1;
			TempData.currentTaskNumber = 1;
			TempData.currentChapter = model.getChapter(TempData.currentSubject, TempData.currentChapterNumber);
			TempData.answersContainer = new boolean[model.getTaskCountOfAChapter(TempData.currentSubject, TempData.currentChapterNumber)];
			TempData.givenAnswersContainer = new String [model.getTaskCountOfAChapter(TempData.currentSubject, TempData.currentChapterNumber)];
			System.out.println("neuer Boolean Container für Kapitel: " + TempData.currentChapter);
			switch (TempData.currentFragenart) {
			case "wf":
				loadWahrFalsch(e);
				break;
			case "mc":
				loadMutlipleChoice(e);
				break;
			case "rf":
				loadRf(e);
				break;
			case "ba":
				loadBildschirmausgabe(e);
				break;
			case "bfba":
				loadBfba(e);
				break;
			case "lt":
				loadLueckentext(e);
				break;
			default:
				System.out.println("fragenart not implemented");
				break;
			}
		}
	}

	
	public void button2Clicked(ActionEvent e) { // weiteres Übungsmaterial geklickt
		if (isSubjectSelected()){
			loadOtherHelp();
		}
	}

	
	public boolean isSubjectSelected(){		//Überprüft ob Fach ausgewählt; Ja -> true
		if (TempData.currentSubject.equals(" ")) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	public void loadWahrFalsch(ActionEvent e) { 	//Öffnet in der Stage das WahrFalsch.fxml
		System.out.println("WahrFalsch wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/wahrFalsch/WahrFalsch.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void loadRf(ActionEvent e) { 	//Öffnet in der Stage das WahrFalsch.fxml
		System.out.println("WahrFalsch wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/reihenfolge/Reihenfolge.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void loadMutlipleChoice(ActionEvent e) {		//Öffnet in der Stage das MultipleChoice.fxml
		System.out.println("MultipleChoice wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/multipleChoice/MultipleChoice.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void loadBildschirmausgabe(ActionEvent e) {		//Öffnet in der Stage das Bildschirmausgabe.fxml
		System.out.println("Bildschirmausgabe wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/bildschirmausgabe/Bildschirmausgabe.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void loadBfba(ActionEvent e) {		//Öffnet in der Stage das Bfba.fxml
		System.out.println("Bfba wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/bildFrageBildAntwort/Bfba.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void loadLueckentext(ActionEvent e) {		//Öffnet in der Stage das Lueckentext.fxml
		System.out.println("Lueckentext wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/lueckentext/Lueckentext.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	
	public void loadOtherHelp() {	//Öffnet in einer neuen Stage die weitere Hilfe
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/weitereHilfe/OtherHelpWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
