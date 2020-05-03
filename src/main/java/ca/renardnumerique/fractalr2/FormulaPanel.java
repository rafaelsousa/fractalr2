package ca.renardnumerique.fractalr2;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Cursor;
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

public class FormulaPanel extends Group {
    
    
    private static FormulaPanel instance = new FormulaPanel();
    public static FormulaPanel getInstance(){return instance;}
    
    private List<ActionButton> botoes = new ArrayList<>();
    private Rectangle area = new Rectangle();
    private Text formula = new Text("Formulas");
    private Rectangle contornoAreaDeBotoes = new Rectangle();
    private Group areaDeBotoes = new Group();

    private FormulaPanel() {
        writeFormulaLabel();
        drawBoundaries();
        this.getChildren().addAll(area, formula, contornoAreaDeBotoes, areaDeBotoes);
    }

    private void drawBoundaries() {
        area.setX(165);
        area.setY(1);
        area.setArcWidth(10);
        area.setArcHeight(10);
        area.setWidth(660);
        area.setHeight(33);
        LinearGradient linearFill =
                new LinearGradient(0,
                        0.5,
                        0,
                        1.5,
                        Boolean.TRUE,
                        CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.web("#FFF")),
                        new Stop(1.0, Color.web("#AAA")));
        area.setFill(linearFill);
        area.setStroke(Color.web("#111"));

        contornoAreaDeBotoes.setY(area.getY() + 3);
        contornoAreaDeBotoes.setWidth(botoes.size() * 32 + 2);
        contornoAreaDeBotoes.setHeight(25);
        contornoAreaDeBotoes.setVisible(botoes.size() > 0);
        LinearGradient linearGradient = new LinearGradient(0,
                0.7,
                0,
                1.5,
                Boolean.TRUE,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#FFFAC1")),
                new Stop(1.0, Color.web("#AAA")));
        contornoAreaDeBotoes.setFill(linearGradient);
        contornoAreaDeBotoes.setStroke(Color.web("#444"));
    }

    private void writeFormulaLabel(){
        formula.setY(16);
        formula.setX(170);
        formula.setFont(Font.font("Bitstream Vera Sans Bold", 12));
        formula.setFill(Color.web("#000"));
    }

    

    public void redrawBar() {
        areaDeBotoes.getChildren().clear();
        Integer cont = 0;
        for (ActionButton botao : botoes) {
            String urlNova = botao.getIcone().getImage().getUrl();
            Rectangle rect = new Rectangle();
            rect.setCursor(Cursor.MOVE);
            rect.setFocusTraversable(Boolean.TRUE);
            rect.setY(area.getY() + 5);
            rect.setX(contornoAreaDeBotoes.getX() + 7 + (30 * cont));
            rect.setWidth(26);
            rect.setHeight(22);
            rect.setArcWidth(10);
            rect.setArcHeight(10);
            rect.setFill(botao.getFillNormal());
            rect.setStroke(Color.web("#aaa"));
            ImageView img = new ImageView();
            img.setCursor(Cursor.MOVE);
            img.setFocusTraversable(true);
            img.setImage(new Image(urlNova));
            img.setY(area.getY() + 6);
            img.setX(contornoAreaDeBotoes.getX() + 10 + (30 * cont));
            BotaoFormula nodo = new BotaoFormula();
            nodo.setRect(rect);
            nodo.setImg(img);
            nodo.setBtn(botao);

            DragDrop dragDrop = new DragDrop(nodo);
            dragDrop.setMaxX(780);
            dragDrop.setMaxY(74);
            dragDrop.setOnSoltar(this::trataMoverExcluir);


            areaDeBotoes.getChildren().add(nodo);
            cont++;
        }
        Fractal.instance.setDesenhoModificado(true);
    }


    public void calculaPosicaoBotao(DragDrop btn) {
        double zx = btn.tx + ((BotaoFormula)btn.target).getImg().getX();
        double zy = btn.ty;
        ImageView imgAntes;
        var count = 0;
        for (Node imagem : areaDeBotoes.getChildren()) {
            if (zx <= ((BotaoFormula)imagem).getImg().getX()){
                imgAntes = ((BotaoFormula)imagem).getImg();
                break;
            }
            count++;
        }
        var botaoAtual = ((BotaoFormula)btn.target).getBtn();
        botoes.remove(botaoAtual);
        botoes.add(count-1,botaoAtual);;
    }

    // trata no reposicionar e o tirar um icone da barra de formulas
    public void trataMoverExcluir(DragDrop btn) {
        if (btn.estaEm(contornoAreaDeBotoes)) {
            calculaPosicaoBotao(btn);
        } else {
            botoes.remove(btn.getTarget());
        }
        redrawBar();
    }

    //retorna o botao correspondente a um icone
    public ActionButton getBotao(ImageView img) {
        for (ActionButton botao : botoes) {
            if (botao.getIcone().getImage().getUrl().equals(img.getImage().getUrl())) {
                return botao;
            }
        }
        return null;
    }

    //trata a adicao de um botao na barra de formulas
    public void trataArrastar(DragDrop btn) {
        TransformationPanel.instanciaAtual.getChildren().remove(ActionButton.BOTAO_SEPARADOR);
        TransformationPanel.instanciaAtual.redesenhaBarra();
        if (btn.estaEm(area)) {
            for (ActionButton botao : botoes) {
                if (botao == ActionButton.BOTAO_SEPARADOR)
                    return;
            }
            botoes.add(ActionButton.BOTAO_SEPARADOR);
        } else {
            botoes.remove(ActionButton.BOTAO_SEPARADOR);
        }
        redrawBar();
    }

    //trata a adicao da um botao na barra de formulas
    public void trataSoltar(DragDrop btn){
        botoes.remove(ActionButton.BOTAO_SEPARADOR);
        if (btn.estaEm(area)) {
            botoes.add((ActionButton) btn.target);
        }
        redrawBar();
    }
}
