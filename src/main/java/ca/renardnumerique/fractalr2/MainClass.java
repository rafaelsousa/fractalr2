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
		private Pencil pencil = Pencil.get();
		//path for pencil animation
		private var transformacoes : Node[]  = [
									design,
				               		pnlExemplos,
				               		pnlFormulas,
				               		pnlControle,
				               		pnlTransformacoes,
				               		pnlBotoes,
				               		pencil
				               		];
	   override public function create(): Node {
	       	   instanciaAtual=this;
				pencil.x=127;
				pencil.y=33;
		  	var grupo = Group{
		           	content:bind transformacoes
		      }
		      pencil.canvas = MainClass.instanciaAtual.design.canvas;
		      return grupo;
	    }
		
		
		private MainClass() {
			this.getChildren().addAll(design,pnlControle);
		}
		
	   	   
}