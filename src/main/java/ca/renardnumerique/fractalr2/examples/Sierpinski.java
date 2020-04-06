package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class Sierpinski extends Exemplo {	
	
    public Sierpinski(){
        setTitulo("Triangulo Sierpinski");
        setFormula("aBa--aa--aa");
        setTransformacoes(List.of("B=--aBa++aBa++aBa--", "a=aa"));
        setAngulo(60);
    }

}
