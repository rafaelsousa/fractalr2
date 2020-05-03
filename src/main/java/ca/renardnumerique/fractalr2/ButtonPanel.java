package ca.renardnumerique.fractalr2;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import lombok.Data;

import java.util.List;


@Data
public class ButtonPanel extends HBox {

    private List<ActionButton> botoes;
    private static ButtonPanel instanciaAtual = null;
    private Line linhaSuperior;


    private ButtonPanel(){
        this.linhaSuperior = new Line(0,0,972,0);
        linhaSuperior.setStroke(Color.web("#cccccc"));
        linhaSuperior.setStrokeWidth(1);
        this.botoes = ActionButton.getAllButtons();
        this.getChildren().addAll(botoes);
        this.getChildren().add(linhaSuperior);
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