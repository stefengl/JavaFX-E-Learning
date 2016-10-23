package multipleChoice;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.FragenartController;
import application.TempData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class MultipleChoiceController extends FragenartController implements Initializable {

	@FXML
	CheckBox check;
	@FXML
	CheckBox check1;
	@FXML
	CheckBox check2;
	@FXML
	CheckBox check3;
	@FXML
	CheckBox check4;
	@FXML
	CheckBox check5;
	@FXML
	CheckBox check6;
	@FXML
	CheckBox check7;
	@FXML
	Button loesen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillChapterListView();
		setChapterLabel();
		setSubjectLabel();
		changeChapter();
		setupProgressBar();
		setAufgabe();
	}

	@Override
	public void setAufgabe() { // eine MC Aufgabe laden
		int countAnswer;
		ArrayList<String> answerList = new ArrayList<String>();
		chapterLabel.setText(TempData.currentChapter);
		questionLabel.setText(
				model.getQuestion(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber));
		countAnswer = model.getAnswersCount(TempData.currentSubject, TempData.currentChapterNumber,
				TempData.currentTaskNumber);
		answerList = model.getAnswers(TempData.currentSubject, TempData.currentChapterNumber,
				TempData.currentTaskNumber);
		resetControls();
		setNecessaryControls(countAnswer);
		setTextOnCheckBoxes(answerList);
		System.out.println("Aufgabe wird aufgebaut: " + TempData.currentSubject + " " + TempData.currentChapter + " "
				+ TempData.currentChapterNumber + " " + TempData.currentTaskNumber);

	}

	private void resetControls() {
		check.setVisible(true);
		check1.setVisible(true);
		check1.setVisible(true);
		check2.setVisible(true);
		check3.setVisible(true);
		check4.setVisible(true);
		check5.setVisible(true);
		check6.setVisible(true);
		check7.setVisible(true);
	}

	// Versteckt CheckBoxen je nach Anttwortmöglichkeiten
	private void setNecessaryControls(int counter) {
		switch (counter) {
		case 2:
			check7.setVisible(false);
			check6.setVisible(false);
			check5.setVisible(false);
			check4.setVisible(false);
			check3.setVisible(false);
			check2.setVisible(false);
			break;
		case 3:
			check7.setVisible(false);
			check6.setVisible(false);
			check5.setVisible(false);
			check4.setVisible(false);
			check3.setVisible(false);
			break;
		case 4:
			check7.setVisible(false);
			check6.setVisible(false);
			check5.setVisible(false);
			check4.setVisible(false);
			break;
		case 5:
			check7.setVisible(false);
			check6.setVisible(false);
			check5.setVisible(false);
			break;
		case 6:
			check7.setVisible(false);
			check6.setVisible(false);
			break;
		case 7:
			check7.setVisible(false);
			break;
		default:
			System.out.println("No Answers");
			break;
		}
	}

	private void setTextOnCheckBoxes(ArrayList<String> answerList) {
		int counter = answerList.size();
		switch (counter) {
		case 2:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			break;
		case 3:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			break;
		case 4:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			check3.setText(answerList.get(3));
			break;
		case 5:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			check3.setText(answerList.get(3));
			check4.setText(answerList.get(4));
			break;
		case 6:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			check3.setText(answerList.get(3));
			check4.setText(answerList.get(4));
			check5.setText(answerList.get(5));
			break;
		case 7:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			check3.setText(answerList.get(3));
			check4.setText(answerList.get(4));
			check5.setText(answerList.get(5));
			check6.setText(answerList.get(6));
			break;
		case 8:
			check.setText(answerList.get(0));
			check1.setText(answerList.get(1));
			check2.setText(answerList.get(2));
			check3.setText(answerList.get(3));
			check4.setText(answerList.get(4));
			check5.setText(answerList.get(5));
			check6.setText(answerList.get(6));
			check7.setText(answerList.get(7));
			break;
		default:
			System.out.println("Keine Antwortmöglichkeiten für: " + TempData.currentChapterNumber + " and "
					+ TempData.currentTaskNumber);
			break;
		}
	}

	// SVENS EXTRAS

	@FXML
	public void checkQuestion(ActionEvent e) {
		TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] = "";
		ArrayList<String> answers = model.getTrueAnswers(TempData.currentSubject, TempData.currentChapterNumber,
				TempData.currentTaskNumber);
		Iterator<String> it = answers.iterator();
		String[] trueAnswer = new String[answers.size()];
		for (int i = 0; it.hasNext(); i++) {
			trueAnswer[i] = it.next().toLowerCase();

		}

		String chosenAnswer[] = new String[getChosenAnswerCount()];
		fillChosenAnswerArray(chosenAnswer);

		// Aufgabe wird auf bestanden gesetzt
		TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
		if (trueAnswer.length != chosenAnswer.length) {
			TempData.answersContainer[TempData.currentTaskNumber - 1] = false;

			for (int i = 0; i < getChosenAnswerCount(); i++) {
				TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] += chosenAnswer[i] + ", ";
			}
		}
		// wenn zuviele/zuwenigeantworten ausgewählt wurde, ist dieaufgabe
		// automatisch falsch
		else {
			for (int i = 0; i < trueAnswer.length; i++) {
				TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] += chosenAnswer[i] + ", ";
				if (!(trueAnswer[i].equals(chosenAnswer[i]))) {
					TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
					// ist eine der antworten falsch wird die aufgabe auf nicht
					// bestanden gesetzt
				}
			}
		}
		nächsteButtonClicked(e);
	}

	public int getChosenAnswerCount() {
		int counter = 0;
		if (check.isSelected()) {
			counter++;
		}
		if (check1.isSelected()) {
			counter++;
		}
		if (check2.isSelected()) {
			counter++;
		}
		if (check3.isSelected()) {
			counter++;
		}
		if (check4.isSelected()) {
			counter++;
		}
		if (check5.isSelected()) {
			counter++;
		}
		if (check6.isSelected()) {
			counter++;
		}
		if (check7.isSelected()) {
			counter++;
		}
		return counter;
	}

	public void fillChosenAnswerArray(String[] chosenAnswer) {
		int counter = 0;
		if (check.isSelected()) {
			chosenAnswer[counter] = check.getText().toLowerCase();
			counter++;
		}
		if (check1.isSelected()) {
			chosenAnswer[counter] = check1.getText().toLowerCase();
			counter++;
		}
		if (check2.isSelected()) {
			chosenAnswer[counter] = check2.getText().toLowerCase();
			counter++;
		}
		if (check3.isSelected()) {
			chosenAnswer[counter] = check3.getText().toLowerCase();
			counter++;
		}
		if (check4.isSelected()) {
			chosenAnswer[counter] = check4.getText().toLowerCase();
			counter++;
		}
		if (check5.isSelected()) {
			chosenAnswer[counter] = check5.getText().toLowerCase();
			counter++;
		}
		if (check6.isSelected()) {
			chosenAnswer[counter] = check6.getText().toLowerCase();
			counter++;
		}
		if (check7.isSelected()) {
			chosenAnswer[counter] = check7.getText().toLowerCase();
			counter++;
		}
	}

}
