package ca.renardnumerique.fractalr2.ui;

import java.util.List;

import ca.renardnumerique.fractalr2.MainClass;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import ca.renardnumerique.fractalr2.lsystem.GerenciadorLSystem;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ActionButton extends Button {

    private AcaoLSystem acaoLSystem;

    public static final ActionButton BOTAO_IGUAL = new ActionButton(new ImageView(new Image("images/igual.png")), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL, "+", -1), null);
    public static final ActionButton BOTAO_SEPARADOR = new ActionButton(new ImageView(new Image("images/botoes/draw-arrow-back.png")), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL, "+", -2), null);

    private Node painelAtual;
    private List<ActionButton> botoes;

    private ColorSelector coresSeletor;
    private LinearGradient fillNormal;
    private Rectangle designRetangulo;
    private int numeroBotoes = -2;
    private Text dspNome;
    private ImageView icone;
    private String nome;
    
    private MainClass mainClass;

    private FormulaPanel formulaPanel;

    public ActionButton(ImageView icone, AcaoLSystem acaoLSystem, LinearGradient fillNormal) {
        this.icone = icone;
        this.acaoLSystem = acaoLSystem;
        this.fillNormal = fillNormal;        
        buildDesignRectangle();
    }
    
    public ActionButton() {
    }

    private void buildDesignRectangle() {
    	designRetangulo = new Rectangle();
    }

    public ActionButton duplicar() {
        ActionButton nova = new ActionButton();
        nova.setNome(this.getNome());
        nova.setIcone(this.getIcone());
        nova.setFillNormal(this.fillNormal);
        nova.setAcaoLSystem(GerenciadorLSystem.instance.obterAcao(coresSeletor.idSelecionado, this.acaoLSystem.getTipoAcao()));
        return nova;
    }


    private DragDrop drag = new DragDrop(this);


    public void create(){
        numeroBotoes++;
        fillNormal = new LinearGradient(
                0.0,
                0.7,
                0.0,
                1.5,
                Boolean.TRUE,
                CycleMethod.NO_CYCLE,
                new Stop(0.2, Color.web("#FFFAC1")),
                new Stop(1.0, Color.web("#AAA")));
        buildDesignRectangle();
        coresSeletor = new ColorSelector();
        coresSeletor.setPosX(98 + designRetangulo.getX());
        coresSeletor.setPosY(16 + designRetangulo.getY());
        coresSeletor.setNodo(designRetangulo);
        coresSeletor.setBotaoPai(this);
        this.getChildren().addAll(designRetangulo, dspNome, icone, coresSeletor);


        drag.onChange(arrastaSolta -> {
            formulaPanel.trataArrastar(arrastaSolta);
            for (Node transformacao : mainClass.getTransformations().getChildren()) {
                if (transformacao instanceof TransformationPanel) {
                    ((TransformationPanel) transformacao).trataArrastar(arrastaSolta);
                }
            }
        });
        drag.setOnSoltar(arrastaSolta -> {
            formulaPanel.trataSoltar(arrastaSolta);
            for (Node transformacao : mainClass.getTransformations().getChildren()) {
                if (transformacao instanceof TransformationPanel) {
                    ((TransformationPanel) transformacao).trataSoltar(arrastaSolta);
                }
            }
        });
        drag.setMaxX(900);
        drag.setMaxY(140);
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ActionButton> getBotoes() {
        return botoes;
    }

    public void setBotoes(List<ActionButton> botoes) {
        this.botoes = botoes;
    }


    public ColorSelector getCoresSeletor() {
        return coresSeletor;
    }

    public void setCoresSeletor(ColorSelector coresSeletor) {
        this.coresSeletor = coresSeletor;
    }

    public AcaoLSystem getAcaoLSystem() {
        return acaoLSystem;
    }

    public void setAcaoLSystem(AcaoLSystem acaoLSystem) {
        this.acaoLSystem = acaoLSystem;
    }

    public LinearGradient getFillNormal() {
        return fillNormal;
    }

    public void setFillNormal(LinearGradient fillNormal) {
        this.fillNormal = fillNormal;
    }

    public Node getPainelAtual() {
        return painelAtual;
    }

    public void setPainelAtual(Node painelAtual) {
        this.painelAtual = painelAtual;
    }

    public Rectangle getDesignRetangulo() {
        return designRetangulo;
    }

    public void setDesignRetangulo(Rectangle designRetangulo) {
        this.designRetangulo = designRetangulo;
    }

    public int getNumeroBotoes() {
        return numeroBotoes;
    }

    public void setNumeroBotoes(int numeroBotoes) {
        this.numeroBotoes = numeroBotoes;
    }

    public Text getDspNome() {
        return dspNome;
    }

    public void setDspNome(Text dspNome) {
        this.dspNome = dspNome;
    }

    public ImageView getIcone() {
        return icone;
    }

    public void setIcone(ImageView icone) {
        this.setGraphic(icone);
        this.icone = icone;
    }

    


}



