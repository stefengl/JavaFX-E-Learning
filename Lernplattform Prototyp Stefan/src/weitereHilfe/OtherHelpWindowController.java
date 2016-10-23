package weitereHilfe;

import java.net.URL;
import java.util.ResourceBundle;

import application.TempData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.ApplicationModel;

public class OtherHelpWindowController implements Initializable {
	@FXML
	private Label title1;
	@FXML
	private Label title4;
	@FXML
	private Label title2;
	@FXML
	private Label title3;
	@FXML
	private Hyperlink link1;
	@FXML
	private Hyperlink link2;
	@FXML
	private Hyperlink link3;
	@FXML
	private Hyperlink link4;

	private ApplicationModel model = new ApplicationModel();

	private String[][] extraHelpArray;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Weitere Hilfe für : " + TempData.currentSubject);
		extraHelpArray = model.getExtraHelp(TempData.currentSubject);
		if (isExtraHelpAvailable()){
			setNecessaryControls(extraHelpArray.length);
		}
	}

	private boolean isExtraHelpAvailable() {
		if (extraHelpArray == null){
			return false;
		} else 
			return true;
	}

	private void setNecessaryControls(int length) {
		resetControls();
		switch (length) {
		case 0:
			System.out.println("Keine weitere Hilfe verfügbar");
			break;
		case 1:
			title1.setText(extraHelpArray[0][0]);
			link1.setText(extraHelpArray[0][1]);
			title2.setVisible(false);
			title3.setVisible(false);
			title4.setVisible(false);
			link2.setVisible(false);
			link3.setVisible(false);
			link4.setVisible(false);
			break;
		case 2:		
			title1.setText(extraHelpArray[0][0]);
			link1.setText(extraHelpArray[0][1]);
			title2.setText(extraHelpArray[1][0]);
			link2.setText(extraHelpArray[1][1]);
			title3.setVisible(false);
			title4.setVisible(false);
			link3.setVisible(false);
			link4.setVisible(false);
			break;
		case 3:
			title1.setText(extraHelpArray[0][0]);
			link1.setText(extraHelpArray[0][1]);
			title2.setText(extraHelpArray[1][0]);
			link2.setText(extraHelpArray[1][1]);
			title3.setText(extraHelpArray[2][0]);
			link3.setText(extraHelpArray[2][1]);
			title4.setVisible(false);
			link4.setVisible(false);
			break;
		case 4:
			title1.setText(extraHelpArray[0][0]);
			link1.setText(extraHelpArray[0][1]);
			title2.setText(extraHelpArray[1][0]);
			link2.setText(extraHelpArray[1][1]);
			title3.setText(extraHelpArray[2][0]);
			link3.setText(extraHelpArray[2][1]);
			title4.setText(extraHelpArray[3][0]);
			link4.setText(extraHelpArray[3][1]);
			break;
		default:
			break;
		}
	}

	private void resetControls() {
		title1.setVisible(true);
		title2.setVisible(true);
		title3.setVisible(true);
		title4.setVisible(true);
		link1.setVisible(true);
		link2.setVisible(true);
		link3.setVisible(true);
		link4.setVisible(true);
	}
	
	@FXML
	public void hyperlinkClicked (ActionEvent e ){
		Hyperlink link = (Hyperlink) e.getSource();
		String url = link.getText();
		TempData.hostServices.showDocument(url);
	}

}
