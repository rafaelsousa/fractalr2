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
		
	  	//public var pnlExemplos = new ExamplePanel();
	  	//public var pnlBotoes = new ButtonPanel();
		//public var pnlFormulas = new FormulaPanel();
		//public var pnlTransformacoes = new TransformationPanel();
		private ControlPanel pnlControle = new ControlPanel();
		//public var pencil = Pencil.get();
		    //path for pencil animation
//		public var transformacoes : Node[]  = [
//									design,
//				               		pnlExemplos,
//				               		pnlFormulas,
//				               		pnlControle,
//				               		pnlTransformacoes,
//				               		pnlBotoes,
//				               		pencil
//				               		];
//	   override public function create(): Node {
//	       	   instanciaAtual=this;
//				pencil.x=127;
//				pencil.y=33;
//		  	var grupo = Group{
//		           	content:bind transformacoes 
//		      }
//		      pencil.canvas = MainClass.instanciaAtual.design.canvas;
//		      return grupo;
//	    }
		
		
		private MainClass() {
			this.getChildren().addAll(design,pnlControle);
		}
		
	   	   
}