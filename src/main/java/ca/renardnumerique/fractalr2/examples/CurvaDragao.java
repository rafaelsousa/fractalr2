package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class CurvaDragao extends Exemplo {
	
	public CurvaDragao(){
		setTitulo("Curva do Dragao");
		setFormula("B");
		setTransformacoes(List.of("B=B+Ca+", "C=-aB-C"));
		setAngulo(90);
	}

	
	
	
}
