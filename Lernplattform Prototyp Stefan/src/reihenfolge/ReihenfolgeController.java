package reihenfolge;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.FragenartController;
import application.TempData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ReihenfolgeController extends FragenartController implements Initializable {
	@FXML
	private Button loesen;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private Label label7;
	@FXML
	private Label label8;
	@FXML
	private ComboBox<Integer> antwort1;
	@FXML
	private ComboBox<Integer> antwort2;
	@FXML
	private ComboBox<Integer> antwort3;
	@FXML
	private ComboBox<Integer> antwort4;
	@FXML
	private ComboBox<Integer> antwort5;
	@FXML
	private ComboBox<Integer> antwort6;
	@FXML
	private ComboBox<Integer> antwort7;
	@FXML
	private ComboBox<Integer> antwort8;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillChapterListView();
		setSubjectLabel();
		setChapterLabel();
		changeChapter();
		setupProgressBar();
		setAufgabe();
	}

	@Override
	public void setAufgabe() {
		int questionCount;
		ArrayList<String> questionList;
		chapterLabel.setText(TempData.currentChapter);
		questionCount = model.getQuestionsCount(TempData.currentSubject, TempData.currentChapterNumber,TempData.currentTaskNumber);
		resetControls();
		setNecessaryControls(questionCount);
		setComboboxChoices(questionCount);
		questionList = model.getQuestionList(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber);
		fillLabelsWithQuestions(questionList);
		System.out.println("Aufgabe wird aufgebaut: " + TempData.currentSubject + "Kapitel:" + TempData.currentChapterNumber +" Aufgabe:" +TempData.currentTaskNumber);

	}

	private void resetControls() {
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		label4.setVisible(true);
		label5.setVisible(true);
		label6.setVisible(true);
		label7.setVisible(true);
		label8.setVisible(true);
		antwort1.setVisible(true);
		antwort2.setVisible(true);
		antwort3.setVisible(true);
		antwort4.setVisible(true);
		antwort5.setVisible(true);
		antwort6.setVisible(true);
		antwort7.setVisible(true);
		antwort8.setVisible(true);

	}

	private void fillLabelsWithQuestions(ArrayList<String> questionList) {
		switch (questionList.size()) {
		case 2:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			break;
		case 3:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			break;
		case 4:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			label4.setText(questionList.get(3));
			break;
		case 5:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			label4.setText(questionList.get(3));
			label5.setText(questionList.get(4));
			break;
		case 6:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			label4.setText(questionList.get(3));
			label5.setText(questionList.get(4));
			label6.setText(questionList.get(5));
			break;
		case 7:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			label4.setText(questionList.get(3));
			label5.setText(questionList.get(4));
			label6.setText(questionList.get(5));
			label7.setText(questionList.get(6));
			break;
		case 8:
			label1.setText(questionList.get(0));
			label2.setText(questionList.get(1));
			label3.setText(questionList.get(2));
			label4.setText(questionList.get(3));
			label5.setText(questionList.get(4));
			label6.setText(questionList.get(5));
			label7.setText(questionList.get(6));
			label8.setText(questionList.get(7));
			break;
		}
	}

	private void setComboboxChoices(int questionCount) { // Füllt die Comboboxen
		ObservableList<Integer> list = FXCollections.observableArrayList();
		for (int i = 1; i <= questionCount; i++) {
			list.add(i);
		}
		antwort1.setItems(list);
		antwort2.setItems(list);
		antwort3.setItems(list);
		antwort4.setItems(list);
		antwort5.setItems(list);
		antwort6.setItems(list);
		antwort7.setItems(list);
		antwort8.setItems(list);
	}

	private void setNecessaryControls(int questionCount) { // Stellt notwendige
															// Controls sichtbar
		switch (questionCount) {
		case 2:
			label8.setVisible(false);
			label7.setVisible(false);
			label6.setVisible(false);
			label5.setVisible(false);
			label4.setVisible(false);
			label3.setVisible(false);
			antwort8.setVisible(false);
			antwort7.setVisible(false);
			antwort6.setVisible(false);
			antwort5.setVisible(false);
			antwort4.setVisible(false);
			antwort3.setVisible(false);
			break;
		case 3:
			label8.setVisible(false);
			label7.setVisible(false);
			label6.setVisible(false);
			label5.setVisible(false);
			label4.setVisible(false);
			antwort8.setVisible(false);
			antwort7.setVisible(false);
			antwort6.setVisible(false);
			antwort5.setVisible(false);
			antwort4.setVisible(false);
			break;
		case 4:
			label8.setVisible(false);
			label7.setVisible(false);
			label6.setVisible(false);
			label5.setVisible(false);
			antwort8.setVisible(false);
			antwort7.setVisible(false);
			antwort6.setVisible(false);
			antwort5.setVisible(false);
			break;
		case 5:
			label8.setVisible(false);
			label7.setVisible(false);
			label6.setVisible(false);
			antwort8.setVisible(false);
			antwort7.setVisible(false);
			antwort6.setVisible(false);
			break;
		case 6:
			label8.setVisible(false);
			label7.setVisible(false);
			antwort8.setVisible(false);
			antwort7.setVisible(false);
			break;
		case 7:
			label8.setVisible(false);
			antwort8.setVisible(false);
			break;
		}
	}
	
	//SVENS EXTRa

	public void checkQuestion(ActionEvent e) {
		TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] = "";
		ArrayList<String> answers = model.getAnswers(TempData.currentSubject, TempData.currentChapterNumber,
				TempData.currentTaskNumber);
		Iterator<String> it = answers.iterator();
		String[] trueAnswer = new String[answers.size()];
		// Füllen von trueAnswers
		for (int i = 0; it.hasNext(); i++) {
			trueAnswer[i] = it.next().toLowerCase();
		}
		// Füllen von chosenAnswer
		String chosenAnswer[] = new String[getChosenAnswerCount()];
		try {
			fillChosenAnswerArray(chosenAnswer);

			// Aufgabe wird auf bestanden gesetzt
			TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			if (trueAnswer.length != chosenAnswer.length) {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				for (int i = 0; i < getChosenAnswerCount(); i++) {
					TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] += chosenAnswer[i] + ", ";
				}
			} else {
				for (int i = 0; i < trueAnswer.length; i++) {
					if (!(trueAnswer[i].equals(chosenAnswer[i]))) {
						TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] += chosenAnswer[i] + ", ";
						TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
						// ist eine der antworten falsch wird die aufgabe auf
						// nicht
						// bestanden gesetzt
					}
				}
			}
		} catch (NullPointerException ex) {
			TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] = "Es wurden nicht alle Felder ausgefüllt";
		}
		System.out.println(TempData.givenAnswersContainer[TempData.currentTaskNumber - 1]);
		nächsteButtonClicked(e);
	}

	public int getChosenAnswerCount() {
		int counter = 0;
		if (antwort1.isVisible()) {
			counter++;
		}
		if (antwort2.isVisible()) {
			counter++;
		}
		if (antwort3.isVisible()) {
			counter++;
		}
		if (antwort4.isVisible()) {
			counter++;
		}
		if (antwort5.isVisible()) {
			counter++;
		}
		if (antwort6.isVisible()) {
			counter++;
		}
		if (antwort7.isVisible()) {
			counter++;
		}
		if (antwort8.isVisible()) {
			counter++;
		}
		return counter;
	}

	public String[] fillChosenAnswerArray(String[] chosenAnswer) {
		int counter = 0;
		if (antwort1.isVisible()) {
			chosenAnswer[counter] = antwort1.getValue().toString();
			counter++;
		}
		if (antwort2.isVisible()) {
			chosenAnswer[counter] = antwort2.getValue().toString();
			counter++;
		}
		if (antwort3.isVisible()) {
			chosenAnswer[counter] = antwort3.getValue().toString();
			counter++;
		}
		if (antwort4.isVisible()) {
			chosenAnswer[counter] = antwort4.getValue().toString();
			counter++;
		}
		if (antwort5.isVisible()) {
			chosenAnswer[counter] = antwort5.getValue().toString();
			counter++;
		}
		if (antwort6.isVisible()) {
			chosenAnswer[counter] = antwort6.getValue().toString();
			counter++;
		}
		if (antwort7.isVisible()) {
			chosenAnswer[counter] = antwort7.getValue().toString();
			counter++;
		}
		if (antwort8.isVisible()) {
			chosenAnswer[counter] = antwort8.getValue().toString();
			counter++;
		}
		return chosenAnswer;
	}

}
