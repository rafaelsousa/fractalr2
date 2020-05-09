package ca.renardnumerique.fractalr2;

import java.util.ArrayList;
import java.util.List;

import ca.renardnumerique.fractalr2.lsystem.AcaoExpansiva;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.layout.HBox;

public class ButtonPanel extends HBox {

    private List<ActionButton> botoes;

    public ButtonPanel(){
        this.botoes = getAllButtons();
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
    
    private List<ActionButton> getAllButtons(){

        List<ActionButton> botoes = new ArrayList<>();
        ActionButton botao = new ActionButton();
        botao.setText("Draw");
        botao.setCoresSeletor(new ColorSelector());
        botao.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_ANDAR));
        botao.setIconeUrl("images/botoes/anda.png");
        botoes.add(botao);

        ActionButton botaoProduce = new ActionButton();
        botaoProduce.setText("Produce");
        botaoProduce.setCoresSeletor(new ColorSelector());
        botaoProduce.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_EXPANDIR));
        botaoProduce.setIconeUrl("images/botoes/legalmoves.png");
        botoes.add(botaoProduce);

        ActionButton botaoTurnLeft = new ActionButton();
        botaoTurnLeft.setText("Turn Left");
        botaoTurnLeft.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_ESQUERDA));
        botaoTurnLeft.setIconeUrl("images/botoes/esquerda.png");
        botoes.add(botaoTurnLeft);

        ActionButton botaoTurnRight = new ActionButton();
        botaoTurnRight.setText("Turn Right");
        botaoTurnRight.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_DIREITA));
        botaoTurnRight.setIconeUrl("images/botoes/direita.png");
        botoes.add(botaoTurnRight);

        ActionButton doAndReturnButton = new ActionButton();
        doAndReturnButton.setText("Do and return");
        doAndReturnButton.setCoresSeletor(new ColorSelector());
        doAndReturnButton.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_FAZER_RETORNAR));
        doAndReturnButton.setIconeUrl("images/botoes/fazerRetornar.png");
        botoes.add(doAndReturnButton);
        
        return botoes;
    }

    
}