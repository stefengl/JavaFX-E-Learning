package application;
	
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		TempData.hostServices = getHostServices();
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/startingWindow/StartingWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/application/styling.css");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Computer Aided Learning Platform");
			primaryStage.getIcons().add(new Image("/logo/logo.PNG"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
