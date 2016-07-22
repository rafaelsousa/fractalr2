package com.github.rafaelsousa.fractalr2.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/*
 * Eclipse note : to fix the annoying error :
 *   Access restriction: The type 'Application' is not API('C:\Program Files\Java\jre8\lib\ext\jfxrt.jar')
 * 
 * Go to Build Path/ JRE/ Access rules/ Edit/ Add/ Accessible: javafx/**
 * 
 * 
 */

public class FractalR2 extends Application {

    private static final Logger log = LoggerFactory.getLogger(FractalR2.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
    	
    	StackPane root = new StackPane();

    	Scene scene = new Scene(root,800,600);

        stage.setTitle("Fractal by Representations - Ver 2.0");
        stage.setScene(scene);
        stage.show();
    }
}
