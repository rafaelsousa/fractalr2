package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.ui.ActionButtonsBar;
import ca.renardnumerique.fractalr2.ui.ApplicationCanvas;
import ca.renardnumerique.fractalr2.ui.ControlPanel;
import ca.renardnumerique.fractalr2.ui.ExamplesPanel;
import ca.renardnumerique.fractalr2.ui.FormulaPanel;
import ca.renardnumerique.fractalr2.ui.Pencil;
import ca.renardnumerique.fractalr2.ui.TransformationPanel;
import ca.renardnumerique.fractalr2.utils.Constants;
import ca.renardnumerique.fractalr2.utils.CoordenatesUtils;
import com.jfoenix.assets.JFoenixResources;
import jakarta.inject.Inject;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ApplicationLayout {

    @Inject
    private CoordenatesUtils coordenatesUtils;

    @Inject
    private ExamplesPanel pnlExemplos;

    @Inject
    private ActionButtonsBar actionButtonsBar;

    @Inject
    private FormulaPanel formulaPanel;

    private List<TransformationPanel> pnlTransformacoes;

    @Inject
    private ControlPanel pnlControle;

    @Inject
    private Pencil pencil;

    @Inject
    private ApplicationCanvas applicationCanvas;

    private Rectangle fundoSistema;


    private VBox transformations;

    public ApplicationLayout(){
        this.pnlTransformacoes = new ArrayList<>();
        this.transformations = new VBox(8);
    }

    public void create(Stage stage) {
        animatePencil();
        Group root = new Group();
        root.getChildren().add(pencil.getPencilContainer());

        //The layout entouring all application.
        final HBox applicationLayoutContainer = new HBox(8);
        root.getChildren().add(applicationLayoutContainer);

        applicationLayoutContainer.setShape(fundoSistema);

        //Left size components, the example panel.
        applicationLayoutContainer.getChildren().add(pnlExemplos);

        // Components of the middle column : the ActionButton Panel, the formula panel, the transformation
        // Panel and the canvas.
        applicationLayoutContainer.getChildren().add(createMiddleColumn());

        // Components of the right column : the ActionButton Panel, the formula panel and the transformation
        // Panel.
        applicationLayoutContainer.getChildren().add(pnlControle);

        final Scene scene = new Scene(root);
        scene.setFill(Color.web("#CCC"));
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(), "css/fractal_r.css");

        stage.setScene(scene);
        stage.setTitle("Fractal-R Version 1.9 - Beta");
        stage.setResizable(Boolean.TRUE);
        stage.setWidth(Constants.SYSTEM_WIDTH);
        stage.setHeight(Constants.SYSTEM_HEIGHT);

        pencil.toFront();
        stage.show();
    }

    private void animatePencil() {

        double canvasY = coordenatesUtils.getAbsoluteCoordenateY(applicationCanvas.getAreaDesenho());
        double canvasX = coordenatesUtils.getAbsoluteCoordenateX(applicationCanvas.getAreaDesenho());

        Path path = new Path();
        path.getElements().add (new MoveTo(0, 0));
        path.getElements().add (new LineTo(canvasX,canvasY));

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setNode(pencil.getPencilContainer());
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(Boolean.FALSE);

        pathTransition.play();
    }

    private VBox createMiddleColumn() {
        final VBox middleColumn = new VBox(8);
        middleColumn.getChildren().add(actionButtonsBar.getContainer());
        middleColumn.getChildren().add(formulaPanel);
        transformations.getChildren().add(new TransformationPanel());
        middleColumn.getChildren().add(transformations);
        middleColumn.getChildren().addAll(applicationCanvas.getContainer());
        middleColumn.setMinWidth(500);
        return middleColumn;
    }

    public void addTransformationPanel(TransformationPanel transformationPanel){
        transformations.getChildren().add(transformationPanel);
    }

    public List<TransformationPanel> getTransformations() {
        return this.getTransformations();
    }
}