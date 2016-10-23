package bildFrageBildAntwort;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import application.FragenartController;
import application.TempData;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BfbaController extends FragenartController implements Initializable {
	
	@FXML
	private ImageView ImageView;
	@FXML
	private ImageView solutionImageView;
	@FXML
	private Button loesungsButton;

	static int counter = 0;

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
		String ImageUrl = "";
		ImageUrl = model.getPfad(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber, "F");
		Image image = new Image(ImageUrl);
		ImageView.setImage(image);
		TempData.givenAnswersContainer[TempData.currentTaskNumber - 1] = "bfba";
		System.out.println("Aufgabe wird aufgebaut: "+ TempData.currentChapter + "'s Aufgabe Nr." + TempData.currentTaskNumber);
		
	}
	
	@FXML
	private void displayLoesung(ActionEvent event) {
		if (counter%2 == 0){
			String imgUrl = "";
			imgUrl = model.getPfad(TempData.currentSubject, TempData.currentChapterNumber, TempData.currentTaskNumber, "L");
			Image image = new Image(imgUrl);
			solutionImageView.setImage(image);
			solutionImageView.setVisible(true);
			ImageView.setVisible(false);
			counter = counter + 1;
		} else {
			ImageView.setVisible(true);
			solutionImageView.setVisible(false);
			counter = counter + 1;
		}
	}	
}
