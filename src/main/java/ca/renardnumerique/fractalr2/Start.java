package ca.renardnumerique.fractalr2;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) {
        MainClass.getInstance().create(stage);        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}