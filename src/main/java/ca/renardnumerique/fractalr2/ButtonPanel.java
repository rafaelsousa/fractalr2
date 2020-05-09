package ca.renardnumerique.fractalr2;

import java.util.List;

import javafx.scene.layout.HBox;
import lombok.Data;


@Data
public class ButtonPanel extends HBox {

    private List<ActionButton> botoes;
    private static ButtonPanel instanciaAtual = null;


    private ButtonPanel(){
        this.botoes = ActionButton.getAllButtons();
        this.getChildren().addAll(botoes);
    }

    public static ButtonPanel getInstance(){
        if(instanciaAtual==null){
            instanciaAtual = new ButtonPanel();
        }
        return instanciaAtual;
    }

    public static ActionButton getBotao(Integer codigoBotao) {
        for(ActionButton btn : instanciaAtual.botoes){
            if(btn.getAcaoLSystem().getTipoAcao().equals(codigoBotao)){
                return btn;
            }
        }
        return null;
    }

}