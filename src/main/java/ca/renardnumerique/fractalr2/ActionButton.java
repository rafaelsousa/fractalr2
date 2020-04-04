package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
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

import javax.swing.*;
import java.util.List;

@Data
@AllArgsConstructor
public class ActionButton extends Group {

    private AcaoLSystem acaoLSystem;

    public static final ActionButton BOTAO_IGUAL = new ActionButton(new Image("images/igual.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-1),null);
    public static final ActionButton BOTAO_SEPARADOR = new ActionButton(new Image("imagens/botoes/draw-arrow-back.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-2),null);

    public ActionButton() {
        fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
        fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
        fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
    }

    public ActionButton(ImageView icone, AcaoLSystem acaoLSystem, LinearGradient fillNormal) {
        this.icone = icone;
        this.acaoLSystem = acaoLSystem;
        this.fillNormal = fillNormal;
    }


    private String nome = "acao";
    private Integer width = 110;
    private Node painelAtual;
    private List<Action> botoes;

    public String iconeUrl = "{__DIR__}imagens/botoes/rating.png"; //por padrao aparece uma estrela
    public Color coresSeletor:SeletorCores;


    private LinearGradient fillNormal =
            new LinearGradient(
                    0.0,
                    0.7,
                    0.0,
                    1.5,
                    Boolean.TRUE,
                    CycleMethod.NO_CYCLE,
                    new Stop(0.2, Color.web("#ffffff")),
                    new Stop(1.0, Color.web("#444444")));

    private Rectangle designRetangulo = new Rectangle();

    private final int numeroBotoes = -2;
    {
        designRetangulo.setY(-2);
        designRetangulo.setWidth(width);
        designRetangulo.setX(170 + (width+10) * (numeroBotoes));
        designRetangulo.setHeight(29);
        designRetangulo.setArcWidth(10);
        designRetangulo.setArcHeight(10);
        designRetangulo.setFill(fillNormal);
        designRetangulo.setOnMouseEntered(e->{
            designRetangulo.setStroke(Color.web("#444444"));
        });
        designRetangulo.setOnMouseExited(e->{
            designRetangulo.setStroke(Color.web("#f8f8f8"));
        });
        designRetangulo.setStroke(Color.web("#f8f8f8"));
    };

    private Text dspNome = new Text(nome);
    {
        dspNome.setX(24+designRetangulo.getX());
        dspNome.setY(17+designRetangulo.getX());
        dspNome.setFont( new Font ( "Bitstream Vera Sans Bold",10));
        dspNome.setFill( Color.web("#000000"));
    }
    private ImageView icone = new ImageView();
    {
        icone.setImage(new Image(iconeUrl));
        icone.setX(1+designRetangulo.getX());
        icone.setY(5+designRetangulo.getY());
    };
    public ActionButton duplicar(){
        ActionButton nova = new ActionButton();
        nova.setNome( this.nome);
        nova.setIconeUrl( this.iconeUrl);
        nova.setFillNormal( this.fillNormal);
        nova.setAcaoLSystem(GerenciadorLSystem.instanciaAtual.obterAcao(coresSeletor.idSelecionado,this.acaoLSystem.tipoAcao));
        return nova;
    }
    private function onDrop = new function()(local:Node){
        this.painelAtual = local;
    }
    private DragDrop drag = new DragDrop() {
        setTarget( this)
        onChange : function(arrastaSolta:DragDrop){
            FormulaPanel.instanciaAtual.trataArrastar(arrastaSolta);
            for(transformacao in MainClass.instanciaAtual.transformacoes){
                if(transformacao instanceof TransformationPanel){
                    (transformacao as TransformationPanel).trataArrastar(arrastaSolta);
                }
            }
        }
        onSoltar : function(arrastaSolta:DragDrop){
            FormulaPanel.instanciaAtual.trataSoltar(arrastaSolta);
            for(transformacao in MainClass.instanciaAtual.transformacoes){
                if(transformacao instanceof TransformationPanel){
                    (transformacao as TransformationPanel).trataSoltar(arrastaSolta);
                }
            }
        }
        setMaxX( 900)
        setMaxY( 140)
    }
    override public function create(): Node {
        numeroBotoes++;
        coresSeletor.posX = 98+designRetangulo.x;
        coresSeletor.posY =  16+designRetangulo.y;
        coresSeletor.nodo =  designRetangulo;
        coresSeletor.botaoPai =  this;

        Group {
            content: [
            designRetangulo,dspNome,icone,coresSeletor
               ]
        };
    }
}


    public static function getBotao(): ActionButton[] {

        var botoes:ActionButton[];
        var botao = ActionButton{
        nome:"Draw"
        coresSeletor: SeletorCores{}
        acaoLSystem : AcaoExpansiva{
        tipoAcao : AcaoLSystem.ACAO_ANDAR;
        }
        iconeUrl:"{__DIR__}imagens/botoes/anda.png"
        };
        insert botao into botoes;
        botao = ActionButton{
        setNome("Produce")
        setCoresSeletor( SeletorCores{})
        acaoLSystem : AcaoExpansiva {
        tipoAcao : AcaoLSystem.ACAO_EXPANDIR;
        }
        iconeUrl:"{__DIR__}imagens/botoes/legalmoves.png"
        };

        insert botao into botoes;
        botao = ActionButton{
        nome:"Turn Left"
        acaoLSystem : AcaoLSystem {
        tipoAcao : AcaoLSystem.ACAO_GIRAR_ESQUERDA;
        }
        iconeUrl:"{__DIR__}imagens/botoes/esquerda.png"
        };
        insert botao into botoes;

        botao = ActionButton{
        nome:"Turn Right"
        acaoLSystem : AcaoLSystem {
        tipoAcao : AcaoLSystem.ACAO_GIRAR_DIREITA;
        }
        iconeUrl:"{__DIR__}imagens/botoes/direita.png"
        };
        insert botao into botoes;

        botao = ActionButton{
        nome:"Do and return";
        coresSeletor: SeletorCores{}
        acaoLSystem : AcaoLSystem {
        tipoAcao : AcaoLSystem.ACAO_FAZER_RETORNAR;
        }
        iconeUrl:"{__DIR__}imagens/botoes/fazerRetornar.png"
        };
        insert botao into botoes;
        return botoes;
        }
}
