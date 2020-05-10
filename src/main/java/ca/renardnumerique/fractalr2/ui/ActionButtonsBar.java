package ca.renardnumerique.fractalr2.ui;

import java.util.ArrayList;
import java.util.List;

import ca.renardnumerique.fractalr2.lsystem.AcaoExpansiva;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ActionButtonsBar {

    private List<ActionButton> botoes;
    private HBox container = null;
    private ImageView clean;

    public ActionButtonsBar(){
    }

    public HBox getContainer() {
        if(container==null){
            container = new HBox(8);
            botoes = createAllActionButtons();
            clean = new ImageView("images/limpar.png");
            container.getChildren().addAll(botoes);
            container.getChildren().add(clean);
        }
        return this.container;
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

    
    private List<ActionButton> createAllActionButtons(){

        List<ActionButton> botoes = new ArrayList<>();
        ActionButton botao = new ActionButton();
        botao.setText("Draw");
        botao.setCoresSeletor(new ColorSelector());
        botao.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_ANDAR));
        botao.setIcone(new ImageView("images/botoes/anda.png"));
        botoes.add(botao);

        ActionButton botaoProduce = new ActionButton();
        botaoProduce.setText("Produce");
        botaoProduce.setCoresSeletor(new ColorSelector());
        botaoProduce.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_EXPANDIR));
        botaoProduce.setIcone(new ImageView("images/botoes/legalmoves.png"));
        botoes.add(botaoProduce);

        ActionButton botaoTurnLeft = new ActionButton();
        botaoTurnLeft.setText("Turn Left");
        botaoTurnLeft.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_ESQUERDA));
        botaoTurnLeft.setIcone(new ImageView("images/botoes/esquerda.png"));
        botoes.add(botaoTurnLeft);

        ActionButton botaoTurnRight = new ActionButton();
        botaoTurnRight.setText("Turn Right");
        botaoTurnRight.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_DIREITA));
        botaoTurnRight.setIcone(new ImageView("images/botoes/direita.png"));
        botoes.add(botaoTurnRight);

        ActionButton doAndReturnButton = new ActionButton();
        doAndReturnButton.setText("Do and return");
        doAndReturnButton.setCoresSeletor(new ColorSelector());
        doAndReturnButton.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_FAZER_RETORNAR));
        doAndReturnButton.setIcone(new ImageView("images/botoes/fazerRetornar.png"));
        botoes.add(doAndReturnButton);
        
        return botoes;
    }

    
}