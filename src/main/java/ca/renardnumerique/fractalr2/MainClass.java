package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import lombok.Data;


@Data
public class MainClass extends Group {

    private Integer systemHeight = 719;
    private Integer systemWidth = 970;

    private static MainClass instance = new MainClass();
	private DesktopLayout design = new DesktopLayout();
	private ExamplePanel pnlExemplos = new ExamplePanel();
	private ButtonPanel pnlBotoes;
	private FormulaPanel pnlFormulas = FormulaPanel.getInstance();
	private TransformationPanel pnlTransformacoes = new TransformationPanel();
	private ControlPanel pnlControle = new ControlPanel();
	private Pencil pencil = Pencil.instance;

    public static MainClass getInstance() {
        return instance;
    }

    public MainClass() {
        Pencil.instance.setX(127);
        Pencil.instance.setY(33);
        pencil.setCanvas(design);
        pnlBotoes = ButtonPanel.instanciaAtual;
        createTransformations();
        this.getChildren().addAll(design, pnlControle);
    }

    private Group transformations;

    private void createTransformations() {
        transformations = new Group();
        transformations.getChildren().add(design);
        transformations.getChildren().add(pnlExemplos);
        transformations.getChildren().add(pnlFormulas);
        transformations.getChildren().add(pnlControle);
        transformations.getChildren().add(pnlTransformacoes);
        transformations.getChildren().add(pnlBotoes);
        transformations.getChildren().add(pencil);
    }


}