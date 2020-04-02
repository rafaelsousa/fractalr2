package ca.renardnumerique.fractalr2;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class FormulaPanel extends Group {


    private List<ActionButton> botoes;
    private List<ImageView> iconesBotoes;
    private Text formula = new Text("Formulas");

    {
        formula.setY(55);
        formula.setX(170);
        formula.setFont(Font.font("Bitstream Vera Sans Bold", 12));
        formula.setFill(Color.web("#000"));
    }

    public Rectangle area = new Rectangle();

    {
        area.setX(165);
        area.setY(33);
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
    }

    private Group areaDeBotoes = new Group();

    Rectangle contornoAreaDeBotoes = new Rectangle();

    {
        contornoAreaDeBotoes.setX(243);
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

    public FormulaPanel() {
        this.getChildren().addAll(area, formula, contornoAreaDeBotoes, areaDeBotoes);
    }

    public void redesenhaBarra() {
        areaDeBotoes.getChildren().clear();
        Integer cont = 0;
        for (ActionButton botao : botoes) {
            String urlNova = botao.getIcone().getUrl();
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
            img.setImage(new Image (urlNova));
            img.setY(area.getY() + 6);
			img.setX(contornoAreaDeBotoes.getX() + 10 + (30 * cont));
            BotaoFormula nodo = new BotaoFormula();
			nodo.setRect(rect);
			nodo.setImg(img);
			nodo.setBtn(botao);

			DragDrop dragDrop = new DragDrop();
			dragDrop.setTarget(nodo);
            dragDrop.setMaxX(780);
            dragDrop.setMaxY(74);
            dragDrop.setOnSoltar(this::trataMoverExcluir);


            areaDeBotoes.getChildren().add(nodo);
            cont++;
        }
        Fractal.instance.setDesenhoModificado(true);
    }


    public function calculaPosicaoBotao(btn:DragDrop):Void

    {
        var zx = btn.tx + (btn.target as BotaoFormula).img.x;
        var zy = btn.ty;
        var imgAntes:ImageView;
        var count = 0;
        for (imagem in areaDeBotoes.content) {
            if (zx <= (imagem as BotaoFormula).img.x){
                imgAntes = (imagem as BotaoFormula).img;
                break;
            }
            count++;
        }
        var botaoAtual = (btn.target as BotaoFormula).btn;
        delete botaoAtual from botoes;
        insert botaoAtual before botoes[ count];
    }

    // trata no reposicionar e o tirar um icone da barra de formulas
    public void trataMoverExcluir(DragDrop btn){
        if (btn.estaEm(contornoAreaDeBotoes)) {
            calculaPosicaoBotao(btn);
        } else {
            botoes.remove(btn.getTarget());
        }
        redesenhaBarra();
    }

    //retorna o bot�o correspondente a um icone
    public function getBotao(img:ImageView):ActionButton

    {
        for (botao in botoes) {
            if (botao.icone.image.url == img.image.url) {
                return botao;
            }
        }
        return null;
    }

    //trata a adi��o da um bot�o na barra de formulas
    public function trataArrastar(btn:DragDrop):Void

    {
        delete ActionButton.botaoSeparador from TransformationPanel.instanciaAtual.botoes;
        TransformationPanel.instanciaAtual.redesenhaBarra();
        if (btn.estaEm(area)) {
            for (botao in botoes) {
                if (botao == ActionButton.botaoSeparador)
                    return;
            }
            insert ActionButton.botaoSeparador into botoes;
        } else {
            delete ActionButton.botaoSeparador from botoes;
        }
        redesenhaBarra();
    }

    //trata a adi��o da um bot�o na barra de formulas
    public function trataSoltar(btn:DragDrop):Void

    {
        delete ActionButton.botaoSeparador from botoes;
        if (btn.estaEm(area)) {
            insert(btn.target as ActionButton).duplicar() into botoes;
        }
        redesenhaBarra();
    }
}
