package application;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ApplicationModel;

public abstract class FragenartController {

	@FXML
	ProgressIndicator prog;
	
	@FXML
	public ImageView logoImage;
	@FXML
	public ListView<String> chapterListView;

	@FXML
	public Label subjectLabel;

	@FXML
	public Label chapterLabel;

	@FXML
	public Label questionLabel;

	@FXML
	public Button vorherigeButton;

	@FXML
	public Button nächsteButton;

	@FXML
	public Button homeButton;

	public List<String> chapterList = FXCollections.observableArrayList();

	public double progresStep;

	public ApplicationModel model = new ApplicationModel();

	public abstract void setAufgabe();
	
	public void setLogo(){
		Image image = new Image("/logo/logo.PNG");
		logoImage.setImage(image);
	}

	public void fillChapterListView() { // Befüllt ListView mit Elemente der
										// Kapitelliste von der Datenbank
		System.out.println("Kapitelübersichtsliste wird befüllt ");
		chapterList = model.getChaptersToSubject(TempData.currentSubject);
		for (int i = 0; i < chapterList.size(); i++) {
			chapterListView.getItems().add(chapterList.get(i));
		}
	}// end fillChapterListView

	public void setSubjectLabel() { // Fach wird als Überschrift angezeigt
		subjectLabel.setText(TempData.currentSubject);
		setLogo();
	}// end setSubjectLabel

	public void setChapterLabel() {
		chapterLabel.setText(TempData.currentChapter);
	}

	public void setupProgressBar() {
		prog.progressProperty().bind(TempData.progressNumber.numberProperty());
		// progCircle.progressProperty().bind(TempData.progressNumber.numberProperty());
	}

	public void changeChapter() {
		progresStep = (1.0 / model.getTaskCountOfAChapter(TempData.currentSubject, TempData.currentChapterNumber));
		chapterListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				chapterLabel.setText(new_val);
				TempData.currentChapter = new_val;
				TempData.currentChapterNumber = model.getChapterNr(TempData.currentSubject, new_val);
				TempData.currentTaskNumber = 1;
				TempData.currentFragenart = model.getTaskFragenart(TempData.currentSubject,TempData.currentChapterNumber, TempData.currentTaskNumber);
				if (TempData.currentTaskNumber == 1) {
					resetProgressNumber();
					TempData.answersContainer = new boolean[model.getTaskCountOfAChapter(TempData.currentSubject,TempData.currentChapterNumber)];
					TempData.givenAnswersContainer = new String[model.getTaskCountOfAChapter(TempData.currentSubject,TempData.currentChapterNumber)];
					System.out.println("neuer Boolean Container für Kapitel: " + TempData.currentChapter);
				}
				Stage stage = (Stage) chapterListView.getScene().getWindow();
				switch (TempData.currentFragenart) {
				case "wf":
					loadWahrFalsch(stage);
					break;
				case "mc":
					loadMutlipleChoice(stage);
					break;
				case "rf":
					loadRf(stage);
					break;
				case "ba":
					loadBildschirmausgabe(stage);
					break;
				case "bfba":
					loadBfba(stage);
					break;
				case "lt":
					loadLueckentext(stage);
					break;
				}
			}
		});
	}// end setChapterLabel

	public void homeButtonClicked(ActionEvent e) {
		resetProgressNumber();
		System.out.println(" ");
		System.out.println("Zurück zum Start");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/startingWindow/StartingWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}// end homeButtonClicked

	public void nächsteButtonClicked(ActionEvent e) {
		TempData.progressNumber.setProgressNumber(TempData.progressNumber.getProgressNumber() + progresStep);
		if (TempData.currentTaskNumber < model.getTaskCountOfAChapter(TempData.currentSubject,
				TempData.currentChapterNumber)) {
			TempData.currentTaskNumber += 1;
			TempData.currentFragenart = model.getTaskFragenart(TempData.currentSubject, TempData.currentChapterNumber,
					TempData.currentTaskNumber);
			System.out.println("Nächste Aufabe wird vorbereitet:");
			System.out.println("Kapitel:" + TempData.currentChapterNumber + " Aufgabe:" + TempData.currentTaskNumber
					+ " " + TempData.currentFragenart);
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
			}// end switch
		} // end if
		else {
			loadSolutionDisplay(e);
		}
	} // end nächsteButtonClicked

	public void vorherigeButtonClicked(ActionEvent e) {
		TempData.progressNumber.setProgressNumber(TempData.progressNumber.getProgressNumber() - progresStep);
		if (TempData.currentTaskNumber > 1) {
			TempData.currentTaskNumber -= 1;
			TempData.currentFragenart = model.getTaskFragenart(TempData.currentSubject, TempData.currentChapterNumber,
					TempData.currentTaskNumber);
			System.out.println("Nächste Aufabe wird vorbereitet:");
			System.out.println("Kapitel:" + TempData.currentChapterNumber + " Aufgabe:" + TempData.currentTaskNumber
					+ " " + TempData.currentFragenart);
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
			}// end switch
		} // end if
	}// end vorherigeButtonClicked

	public void resetProgressNumber() {
		TempData.progressNumber.setProgressNumber(0.0);
	}

	// alle Load Methoden ---

	public void loadWahrFalsch(ActionEvent e) { // Öffnet in der Stage das
												// WahrFalsch.fxml
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
		} // end try
	} // end loadWahrFalsch

	public void loadRf(ActionEvent e) { // Öffnet in der Stage das
										// WahrFalsch.fxml
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
		} // end try
	}

	public void loadMutlipleChoice(ActionEvent e) { // Öffnet in der Stage das
													// MultipleChoice.fxml
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
		} // end try
	}

	public void loadBildschirmausgabe(ActionEvent e) { // Öffnet in der Stage
														// das
														// Bidlschirmausgabe.fxml
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
		} // end try
	}

	public void loadBfba(ActionEvent e) { // Öffnet in der Stage das Bfba.fxml
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
		} // end try
	}

	public void loadLueckentext(ActionEvent e) { // Öffnet in der Stage das
													// Lueckentext.fxml
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
		} // end try
	}

	public void loadSolutionDisplay(ActionEvent e) { // Öffnet in der Stage das
		// Lueckentext.fxml
		System.out.println("Lösungsfenster wird geladen");
		Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/displaySolution/displaySolution.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

	public void loadWahrFalsch(Stage s) { // Öffnet in der Stage das
		// WahrFalsch.fxml
		System.out.println("WahrFalsch wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/wahrFalsch/WahrFalsch.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	} // end loadWahrFalsch

	public void loadRf(Stage s) { // Öffnet in der Stage das
		// WahrFalsch.fxml
		System.out.println("WahrFalsch wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/reihenfolge/Reihenfolge.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

	public void loadMutlipleChoice(Stage s) { // Öffnet in der Stage das
		// MultipleChoice.fxml
		System.out.println("MultipleChoice wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/multipleChoice/MultipleChoice.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

	public void loadBildschirmausgabe(Stage s) { // Öffnet in der Stage
		// das
		// Bidlschirmausgabe.fxml
		System.out.println("Bildschirmausgabe wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/bildschirmausgabe/Bildschirmausgabe.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

	public void loadBfba(Stage s) { // Öffnet in der Stage das Bfba.fxml
		System.out.println("Bfba wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/bildFrageBildAntwort/Bfba.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

	public void loadLueckentext(Stage s) { // Öffnet in der Stage das
											// Lueckentext.fxml
		System.out.println("Lueckentext wird geladen");
		Stage stage = s;
		Pane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/lueckentext/Lueckentext.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException e1) {
			e1.printStackTrace();
		} // end try
	}

}
