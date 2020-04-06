package ca.renardnumerique.fractalr2;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import lombok.Data;

import java.util.List;


@Data
public class ButtonPanel extends Group  {

    private List<ActionButton> botoes;

    public static ButtonPanel instanciaAtual = new ButtonPanel();

    private ButtonPanel(){
        this.getChildren().addAll(botoes);
        this.getChildren().add(linhaSuperior);
        this.botoes = ActionButton.getBotao();
    }

    private Line linhaSuperior = new Line(0,0,972,0);
    {
        linhaSuperior.setStroke(Color.web("#cccccc"));
        linhaSuperior.setStrokeWidth(1);
    };

    public static ActionButton getBotao(Integer codigoBotao) {
        for(ActionButton btn : instanciaAtual.botoes){
            if(btn.getAcaoLSystem().getTipoAcao().equals(codigoBotao)){
                return btn;
            }
        }
        return null;
    }

}