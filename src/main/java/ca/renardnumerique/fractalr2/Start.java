package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.utils.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fractal-R Version 1.9 - Beta");
        stage.setResizable(Boolean.FALSE);
        stage.setWidth(Constants.SYSTEM_WIDTH);
        stage.setHeight(Constants.SYSTEM_HEIGHT);
        MainClass.getInstance().create(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}