package displaySolution;

import java.net.URL;
import java.util.ResourceBundle;
import application.FragenartController;
import application.TempData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class displaySolutionController extends FragenartController implements Initializable {

	@FXML
	private Button vorherigeButton;
	@FXML
	private Button nächsteButton;
	@FXML
	private Label subjectLabel;
	@FXML
	private Label chapterLabel;
	@FXML
	private Button homeButton;
	@FXML
	private TextArea trueTextArea;
	@FXML
	private TextArea falseTextArea;
	@FXML
	private PieChart pieChart;

	private	String solutionString = "";
	private String falseSolutionString ="";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillChapterListView();
		changeChapter();
		setAufgabe();

	}

	@Override
	public void setAufgabe() {
		chapterLabel.setText(TempData.currentChapter);
		loadSolutions();
	}

	private void loadSolutions() {
		int falsch = 0, wahr = 0, nichtBeantwortet = 0;

	
		trueTextArea.setWrapText(true);
		falseTextArea.setWrapText(true);
		for (int i = 0; i < model.getTaskCountOfAChapter(TempData.currentSubject, TempData.currentChapterNumber); i++) {

			if (TempData.givenAnswersContainer[i] == null || TempData.givenAnswersContainer[i].equals("")) {
				falseSolutionString += model.getQuestion(TempData.currentSubject, TempData.currentChapterNumber, i + 1)
						+ "--> Keine Antwort!\n";
				falseTextArea.setText(solutionString);
				nichtBeantwortet++;
			} else if (TempData.givenAnswersContainer[i].equals("Bildschirmausgabe")) {
				solutionString += "Frage " + (i + 1) + " war eine Bildschirmausgabe.";
				wahr++;
			} else if (TempData.givenAnswersContainer[i].equals("Es wurden nicht alle Felder ausgefüllt")) {
				falseSolutionString += "Frage " + (i + 1) + " war eine Sortieraufgabe. " + TempData.givenAnswersContainer[i];
				nichtBeantwortet++;

			} else if (TempData.answersContainer[i]) {
				solutionString += model.getQuestion(TempData.currentSubject, TempData.currentChapterNumber, i + 1)
						+ "--> Deine Antwort: " + TempData.givenAnswersContainer[i] + " ist Richtig! \n";
				wahr++;
			} else if (!TempData.answersContainer[i]) {
				falseSolutionString += model.getQuestion(TempData.currentSubject, TempData.currentChapterNumber, i + 1)
						+ "--> Deine Antwort: " + TempData.givenAnswersContainer[i] + " ist Falsch! \n";
				falsch++;
			}
		}

		ObservableList<Data> list = FXCollections.observableArrayList(new PieChart.Data("Falsch", falsch),
				new PieChart.Data("Nicht beantwortet", nichtBeantwortet), new PieChart.Data("Richtig", wahr));
		pieChart.setData(list);
		
		
		falseTextArea.setText(falseSolutionString);
		falseTextArea.setStyle("-fx-text-fill: red");
		trueTextArea.setText(solutionString);
		trueTextArea.setStyle("-fx-text-fill: green");
		
	}

	public void vorherigeButtonClicked(ActionEvent e) {
		TempData.progressNumber.setProgressNumber(TempData.progressNumber.getProgressNumber() - progresStep);
		if (TempData.currentTaskNumber >= 1) {
			TempData.currentFragenart = model.getTaskFragenart(TempData.currentSubject, TempData.currentChapterNumber,
					TempData.currentTaskNumber);
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
	}// end vorherigeButtonClicked

}