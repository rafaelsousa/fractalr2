package ca.renardnumerique.fractalr2;

import com.jfoenix.assets.JFoenixResources;

import ca.renardnumerique.fractalr2.utils.Constants;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainClass {

    private DesktopLayout design;
    private ExamplePanel pnlExemplos;
    private ButtonPanel pnlBotoes;
    private FormulaPanel pnlFormulas;
    private TransformationPanel pnlTransformacoes;
    private ControlPanel pnlControle;
    private Pencil pencil;
    private Group transformations;

    @Inject
    public MainClass(DesktopLayout design, 
            ExamplePanel pnlExemplos, 
            ButtonPanel pnlBotoes, 
            FormulaPanel pnlFormulas,
            TransformationPanel pnlTransformacoes, 
            ControlPanel pnlControle, 
            Pencil pencil) {
        this.design = design;
        this.pnlExemplos = pnlExemplos;
        this.pnlBotoes = pnlBotoes;
        this.pnlFormulas = pnlFormulas;
        this.pnlTransformacoes = pnlTransformacoes;
        this.pnlControle = pnlControle;
        this.pencil = pencil;
        this.transformations = new Group();
    }

    

    public void create(Stage stage) {

        transformations = new Group();
        pnlExemplos = new ExamplePanel();
        pnlControle = new ControlPanel();

        VBox transformationColumn = createTransformationColumn();
        HBox hbox = new HBox(8);
        hbox.getChildren().addAll(pnlExemplos, transformationColumn);
        design = new DesktopLayout();
        pencil = Pencil.getInstance();
        pencil.setX(127);
        pencil.setY(33);
        pencil.setCanvas(design);

        // hbox.getChildren().add(pencil);
        // createTransformations();

        Scene scene = new Scene(hbox);
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
        VBox transformationColumn = new VBox();
        transformationColumn.getChildren().addAll(pnlBotoes, pnlFormulas, pnlTransformacoes);
        return transformationColumn;
    }

    public DesktopLayout getDesign() {
        return this.design;
    }

    public void setDesign(DesktopLayout design) {
        this.design = design;
    }

    public ExamplePanel getPnlExemplos() {
        return this.pnlExemplos;
    }

    public void setPnlExemplos(ExamplePanel pnlExemplos) {
        this.pnlExemplos = pnlExemplos;
    }

    public ButtonPanel getPnlBotoes() {
        return this.pnlBotoes;
    }

    public void setPnlBotoes(ButtonPanel pnlBotoes) {
        this.pnlBotoes = pnlBotoes;
    }

    public FormulaPanel getPnlFormulas() {
        return this.pnlFormulas;
    }

    public void setPnlFormulas(FormulaPanel pnlFormulas) {
        this.pnlFormulas = pnlFormulas;
    }

    public TransformationPanel getPnlTransformacoes() {
        return this.pnlTransformacoes;
    }

    public void setPnlTransformacoes(TransformationPanel pnlTransformacoes) {
        this.pnlTransformacoes = pnlTransformacoes;
    }

    public ControlPanel getPnlControle() {
        return this.pnlControle;
    }

    public void setPnlControle(ControlPanel pnlControle) {
        this.pnlControle = pnlControle;
    }

    public Pencil getPencil() {
        return this.pencil;
    }

    public void setPencil(Pencil pencil) {
        this.pencil = pencil;
    }

    public Group getTransformations() {
        return this.transformations;
    }

    public void setTransformations(Group transformations) {
        this.transformations = transformations;
    }

}