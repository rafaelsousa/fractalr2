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
    private Group transformations;


    public static MainClass getInstance() {
        if(MAINCLASS_INSTANCE == null){
            MAINCLASS_INSTANCE = new MainClass();
        }
        return MAINCLASS_INSTANCE;
    }

    private MainClass() {
    }

    public void create() {
        transformations = new Group();
        pnlExemplos = new ExamplePanel();
        getChildren().add(pnlExemplos);

        pnlFormulas = FormulaPanel.getInstance();
        getChildren().add(pnlFormulas);

        pnlControle = new ControlPanel();
        getChildren().add(pnlControle);

        pnlTransformacoes = new TransformationPanel();
        getChildren().add(pnlTransformacoes);

        pnlBotoes = ButtonPanel.getInstance();
        getChildren().add(pnlBotoes);


        design = new DesktopLayout();
        pencil = Pencil.getInstance();
        pencil.setX(127);
        pencil.setY(33);
        pencil.setCanvas(design);

        getChildren().add(pencil);
        //createTransformations();
        this.getChildren().add(design);
    }


}