package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class FractalGrama extends Exemplo {

	public FractalGrama(){
        setTitulo("Fractal Grama");
        setFormula("B");
        setTransformacoes(List.of("B=cb-1+b2-B", "1=3+B", "2=+abB", "3=B", "b=bb"));
        setAngulo(25);
    }
	
}
