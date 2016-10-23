package bildschirmausgabe;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.FragenartController;
import application.TempData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BildschirmausgabeController extends FragenartController implements Initializable {

	@FXML
	private ImageView codeImageVIew;
	@FXML
	private TextField answerTextfield1;
	@FXML
	private TextField answerTextfield2;
	@FXML
	private TextField answerTextfield3;
	@FXML
	private TextField answerTextfield4;
	@FXML
	private TextField answerTextfield5;
	@FXML
	private TextField answerTextfield6;
	@FXML
	private TextField answerTextfield7;
	@FXML
	private TextField answerTextfield8;

	@FXML
	private Label checkLabel;

	public ArrayList<String> answers = new ArrayList<String>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillChapterListView();
		setSubjectLabel();
		setChapterLabel();
		changeChapter();
		setupProgressBar();
		setAufgabe();
	}

	@Override
	public void setAufgabe() {
		int answerCounter;
		String codeImageUrl = "";
		codeImageUrl = model.getPfad(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber, "F");
		Image image = new Image(codeImageUrl);
		codeImageVIew.setImage(image);
		answerCounter = model.getAnswersCount(TempData.currentSubject, TempData.currentChapterNumber,TempData.currentTaskNumber);
		resetControls();
		setControls(answerCounter);
		answers = model.getAnswers(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber);
		TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] = "Bildschirmausgabe";
		System.out.println("Aufgabe wird aufgebaut: " + TempData.currentChapter + "'s Aufgabe Nr." + TempData.currentTaskNumber);

	}

	private void resetControls() {
		answerTextfield1.setVisible(true);
		answerTextfield2.setVisible(true);
		answerTextfield3.setVisible(true);
		answerTextfield4.setVisible(true);
		answerTextfield5.setVisible(true);
		answerTextfield6.setVisible(true);
		answerTextfield7.setVisible(true);
		answerTextfield8.setVisible(true);
		answerTextfield1.setStyle("-fx-text-fill: black");
		answerTextfield2.setStyle("-fx-text-fill: black");
		answerTextfield3.setStyle("-fx-text-fill: black");
		answerTextfield4.setStyle("-fx-text-fill: black");
		answerTextfield5.setStyle("-fx-text-fill: black");
		answerTextfield6.setStyle("-fx-text-fill: black");
		answerTextfield7.setStyle("-fx-text-fill: black");
		answerTextfield8.setStyle("-fx-text-fill: black");
		answerTextfield1.setEditable(true);
		answerTextfield2.setEditable(true);
		answerTextfield3.setEditable(true);
		answerTextfield4.setEditable(true);
		answerTextfield5.setEditable(true);
		answerTextfield6.setEditable(true);
		answerTextfield7.setEditable(true);
		answerTextfield8.setEditable(true);
	}

	private void setControls(int answerCounter) {
		switch (answerCounter) {
		case 1:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(false);
			answerTextfield3.setVisible(false);
			answerTextfield4.setVisible(false);
			answerTextfield5.setVisible(false);
			answerTextfield6.setVisible(false);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);
			break;
		case 2:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(false);
			answerTextfield4.setVisible(false);
			answerTextfield5.setVisible(false);
			answerTextfield6.setVisible(false);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);

			break;
		case 3:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(false);
			answerTextfield5.setVisible(false);
			answerTextfield6.setVisible(false);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);

			break;
		case 4:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(true);
			answerTextfield5.setVisible(false);
			answerTextfield6.setVisible(false);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);

			break;
		case 5:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(true);
			answerTextfield5.setVisible(true);
			answerTextfield6.setVisible(false);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);
			break;
		case 6:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(true);
			answerTextfield5.setVisible(true);
			answerTextfield6.setVisible(true);
			answerTextfield7.setVisible(false);
			answerTextfield8.setVisible(false);
			break;
		case 7:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(true);
			answerTextfield5.setVisible(true);
			answerTextfield6.setVisible(true);
			answerTextfield7.setVisible(true);
			answerTextfield8.setVisible(false);
			break;
		case 8:
			answerTextfield1.setVisible(true);
			answerTextfield2.setVisible(true);
			answerTextfield3.setVisible(true);
			answerTextfield4.setVisible(true);
			answerTextfield5.setVisible(true);
			answerTextfield6.setVisible(true);
			answerTextfield7.setVisible(true);
			answerTextfield8.setVisible(true);
			break;
		default:
			System.out.println("Zu wenige Eingabefelder");
		}
	}

	public void checkQuestion(ActionEvent e) {
		String answersString = "";
		int counter = answers.size();
		switch (counter) {
		case 1:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
				answerTextfield1.setEditable(false);
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText();
			}
			break;
		case 2:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}

			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText();
			}
			break;
		case 3:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText();
			}
			break;
		case 4:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText() + "; ";
			}
			if (answerTextfield4.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield4.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield4.getText();
			}
			break;
		case 5:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText() + "; ";
			}
			if (answerTextfield4.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield4.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield4.getText() + "; ";
			}
			if (answerTextfield5.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield5.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield5.getText();
			}
			break;
		case 6:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText() + "; ";
			}
			if (answerTextfield4.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield4.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield4.getText() + "; ";
			}
			if (answerTextfield5.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield5.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield5.getText() + "; ";
			}
			if (answerTextfield6.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield5.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield6.getText();
			}
			break;
		case 7:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText() + "; ";
			}
			if (answerTextfield4.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield4.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield4.getText() + "; ";
			}
			if (answerTextfield5.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield5.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield5.getText() + "; ";
			}
			if (answerTextfield6.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield6.getText() + ", ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield6.getText() + ", ";
			}
			if (answerTextfield7.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield7.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield7.getText();
			}
			break;
		case 8:
			if (answerTextfield1.getText().equalsIgnoreCase(answers.get(0))) {
				answersString += answerTextfield1.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield1.getText() + "; ";
			}
			if (answerTextfield2.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield2.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield2.getText() + "; ";
			}
			if (answerTextfield3.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield3.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield3.getText() + "; ";
			}
			if (answerTextfield4.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield4.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield4.getText() + "; ";
			}
			if (answerTextfield5.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield5.getText() + "; ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield5.getText() + "; ";
			}
			if (answerTextfield6.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield6.getText() + ", ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield6.getText() + ", ";
			}
			if (answerTextfield7.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield7.getText() +", ";
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield7.getText() +", ";
			}
			if (answerTextfield8.getText().equalsIgnoreCase(answers.get(1))) {
				answersString += answerTextfield8.getText();
				TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			} else {
				TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
				answersString += answerTextfield8.getText();
			}
			break;
		}
		TempData.givenAnswersContainer[TempData.currentTaskNumber - 1 ] = answersString;
		nächsteButtonClicked(e);
	}

}
