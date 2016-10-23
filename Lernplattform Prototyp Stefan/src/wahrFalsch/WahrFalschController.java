package wahrFalsch;

import java.net.URL;
import java.util.ResourceBundle;

import application.FragenartController;
import application.TempData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class WahrFalschController extends FragenartController implements Initializable {

	@FXML
	private Button wahrButton;
	@FXML
	private Button falschButton;
	@FXML
	private Button vorherigeButton;
	@FXML
	private Button nächsteButton;
	@FXML
	private Label subjectLabel;
	@FXML
	private Label chapterLabel;
	@FXML
	private Label questionLabel;
	@FXML 
	private ImageView logoImage;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //Kapitelübersicht laden
		fillChapterListView();
		setSubjectLabel();
		changeChapter();
		setupProgressBar();
		setAufgabe();
	}
	
	@Override
	public void setAufgabe(){
		chapterLabel.setText(TempData.currentChapter);
		questionLabel.setText(model.getQuestion(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber));
		System.out.println("Aufgabe wird aufgebaut: "+ TempData.currentSubject + " " + TempData.currentChapter + " " + TempData.currentChapterNumber + " " + TempData.currentTaskNumber);

	}
	
	
	@FXML
	public void checkQuestion (ActionEvent e){
		System.out.println("CheckQustion gestartet");
		Boolean trueAnswer = model.getAnswersBoolean(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber);
		boolean chosenAnswer;
		
		if(((Button)e.getSource()).getText().toLowerCase().equals("wahr")){
			chosenAnswer = true;
		}else chosenAnswer = false;
		
		System.out.println("trueAnswer:" + trueAnswer);
		System.out.println("ChosenAnswer:" +chosenAnswer);
		
		if(trueAnswer.equals(chosenAnswer)){	
			System.out.println("Antwort ist Richtig!");
			TempData.answersContainer[TempData.currentTaskNumber - 1] = true;
			TempData.givenAnswersContainer[TempData.currentTaskNumber -1] = "Wahr";
		}else{
			System.out.println("Antwort ist Falsch!");
			TempData.answersContainer[TempData.currentTaskNumber - 1] = false;
			TempData.givenAnswersContainer[TempData.currentTaskNumber -1] = "Falsch";
		}
		System.out.println("TempData.currentTaskNumber ist:" + TempData.currentTaskNumber);
		System.out.println("Inhalt von answersContainer: " + TempData.answersContainer[TempData.currentTaskNumber - 1]);
		nächsteButtonClicked(e);	
	}
}
