import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		URL url = getClass().getResource("FractalR.fxml");
		
		Parent parent = (Parent) FXMLLoader.load(url);
		
		Scene scene = new Scene(parent);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Fractal-R");
	}
	
	
	
	
	
	
}
