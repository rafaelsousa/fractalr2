package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.ui.ActionButtonsBar;
import ca.renardnumerique.fractalr2.ui.ControlPanel;
import ca.renardnumerique.fractalr2.ui.DesktopLayout;
import ca.renardnumerique.fractalr2.ui.ExamplesPanel;
import ca.renardnumerique.fractalr2.ui.FormulaPanel;
import ca.renardnumerique.fractalr2.ui.Pencil;
import ca.renardnumerique.fractalr2.ui.TransformationPanel;
import ca.renardnumerique.fractalr2.utils.Constants;
import com.jfoenix.assets.JFoenixResources;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ApplicationLayout {

    private DesktopLayout design;

    @Inject
    private ExamplesPanel pnlExemplos;

    @Inject
    private ActionButtonsBar pnlBotoes;

    @Inject
    private FormulaPanel pnlFormulas;

    private List<TransformationPanel> pnlTransformacoes;

    @Inject
    private ControlPanel pnlControle;

    @Inject
    private Pencil pencil;

    private VBox transformations;

    public ApplicationLayout(){
        this.pnlTransformacoes = new ArrayList<>();
        this.transformations = new VBox(8);
    }

    public void create(final Stage stage) {

        //The layout entouring all application.
        final HBox applicationLayoutContainer = new HBox(8);

        //Left size components, the example panel.
        applicationLayoutContainer.getChildren().add(pnlExemplos);

        // Components of the middle column : the ActionButton Panel, the formula panel and the transformation
        // Panel.
        applicationLayoutContainer.getChildren().add(createTransformationColumn());

        // Components of the right column : the ActionButton Panel, the formula panel and the transformation
        // Panel.
        applicationLayoutContainer.getChildren().add(pnlControle);

        applicationLayoutContainer.getChildren().add(pencil.getPencilImage());

        final Scene scene = new Scene(applicationLayoutContainer);
        scene.setFill(Color.web("#CCC"));
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(), "css/fractal_r.css");

        stage.setScene(scene);
        stage.setTitle("Fractal-R Version 1.9 - Beta");
        stage.setResizable(Boolean.FALSE);
        stage.setWidth(Constants.SYSTEM_WIDTH);
        stage.setHeight(Constants.SYSTEM_HEIGHT);
        stage.show();
    }

    private VBox createTransformationColumn() {
        final VBox transformationColumn = new VBox();
        transformationColumn.getChildren().add(pnlBotoes.getContainer());
        transformationColumn.getChildren().add(pnlFormulas);
        transformations.getChildren().add(new TransformationPanel());
        transformationColumn.getChildren().add(transformations);
        return transformationColumn;
    }

    public void addTransformationPanel(TransformationPanel transformationPanel){
        transformations.getChildren().add(transformationPanel);
    }

    public List<TransformationPanel> getTransformations() {
        return this.getTransformations();
    }
}