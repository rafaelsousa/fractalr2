package ca.rafaelsousa.fractalr2.ui;

import javafx.scene.*;

public static var instanciaAtual:MainClass;
public class MainClass extends CustomNode  {
		public var alturaSistema=719;
		public var larguraSistema=970;
	  	public var pnlExemplos = new ExamplePanel();
	  	public var pnlBotoes = new ButtonPanel();
		public var pnlFormulas = new FormulaPanel();
		public var pnlTransformacoes = new TransformationPanel();
		public var pnlControle = new ControlPanel();
		public var design = new DesktopLayout();
		public var pencil = Pencil.get();
		    //path for pencil animation
		public var transformacoes : Node[]  = [
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
	   	   
}