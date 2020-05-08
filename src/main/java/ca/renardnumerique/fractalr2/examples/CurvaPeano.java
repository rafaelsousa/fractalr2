package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class CurvaPeano extends Exemplo {
		
	public CurvaPeano() {		
        setText("Curva de Peano");
        setFormula("a");
        setTransformacoes(List.of("a=a-a+a+a+a-a-a-a+a"));
        setAngulo(90);
    }

}
