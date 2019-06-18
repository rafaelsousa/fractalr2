package ca.rafaelsousa.fractalr2.ui;

import javafx.scene.*;




public class MainClass extends Node  {

		public static MainClass instanciaAtual;


		public Integer alturaSistema=719;
		public Integer larguraSistema=970;
	  	public ExamplePanel pnlExemplos = new ExamplePanel();
	  	public ButtonPanel pnlBotoes = new ButtonPanel();
		public FormulaPanel pnlFormulas = new FormulaPanel();
		public TransformationPanel pnlTransformacoes = new TransformationPanel();
		public ControlPanel pnlControle = new ControlPanel();
		public DesktopLayout design = new DesktopLayout();
		public Integer pencil = Pencil.get();

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