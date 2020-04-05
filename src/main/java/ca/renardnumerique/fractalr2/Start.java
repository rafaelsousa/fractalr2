package ca.renardnumerique.fractalr2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fractal-R Version 1.9 - Beta");
        stage.setResizable(Boolean.FALSE);
        stage.setWidth(MainClass.getInstance().getLarguraSistema());
        stage.setHeight(MainClass.getInstance().getAlturaSistema());
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Scene scene = new Scene(MainClass.getInstance());
        scene.setFill(Color.web("#CCC"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}