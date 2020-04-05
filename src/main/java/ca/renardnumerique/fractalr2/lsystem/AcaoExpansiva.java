package ca.renardnumerique.fractalr2.lsystem;

import javafx.beans.NamedArg;
import lombok.Data;

import java.util.List;

/**
 * @author rafael
 */

@Data
public class AcaoExpansiva extends AcaoLSystem {
    
    private List<AcaoLSystem> acoesDerivadas;

    public AcaoExpansiva(Integer acao) {
        super(acao);
    }
}