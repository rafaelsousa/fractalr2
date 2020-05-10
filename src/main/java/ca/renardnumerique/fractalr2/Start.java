package ca.renardnumerique.fractalr2;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) {
        SeContainer container = SeContainerInitializer.newInstance()
            .disableDiscovery()
            .addPackages(Boolean.TRUE,Start.class)
            .initialize();
        container.getBeanManager().createInstance().select(ApplicationLayout.class).get().create(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}