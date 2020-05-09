package ca.renardnumerique.fractalr2.lsystem;

import java.util.List;

/**
 * @author rafael
 */

public class AcaoExpansiva extends AcaoLSystem {
    
    private List<AcaoLSystem> acoesDerivadas;

    public AcaoExpansiva(Integer acao) {
        super(acao);
    }

    public List<AcaoLSystem> getAcoesDerivadas() {
        return this.acoesDerivadas;
    }

    public void setAcoesDerivadas(List<AcaoLSystem> acoesDerivadas) {
        this.acoesDerivadas = acoesDerivadas;
    }

    
}