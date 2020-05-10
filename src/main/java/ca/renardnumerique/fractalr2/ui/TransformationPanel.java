package ca.renardnumerique.fractalr2.ui;

import ca.renardnumerique.fractalr2.ApplicationLayout;
import ca.renardnumerique.fractalr2.Fractal;
import jakarta.inject.Inject;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TransformationPanel extends Group {

    public static Integer quantidadeTransformacoes = 1;

    private final List<ActionButton> botoes = new ArrayList<>();
    private List<ImageView> iconesBotoes = new ArrayList<>();
    private Integer incrementoY = 26;
    private Integer posicao = quantidadeTransformacoes;
    private Boolean ultimo = Boolean.TRUE;
    private String txtSinal;
    private Integer posY = incrementoY * posicao;
    private Rectangle area = new Rectangle();
    private Button btnDpl = new Button();
    private Fractal fractal;
    private ApplicationLayout applicationLayout;
    private TransformationPanel formulaPanel;
    private HBox container;
    private Group areaDeBotoes = new Group();
    private Rectangle contornoAreaDeBotoes = new Rectangle();


    private Text transformacao = new Text("Transformations");

    @Inject
    private ApplicationCanvas applicationCanvas;

    @Inject
    private Pencil pencil;


    private void initComponents(){

        area.setArcWidth(10);
        area.setArcHeight(10);
        area.setWidth(660);
        area.setHeight(22);
        LinearGradient linearFill = new LinearGradient(0, 0.0, 0.0, 1.5, Boolean.TRUE, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#F1FFDE")), new Stop(1.0, Color.web("#AAA")));
        area.setFill(linearFill);
        area.setStroke(Color.web("#111"));

        this.container = new HBox();
        this.container.setShape(area);

        btnDpl.setId("duplicate-button");
        btnDpl.setMinWidth(10);
        btnDpl.setMinHeight(22);
        btnDpl.setText("+");
        btnDpl.setOnAction(this::trataAdicaoExclusao);

        this.container.getChildren().addAll(btnDpl);

        transformacao.setY(area.getY() + 17);
        transformacao.setX(area.getX() + 5);
        transformacao.setFont(new Font("Bitstream Vera Sans Bold", 10));
        transformacao.setFill(Color.web("#3a5833"));

        contornoAreaDeBotoes.setX(area.getX() + 100);
        contornoAreaDeBotoes.setY(area.getY() + 1);
        contornoAreaDeBotoes.setWidth(botoes.size() * 26 + 10);
        contornoAreaDeBotoes.setHeight(20);
        contornoAreaDeBotoes.setVisible(botoes.size() > 0);
        contornoAreaDeBotoes.setStroke(Color.web("#444"));

    }

    private void trataAdicaoExclusao(Event mouseEvent) {
        if (ultimo || botoes.size() > 2) {
            adicionarBarra();
        } else {
            excluirBarra();
        }
    }

    public void adicionarBarra() {
//        Integer inicioDesenhoY = applicationCanvas.getInicioDesenhoY();
//        applicationCanvas.setInicioDesenhoY(inicioDesenhoY + incrementoY);
        TransformationPanel aux = new TransformationPanel();
        applicationLayout.addTransformationPanel(aux);
        txtSinal = "-";
        ultimo = false;
        fractal.limpaCanvas();
        pencil.clear();
    }

    public void resetarBarra() {
//        while ((applicationLayout.getTransformations().getChildren().size() >= 8)) {
//            excluirBarra();
//        }

    }

    public void excluirBarra() {
//        if ((applicationLayout.getTransformations().getChildren().size() >= 8)) {
//
//            Integer inicioDesenhoY = applicationLayout.getDesign().getInicioDesenhoY() - incrementoY;
//            applicationLayout.getDesign().setInicioDesenhoY(inicioDesenhoY);
//            quantidadeTransformacoes--;
//            applicationLayout.getTransformations().getChildren().remove(this);
//            var i = quantidadeTransformacoes - 1;
//            TransformationPanel primeiro = null;
//            for (Node pnl : applicationLayout.getTransformations().getChildren()) {
//                if (pnl instanceof TransformationPanel) {
//                    if (primeiro == null) {
//                        primeiro = (TransformationPanel) pnl;
//                    }
//                    ((TransformationPanel) pnl).setPosicao(i--);
//                }
//            }
//            if (primeiro != null) {
//                primeiro.txtSinal = "+";
//                primeiro.ultimo = true;
//                //instanciaAtual = primeiro;
//            }
//        }
    }
    {

    }



    public TransformationPanel() {
        initComponents();
        quantidadeTransformacoes++;
        this.getChildren().addAll(area, transformacao, contornoAreaDeBotoes, areaDeBotoes);
    }

    public void redesenhaBarra() {
//        areaDeBotoes.getChildren().clear();
//        var cont = 0;
//        if (ultimo && botoes.size() > 2) {
//            txtSinal = "+";
//        } else {
//            if ((applicationLayout.getTransformations().getChildren().size() > 6)) {
//                txtSinal = "-";
//            }
//        }
//        for (ActionButton botao : botoes) {
//            var urlNova = botao.getIcone().getImage().getUrl();
//            var alt = 16;
//            var larg = 24;
//
//            ImageView img = new ImageView();
//            {
//                img.setCursor(Cursor.MOVE);
//                img.setFocusTraversable(Boolean.TRUE);
//                img.setImage(new Image(urlNova, larg, alt, Boolean.TRUE, Boolean.TRUE));
//                img.setY(area.getY() + 3);
//                img.setX(contornoAreaDeBotoes.getX() + 5 + (26 * cont));
//
//            }
//            Rectangle rect = new Rectangle();
//            {
//                rect.setCursor(Cursor.MOVE);
//                rect.setFocusTraversable(true);
//                rect.setY(area.getY() + 3);
//                rect.setX(contornoAreaDeBotoes.getX() + 5 + (26 * cont));
//                rect.setWidth(larg);
//                rect.setHeight(alt);
//                rect.setArcWidth(10);
//                rect.setArcHeight(10);
//                rect.setFill(botao.getFillNormal());
//                rect.setStroke(Color.web("#aaa"));
//            }
//            BotaoTransformacoes nodo = new BotaoTransformacoes();
//            {
//                nodo.setImg(img);
//                nodo.setRect(rect);
//                nodo.setBtn(botao);
//            }
//            DragDrop drag = new DragDrop(nodo);
//            {
//                drag.setMaxX(900);
//                drag.setMaxY(874);
//                drag.setOnSoltar(this::trataMoverExcluir);
//            }
//            areaDeBotoes.getChildren().add(nodo);
//            cont++;
//        }
//        fractal.setDesenhoModificado(true);
    }

    public void calculaPosicaoBotao(DragDrop btn) {
        var zx = btn.getTx() + ((BotaoTransformacoes) btn.target).getRect().getX();
        var count = 0;
        for (Node btrans : areaDeBotoes.getChildren()) {
            if (zx <= ((BotaoTransformacoes) btrans).getImg().getX()) {
                break;
            }
            count++;
        }
        var botaoAtual = ((BotaoTransformacoes) btn.target).getBtn();
        botoes.remove(botaoAtual);
        botoes.add(count - 1, botaoAtual);
    }

    // trata no reposicionar e o tirar um icone da barra de formulas
    public void trataMoverExcluir(DragDrop btn) {
        if (btn.estaEm(contornoAreaDeBotoes)) {
            calculaPosicaoBotao(btn);
        } else {
            botoes.remove(btn.target);
            if (botoes.size() == 1) {
                botoes.remove(ActionButton.BOTAO_IGUAL);
            }
        }
        redesenhaBarra();
    }

    public void trataArrastar(DragDrop btn) {
        if (btn.estaEm(area)) {
            for (ActionButton botao : botoes) {
                if (botao.equals(ActionButton.BOTAO_SEPARADOR))
                    return;
            }
            formulaPanel.getBotoes().remove(ActionButton.BOTAO_SEPARADOR);
            formulaPanel.redesenhaBarra();
            botoes.add(ActionButton.BOTAO_SEPARADOR);
        } else {
            botoes.remove(ActionButton.BOTAO_SEPARADOR);
        }
        redesenhaBarra();
    }

    public void trataSoltar(DragDrop btn) {
        botoes.remove(ActionButton.BOTAO_SEPARADOR);
        if (btn.estaEm(area)) {
            ActionButton novoBtn = ((ActionButton) btn.target).duplicar();
            botoes.add(novoBtn);
            if (botoes.size() == 1) {
                botoes.add(ActionButton.BOTAO_IGUAL);
            }
        }
        redesenhaBarra();
    }

    public List<ActionButton> getBotoes() {
        return this.botoes;
    }
    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }





}
