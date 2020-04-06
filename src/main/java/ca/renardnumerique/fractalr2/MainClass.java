package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import lombok.Data;


@Data
public class MainClass extends Group {

    private Integer systemHeight = 719;
    private Integer systemWidth = 970;

    private static MainClass MAINCLASS_INSTANCE;

    private DesktopLayout design;
	private ExamplePanel pnlExemplos;
	private ButtonPanel pnlBotoes;
	private FormulaPanel pnlFormulas = FormulaPanel.getInstance();
	private TransformationPanel pnlTransformacoes;
	private ControlPanel pnlControle;
	private Pencil pencil;

    public static MainClass getInstance() {
        if(MAINCLASS_INSTANCE == null){
            MAINCLASS_INSTANCE = new MainClass();
        }
        return MAINCLASS_INSTANCE;
    }

    private MainClass() {
    }

    private Group transformations;

    public void create() {
        transformations = new Group();
        transformations.getChildren().add(pnlExemplos);
        transformations.getChildren().add(pnlFormulas);
        transformations.getChildren().add(pnlControle);
        transformations.getChildren().add(pnlTransformacoes);
        transformations.getChildren().add(pnlBotoes);
        transformations.getChildren().add(pencil);
        design = new DesktopLayout();
        pencil = Pencil.getInstance();
        pencil.setX(127);
        pencil.setY(33);
        pencil.setCanvas(design);
        pnlBotoes = ButtonPanel.getInstance();
        //createTransformations();
        this.getChildren().addAll(design, pnlControle);
    }


}