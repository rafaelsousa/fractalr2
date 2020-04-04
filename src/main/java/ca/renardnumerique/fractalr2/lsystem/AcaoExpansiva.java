package ca.renardnumerique.fractalr2.lsystem;

import lombok.Data;

import java.util.List;

/**
 * @author rafael
 */

@Data
public class AcaoExpansiva extends AcaoLSystem {
    
    private List<AcaoLSystem> acoesDerivadas;

    public AcaoExpansiva(Integer tipoAcao, String simbolo, Integer cor) {
        super(tipoAcao, simbolo, cor);
    }

}