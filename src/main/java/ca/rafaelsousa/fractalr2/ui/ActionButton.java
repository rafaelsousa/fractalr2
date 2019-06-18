package ca.rafaelsousa.fractalr2.ui;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

import net.sourceforge.fractalr.lsystem.AcaoLSystem;
import net.sourceforge.fractalr.lsystem.GerenciadorLSystem;
import net.sourceforge.fractalr.lsystem.AcaoExpansiva;
import net.sourceforge.fractalr.ColorSelector.SeletorCores;


static var numeroBotoes=-2; //inicia em -1 para descontar a instancia do bot�o static

public static var botaoSeparador = ActionButton{
      		iconeUrl:"{__DIR__}imagens/botoes/draw-arrow-back.png"
       };
public static var botaoIgual = ActionButton{
      		iconeUrl:"{__DIR__}imagens/igual.png" 
      		acaoLSystem:AcaoLSystem{
      		    simbolo:"=";
      		} 
       };
       
public class ActionButton extends CustomNode  {
  public var nome="acao";
  public var width=110;
  public var painelAtual;
  public var botoes:ActionButton[];
  
  public var iconeUrl="{__DIR__}imagens/botoes/rating.png"; //por padr�o aparece uma estrela
  public var acaoLSystem : AcaoLSystem;
  public var coresSeletor:SeletorCores;
  
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
