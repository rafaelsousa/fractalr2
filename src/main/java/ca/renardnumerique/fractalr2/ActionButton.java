package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AcaoExpansiva;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import ca.renardnumerique.fractalr2.lsystem.GerenciadorLSystem;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ActionButton extends Group {

    private AcaoLSystem acaoLSystem;

    public static final ActionButton BOTAO_IGUAL = new ActionButton(new ImageView("file:images/igual.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL, "+", -1), null);
    public static final ActionButton BOTAO_SEPARADOR = new ActionButton(new ImageView("file:images/botoes/draw-arrow-back.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL, "+", -2), null);

    private String nome = "acao";
    private Integer width = 110;
    private Node painelAtual;
    private List<ActionButton> botoes;

    public String iconeUrl = "file:images/botoes/rating.png"; //por padrao aparece uma estrela

    public ColorSelector coresSeletor;
    private LinearGradient fillNormal;
    private Rectangle designRetangulo;
    private int numeroBotoes = -2;
    private Text dspNome;
    private ImageView icone;

    public ActionButton(ImageView icone, AcaoLSystem acaoLSystem, LinearGradient fillNormal) {
        this.icone = icone;
        this.acaoLSystem = acaoLSystem;
        this.fillNormal = fillNormal;
        buildDesignRectangle();
        buildDspNome();
        buildIcone();
    }


    
    private void buildDesignRectangle(){
    	designRetangulo = new Rectangle();
        designRetangulo.setY(-2);
        designRetangulo.setWidth(width);
        designRetangulo.setX(170 + (width + 10) * (numeroBotoes));
        designRetangulo.setHeight(29);
        designRetangulo.setArcWidth(10);
        designRetangulo.setArcHeight(10);
        designRetangulo.setFill(fillNormal);
        designRetangulo.setOnMouseEntered(e -> {
            designRetangulo.setStroke(Color.web("#444444"));
        });
        designRetangulo.setOnMouseExited(e -> {
            designRetangulo.setStroke(Color.web("#f8f8f8"));
        });
        designRetangulo.setStroke(Color.web("#f8f8f8"));
    }

    ;


    private void buildDspNome(){
    	dspNome = new Text();
    	dspNome.setText(nome);
        dspNome.setX(24 + designRetangulo.getX());
        dspNome.setY(17 + designRetangulo.getX());
        dspNome.setFont(new Font("Bitstream Vera Sans Bold", 10));
        dspNome.setFill(Color.web("#000000"));
    }

    
    private void buildIcone(){
    	icone = new ImageView();
        icone.setImage(new Image(iconeUrl));
        icone.setX(1 + designRetangulo.getX());
        icone.setY(5 + designRetangulo.getY());
    }

    

    public ActionButton duplicar() {
        ActionButton nova = new ActionButton();
        nova.setNome(this.nome);
        nova.setIconeUrl(this.iconeUrl);
        nova.setFillNormal(this.fillNormal);
        nova.setAcaoLSystem(GerenciadorLSystem.instance.obterAcao(coresSeletor.idSelecionado, this.acaoLSystem.getTipoAcao()));
        return nova;
    }

    private void onDrop(Node local) {
        this.painelAtual = local;
    }

    private DragDrop drag = new DragDrop(this);
    {
        drag.onChange(arrastaSolta -> {
            FormulaPanel.getInstance().trataArrastar(arrastaSolta);
            for (Node transformacao : MainClass.getInstance().getTransformations().getChildren()) {
                if (transformacao instanceof TransformationPanel) {
                    ((TransformationPanel) transformacao).trataArrastar(arrastaSolta);
                }
            }
        });
        drag.setOnSoltar(arrastaSolta -> {
            FormulaPanel.getInstance().trataSoltar(arrastaSolta);
            for (Node transformacao : MainClass.getInstance().getTransformations().getChildren()) {
                if (transformacao instanceof TransformationPanel) {
                    ((TransformationPanel) transformacao).trataSoltar(arrastaSolta);
                }
            }
        });
        drag.setMaxX(900);
        drag.setMaxY(140);
    }

    public ActionButton() {
        numeroBotoes++;
        fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
        fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
        coresSeletor.setPosX(98 + designRetangulo.getX());
        fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
        coresSeletor.setPosY(16 + designRetangulo.getY());
        coresSeletor.setNodo(designRetangulo);
        coresSeletor.setBotaoPai(this);
        this.getChildren().addAll(designRetangulo, dspNome, icone, coresSeletor);
    }

    public static List<ActionButton> getBotao(){

        List<ActionButton> botoes = new ArrayList<>();
        ActionButton botao = new ActionButton();
        botao.setNome("Draw");
        botao.setCoresSeletor(new ColorSelector());
        botao.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_ANDAR));
        botao.setIconeUrl("file:images/botoes/anda.png");
        botoes.add(botao);

        ActionButton botaoProduce = new ActionButton();
        botaoProduce.setNome("Produce");
        botaoProduce.setCoresSeletor(new ColorSelector());
        botaoProduce.setAcaoLSystem(new AcaoExpansiva(AcaoLSystem.ACAO_EXPANDIR));
        botaoProduce.setIconeUrl("file:images/botoes/legalmoves.png");
        botoes.add(botaoProduce);

        ActionButton botaoTurnLeft = new ActionButton();
        botaoTurnLeft.setNome("Turn Left");
        botaoTurnLeft.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_ESQUERDA));
        botaoTurnLeft.setIconeUrl("file:images/botoes/esquerda.png");
        botoes.add(botaoTurnLeft);

        ActionButton botaoTurnRight = new ActionButton();
        botaoTurnRight.setNome("Turn Right");
        botaoTurnRight.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_DIREITA));
        botaoTurnRight.setIconeUrl("file:images/botoes/direita.png");
        botoes.add(botaoTurnRight);

        ActionButton doAndReturnButton = new ActionButton();
        doAndReturnButton.setNome("Do and return");
        doAndReturnButton.setCoresSeletor(new ColorSelector());
        doAndReturnButton.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_FAZER_RETORNAR));
        doAndReturnButton.setIconeUrl("file:images/botoes/fazerRetornar.png");
        botoes.add(doAndReturnButton);
        
        return botoes;
    }

}



