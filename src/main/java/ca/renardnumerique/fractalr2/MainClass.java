package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import lombok.Data;


@Data
public class MainClass extends Group  {

		private static MainClass instance = new MainClass();
		public static MainClass getInstance(){
			return instance;
		}

		private DesktopLayout design = new DesktopLayout();

	  	private ExamplePanel pnlExemplos = new ExamplePanel();
	  	private ButtonPanel pnlBotoes = new ButtonPanel();
		private FormulaPanel pnlFormulas = FormulaPanel.getInstance();
		private TransformationPanel pnlTransformacoes = new TransformationPanel();
		private ControlPanel pnlControle = new ControlPanel();
		private Pencil pencil = Pencil.instance;

		//path for pencil animation
		private Group transformacoes = new Group();
		{
			transformacoes.getChildren().add(design);
			transformacoes.getChildren().add(pnlExemplos);
			transformacoes.getChildren().add(pnlFormulas);
			transformacoes.getChildren().add(pnlControle);
			transformacoes.getChildren().add(pnlTransformacoes);
			transformacoes.getChildren().add(pnlBotoes);
			transformacoes.getChildren().add(pencil);
		}

		public MainClass(){
			Pencil.instance.setX(127);
			Pencil.instance.setY(33);
			pencil.setCanvas(design);
			this.getChildren().addAll(design,pnlControle);
		}

		

}