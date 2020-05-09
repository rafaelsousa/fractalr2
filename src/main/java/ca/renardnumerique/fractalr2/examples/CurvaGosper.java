package ca.renardnumerique.fractalr2.examples;

import java.util.List;

public class CurvaGosper extends Exemplo {

	public CurvaGosper() {
        setText("Curva de Gosper");
        setFormula("a");
        setTransformacoes(List.of("a=a+b++b-a--aa-b+ ", "b=-a+bb++b+a--a-b"));
        setAngulo(60);
    }

}
