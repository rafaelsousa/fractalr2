package ca.renardnumerique.fractalr2;

import java.util.List;

import javafx.scene.layout.HBox;

public class ButtonPanel extends HBox {

    private List<ActionButton> botoes;

    public ButtonPanel(){
        this.botoes = ActionButton.getAllButtons();
        this.getChildren().addAll(botoes);
    }


    public ActionButton findButton(Integer codigoBotao) {
        for(ActionButton btn : botoes){
            if(btn.getAcaoLSystem().getTipoAcao().equals(codigoBotao)){
                return btn;
            }
        }
        return null;
    }

    public List<ActionButton> getBotoes() {
        return botoes;
    }

    public void setBotoes(List<ActionButton> botoes) {
        this.botoes = botoes;
    }

    
}