package ca.renardnumerique.fractalr2;

import javafx.beans.binding.DoubleBinding;
import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

import java.util.ArrayList;
import java.util.List;


@Data
public class TransformationPanel extends Group  {

    public static Integer quantidadeTransformacoes=1;
    public static TransformationPanel instanciaAtual;
    private List<ActionButton> botoes = new ArrayList<>();
    private List<ImageView> iconesBotoes = new ArrayList<>();
    private Integer incrementoY=26;
    private Integer posicao = quantidadeTransformacoes;
    private Boolean ultimo = Boolean.TRUE;
    private String txtSinal="";
    private DoubleBinding posY = new DoubleBinding() {
        @Override
        protected double computeValue() {
            return 44+(incrementoY*posicao);
        }
    };

    private Rectangle area = new Rectangle();

    {
        area.setX(165);
        area.setY(posY.getValue());
        area.setArcWidth(10);
        area.setArcHeight(10);
        area.setWidth(660);
        area.setHeight(22);

        LinearGradient linearFill =
                new LinearGradient(0,
                        0.0,
                        0.0,
                        1.5,
                        Boolean.TRUE,
                        CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.web("#f1ffde")),
                        new Stop(1.0, Color.web("#AAA")));
        area.setFill(linearFill);
        area.setStroke(Color.web("#111"));
    }
    public Button btnDpl = new Button();
    { // swing button B
        btnDpl.setTranslateX(813);
        btnDpl.setTranslateY(posY.getValue());
        btnDpl.setMaxWidth(10);
        btnDpl.setMaxHeight(22);
        btnDpl.setText("b");
        btnDpl.setOnAction(this::trataAdicaoExclusao);
    }

    public Rectangle btnDuplicar;
    {
        btnDuplicar.setX(813);
        btnDuplicar.setY(posY);
        btnDuplicar.setArcWidth(10);
        btnDuplicar.setArcHeight(10);
        btnDuplicar.setWidth(10);
        btnDuplicar.setHeight(22);
        btnDuplicar.setCursor(Cursor.HAND);
        LinearGradient linearGradient =
                new LinearGradient(
                        0.0,
                        0.0,
                        0.0,
                        1.0,
                        Boolean.TRUE,
                        CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.web("#3F9B2F")),
                        new Stop(1.0, Color.web("#c8eab2")));
        btnDuplicar.setFill(linearGradient);
        btnDuplicar.setStroke(Color.web("#f8f8f8"));
        btnDuplicar.setOnMouseClicked(this::trataAdicaoExclusao);
    }

    private void trataAdicaoExclusao(Event mouseEvent) {
        if(ultimo || botoes.size() > 2 ){
            adicionarBarra();
        }else{
            excluirBarra();
        }
    }

    public void adicionarBarra(){
        Integer inicioDesenhoY = MainClass.getInstance().getDesign().getInicioDesenhoY();
        MainClass.getInstance().getDesign().setInicioDesenhoY(inicioDesenhoY+incrementoY);
        Integer alturaRealSistema = MainClass.getInstance().getAlturaSistema();
        MainClass.getInstance().setAlturaSistema(alturaRealSistema+incrementoY);
        TransformationPanel aux = new TransformationPanel();
        MainClass.getInstance().getTransformacoes().getChildren().add(3,aux);
        txtSinal="-";
        ultimo=false;
        instanciaAtual = aux;
        Fractal.instance.limpaCanvas();
        MainClass.getInstance().getPencil().clear();
    }
    public void resetarBarra(){
        while((MainClass.getInstance().getTransformacoes().getChildren().size() >=8)){
            excluirBarra();
        }

    }
    public void excluirBarra(){
        if((MainClass.getInstance().getTransformacoes().getChildren().size() >=8)){

            Integer inicioDesenhoY = MainClass.getInstance().getDesign().getInicioDesenhoY()-incrementoY;
            Integer alturaRealSistema = MainClass.getInstance().getAlturaSistema()-incrementoY;
            MainClass.getInstance().getDesign().setInicioDesenhoY(inicioDesenhoY);
            MainClass.getInstance().setAlturaSistema(alturaRealSistema);
            quantidadeTransformacoes--;
            MainClass.getInstance().getTransformacoes().getChildren().remove(this);
            var i = quantidadeTransformacoes-1;
            TransformationPanel primeiro = null;
            for(Node pnl : MainClass.getInstance().getTransformacoes().getChildren()){
                if(pnl instanceof TransformationPanel){
                    if(primeiro == null){
                        primeiro=(TransformationPanel)pnl;
                    }
                    ((TransformationPanel)pnl).setPosicao(i--);
                }
            }
            if(primeiro !=null  ){
                primeiro.txtSinal="+";
                primeiro.ultimo=true;
                instanciaAtual= primeiro ;
            }
        }
    }

    private Text mais = new Text(txtSinal);
    {
        mais.setY(btnDuplicar.getY()+15);
        mais.setX(btnDuplicar.getX()+1);
        mais.setFont(new Font("Bitstream Vera Sans Bold",10));
        mais.setFill(Color.web("#3a5833"));
    }

    private Text transformacao = new Text("Transformations");
    {
        transformacao.setY(area.getY()+17);
        transformacao.setX(area.getX()+5);
        transformacao.setFont(new Font("Bitstream Vera Sans Bold", 10));
        transformacao.setFill(Color.web("#3a5833"));
    }

    private Group areaDeBotoes = new Group();

    private Rectangle contornoAreaDeBotoes = new Rectangle();
    {
        contornoAreaDeBotoes.setX(area.getX()+ 100);
        contornoAreaDeBotoes.setY(area.getY()+1);
        contornoAreaDeBotoes.setWidth(botoes.size()*26+10);
        contornoAreaDeBotoes.setHeight(20);
        contornoAreaDeBotoes.setVisible(botoes.size()>0);
        LinearGradient linearGradient =
                new LinearGradient(
                        0.0,
                        0.7,
                        0.0,
                        1.5,
                        Boolean.TRUE,
                        CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.web("#c8eab2")),
                        new Stop(1.0, Color.web("#aaa")));
        contornoAreaDeBotoes.setStroke(Color.web("#444"));
    }


    public TransformationPanel() {
        quantidadeTransformacoes++;
        this.getChildren().addAll(
                    area,
                    transformacao,
                    contornoAreaDeBotoes,
                    areaDeBotoes,
                    btnDuplicar,
                    mais
        );
    }

    public void redesenhaBarra(){
        areaDeBotoes.getChildren().clear();
        var cont=0;
        if(ultimo && botoes.size()>2){
            txtSinal="+";
        }else{
            if((MainClass.getInstance().getTransformacoes().getChildren().size()>6)){
                txtSinal="-";
            }
        }
        for(ActionButton botao : botoes){
            var urlNova=botao.getIcone().getUrl();
            var alt=16;
            var larg=24;

            ImageView img = new ImageView();
            {
                img.setCursor(Cursor.MOVE);
                img.setFocusTraversable(Boolean.TRUE);
                img.setImage(new Image(urlNova,larg,alt,Boolean.TRUE,Boolean.TRUE));
                img.setY(area.getY()+3);
                img.setX(contornoAreaDeBotoes.getX()+5+(26*cont));

            }
            Rectangle rect = new Rectangle();
            {
                rect.setCursor(Cursor.MOVE);
                rect.setFocusTraversable(true);
                rect.setY(area.getY()+3);
                rect.setX(contornoAreaDeBotoes.getX()+5+(26*cont));
                rect.setWidth(larg);
                rect.setHeight(alt);
                rect.setArcWidth(10);
                rect.setArcHeight(10);
                rect.setFill(botao.getFillNormal());
                rect.setStroke(Color.web("#aaa"));
            }
            BotaoTransformacoes nodo = new BotaoTransformacoes();
            {
                nodo.setImg(img);
                nodo.setRect(rect);
                nodo.setBtn(botao);
            }
            DragDrop drag = new DragDrop();
            {
                drag.setTarget(nodo);
                drag.setMaxX(900);
                drag.setMaxY(874);
                drag.setOnSoltar(this::trataMoverExcluir);
            }
            areaDeBotoes.getChildren().add(nodo);
            cont++;
        }
        Fractal.instance.setDesenhoModificado(true);
    }

    public void calculaPosicaoBotao(DragDrop btn) {
        var zx = btn.getTx() + ((BotaoTransformacoes)btn.target).getRect().getX();
        var zy = btn.getTy();
        ImageView btransAntes = null;
        var count = 0;
        for(Node btrans : areaDeBotoes.getChildren()){
            if(zx <= ((BotaoTransformacoes)btrans).img.getX()){
                btransAntes =((BotaoTransformacoes)btrans).img;
                break;
            }
            count++;
        }
        var botaoAtual = ((BotaoTransformacoes)btn.target).btn;
        botoes.remove(botaoAtual);
        botoes.add(count-1,botaoAtual);
    }

    // trata no reposicionar e o tirar um icone da barra de formulas
    public void trataMoverExcluir(DragDrop btn) {
        if(btn.estaEm(contornoAreaDeBotoes)){
            calculaPosicaoBotao(btn);
        }else{
            botoes.remove(btn.target);;
            if(botoes.size() == 1){
                botoes.remove(ActionButton.botaoIgual);
            }
        }
        redesenhaBarra();
    }

    //retorna o bot�o correspondente a um icone
    public function getBotao(img:ImageView): ActionButton {
        for(botao in botoes){
            if(botao.icone.image.url == img.image.url){
                return botao;
            }
        }
        return null;
    }

    //trata a adi��o da um bot�o na barra de formulas
    public function trataArrastar(btn:DragDrop): Void {
        if(btn.estaEm(area)){
            for(botao in botoes){
                if(botao == ActionButton.botaoSeparador )
                    return;
            }
            delete ActionButton.botaoSeparador from FormulaPanel.instanciaAtual.botoes;
            FormulaPanel.instanciaAtual.redesenhaBarra();
            insert ActionButton.botaoSeparador into botoes;
        }else{
            delete ActionButton.botaoSeparador from botoes;
        }
        redesenhaBarra();
    }

    //trata a adi��o da um bot�o na barra de formulas
    public function trataSoltar(btn:DragDrop): Void {
        delete ActionButton.botaoSeparador from botoes;
        if(btn.estaEm(area)){
            var novoBtn =(btn.target as ActionButton).duplicar();
            insert novoBtn into botoes;
            if((sizeof botoes) ==1){
                insert  ActionButton.botaoIgual into botoes;
            }
        }
        redesenhaBarra();
    }
}
