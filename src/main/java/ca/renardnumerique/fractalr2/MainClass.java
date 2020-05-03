package ca.renardnumerique.fractalr2;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Data;


@Data
public class MainClass {

    private Integer systemHeight = 768;
    private Integer systemWidth = 1024;

    private static MainClass MAINCLASS_INSTANCE;

    private DesktopLayout design;
	private ExamplePanel pnlExemplos;
	private ButtonPanel pnlBotoes;
	private FormulaPanel pnlFormulas = FormulaPanel.getInstance();
    private TransformationPanel pnlTransformacoes;
    private ControlPanel pnlControle;
    private Pencil pencil;
    private Group transformations;


    public static MainClass getInstance() {
        if(MAINCLASS_INSTANCE == null){
            MAINCLASS_INSTANCE = new MainClass();
        }
        return MAINCLASS_INSTANCE;
    }

    private MainClass() {
    }

    public void create(Stage stage) {

        transformations = new Group();
        pnlExemplos = new ExamplePanel();
        pnlControle = new ControlPanel();
        pnlTransformacoes = new TransformationPanel();
        pnlFormulas = FormulaPanel.getInstance();
        pnlBotoes = ButtonPanel.getInstance();


        VBox middleColumn = new VBox();
        middleColumn.getChildren().addAll(pnlBotoes,pnlFormulas,pnlTransformacoes);


        HBox hbox = new HBox(8);
        hbox.setSpacing(10);

        hbox.setMargin(transformations, new Insets(20, 20, 20, 20));
        hbox.setMargin(pnlExemplos, new Insets(20, 20, 20, 20));
        hbox.setMargin(pnlControle, new Insets(20, 20, 20, 20));
        hbox.getChildren().addAll(pnlExemplos,middleColumn);



        design = new DesktopLayout();
        pencil = Pencil.getInstance();
        pencil.setX(127);
        pencil.setY(33);
        pencil.setCanvas(design);

        //hbox.getChildren().add(pencil);
        //createTransformations();

        Scene scene = new Scene(hbox);
        scene.setFill(Color.web("#CCC"));
        stage.setScene(scene);
    }


}