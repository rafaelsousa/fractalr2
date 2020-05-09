package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class FlocoNeve extends Exemplo {
	
	public FlocoNeve(){
        super();
        setText("Floco de neve Koch");
        setFormula("a--a--a");
        setTransformacoes(List.of("a=a+a--a+a"));
        setAngulo(60);
    }

}
