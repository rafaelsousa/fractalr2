package ca.renardnumerique.fractalr2;

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
        
        VBox transformationColumn = createTransformationColumn();
        
        HBox hbox = new HBox(8);        
        hbox.getChildren().addAll(pnlExemplos,transformationColumn);



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

    private VBox createTransformationColumn() {
        pnlTransformacoes = new TransformationPanel();
        pnlFormulas = FormulaPanel.getInstance();
        pnlBotoes = ButtonPanel.getInstance();
        VBox transformationColumn = new VBox();
        transformationColumn.getChildren().addAll(pnlBotoes,pnlFormulas,pnlTransformacoes);
        return transformationColumn;
    }


}