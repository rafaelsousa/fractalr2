package ca.renardnumerique.fractalr2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//var mainClass = MainClass{};//para acertar o escopo das variaveis
//Stage {
//    title: "Fractal-R Version 0.9 - Beta"
//    resizable: false
//    width:  bind mainClass.larguraSistema;
//    height : bind mainClass.alturaSistema;
//    scene: Scene {
//    	content:[
//    		mainClass
//    		]
//		fill:Color.web("#ccc")
//    }  
//
//}


public class Start extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(MainClass.instance);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}