package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.util.List;

@Data
@AllArgsConstructor
public class ActionButton extends Group {

    private Image icone;
    private AcaoLSystem acaoLSystem;
    private LinearGradient fillNormal;

    public static final ActionButton BOTAO_IGUAL = new ActionButton(new Image("images/igual.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-1),null);
    public static final ActionButton BOTAO_SEPARADOR = new ActionButton(new Image("imagens/botoes/draw-arrow-back.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-2),null);

    public ActionButton() {
        fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
        fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
        fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
    }


    public ActionButton duplicar() {
        return null;
    }

    private String nome="acao";
    private Integer width=110;
    private Node painelAtual;
    private List<Action> botoes;

    public String iconeUrl="{__DIR__}imagens/botoes/rating.png"; //por padrao aparece uma estrela
    public Color coresSeletor:SeletorCores;

    public var fillNormal=LinearGradient {
        startX: 0.0, startY: 0.7, endX: 0.0, endY: 1.5
        proportional: true
        stops: [ Stop { offset: 0.2 color: Color.web("#ffffff") },
        Stop { offset: 1.0 color: Color.web("#444444") }
                ]
    };
    public var designRetangulo= Rectangle{
        y:-2
        width: width
        x:170+ (width+10)*(numeroBotoes)
                height: 29
        arcWidth: 10
        arcHeight: 10
        fill:bind fillNormal
        override public var onMouseEntered= function(e:MouseEvent):Void{
            stroke=Color.web("#444444")
        }
        override public var onMouseExited= function(e:MouseEvent):Void{
            stroke=Color.web("#f8f8f8")
        }
        stroke:Color.web("#f8f8f8")
    };
    var dspNome = Text {
        x:24+designRetangulo.x
        y:17+designRetangulo.y
        content: bind nome
        font: Font { name: "Bitstream Vera Sans Bold", size: 10}
        fill: Color.web("#000000")
    }
    public var icone = ImageView {
        image: Image{url:iconeUrl}
        x:1+designRetangulo.x
        y:5+designRetangulo.y
    };
    public var duplicar = function():ActionButton{
        var nova = ActionButton{
            nome: this.nome;
            iconeUrl: this.iconeUrl;
            fillNormal: this.fillNormal;
            acaoLSystem : GerenciadorLSystem.instanciaAtual.obterAcao(coresSeletor.idSelecionado,this.acaoLSystem.tipoAcao);
        }
        return nova;
    }
    public var onDrop = function(local:Node){
        this.painelAtual=local;
    }
    public var drag = DragDrop {
        target: this
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
        maxX: 900
        maxY: 140
    }
    override public function create(): Node {
        numeroBotoes++;
        coresSeletor.posX=98+designRetangulo.x;
        coresSeletor.posY= 16+designRetangulo.y;
        coresSeletor.nodo= designRetangulo;
        coresSeletor.botaoPai= this;

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
        nome:"Produce"
        coresSeletor: SeletorCores{}
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
