package net.sourceforge.fractalr;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.ext.swing.SwingButton;


//simular instancia unica.
public static var instanciaAtual:TransformationPanel;
class BotaoTransformacoes extends CustomNode  {
    public var rect:Rectangle;
    public var img:ImageView;
    public var btn:ActionButton;
	override public function create(): Node {
   	       Group {
   	           content: bind [
   	           			rect,img
   	               ]
   	       };
       }   
}
public static var quantidadeTransformacoes=1;
public class TransformationPanel extends CustomNode  {
	public var botoes:ActionButton[];
    public var iconesBotoes:ImageView[];
    var incrementoY=26;
    public var posicao =quantidadeTransformacoes;
    var ultimo=true;
    var txtSinal="";
    var posY=bind 44+(incrementoY*posicao);
   public var area= Rectangle{
          x: 165 
          y: bind posY
          arcWidth: 10  
          arcHeight: 10
          width: 660  
          height: 22
          fill: LinearGradient {
                      startX: 0.0, startY: 0.0, endX: 0.0, endY: 1.0
                      proportional: true
                      stops: [ Stop { offset: 0.0 color: Color.web("#f1ffde") },
                               Stop { offset: 1.0 color: Color.web("#c8eab2") } ]
                  }
          stroke:Color.web("#f8f8f8")
      };
   public var btnDpl= SwingButton { // swing button B
	        translateX: 813 
	        translateY: bind posY
            width: 10  
            height: 22
           text: "b"
           action: function(){
	           (this.parent.parent as TransformationPanel).trataAdicaoExclucao();
           }
       }
   public var btnDuplicar= Rectangle{
          x: 813 
          y: bind posY
          arcWidth: 10  
          arcHeight: 10
          width: 10  
          height: 22
          cursor:Cursor.HAND
          fill:  LinearGradient {
                      startX: 0.0, startY: 0.0, endX: 0.0, endY: 1.0
                      proportional: true
                      stops: [ Stop { offset: 0.0 color: Color.web("#3F9B2F") },
                               Stop { offset: 1.0 color: Color.web("#c8eab2") } ]
                  }
          stroke:Color.web("#f8f8f8")
        override public var onMouseClicked = function(e:MouseEvent):Void{
           (this.parent.parent as TransformationPanel).trataAdicaoExclucao();
         }
      };
      public function trataAdicaoExclucao(){
    		//println("posicao: {posicao} tamanho: {sizeof MainClass.instanciaAtual.transformacoes} ");
    		if(ultimo and (sizeof botoes >2)){
                adicionarBarra();
            }else{
                excluirBarra();
            }
	  }
      public function adicionarBarra(){
  				MainClass.instanciaAtual.design.inicioDesenhoY+=incrementoY;
  				MainClass.instanciaAtual.alturaSistema+=incrementoY;
  				var aux = new TransformationPanel();
  				insert aux after MainClass.instanciaAtual.transformacoes[3];
  				txtSinal="-";
  				ultimo=false;
  				instanciaAtual = aux;
  				Fractal.limpaCanvas();
  				MainClass.instanciaAtual.pencil.clear();
      }
      public function resetarBarra(){
			while((sizeof MainClass.instanciaAtual.transformacoes >=8)){
			    excluirBarra();
			}
          
      }
      public function excluirBarra(){
			if((sizeof MainClass.instanciaAtual.transformacoes >=8)){
  	  				MainClass.instanciaAtual.design.inicioDesenhoY-=incrementoY;
  	  				MainClass.instanciaAtual.alturaSistema-=incrementoY;
  	  				quantidadeTransformacoes--;
  	  				delete this from MainClass.instanciaAtual.transformacoes;
  	  				var i =quantidadeTransformacoes-1;
  	  				var primeiro:TransformationPanel;
  	  				for(pnl in MainClass.instanciaAtual.transformacoes){
  	  				    if(pnl instanceof TransformationPanel){
  	  				        if(primeiro == null)
  	  				        	primeiro=(pnl as TransformationPanel);
    				    	(pnl as TransformationPanel).posicao=i--;
  	  				    }
  	  				} 
  	  				primeiro.txtSinal="+";
  	  				primeiro.ultimo=true;
	  				instanciaAtual= primeiro ;
                  }          
      }
    var mais = Text {
       content: bind txtSinal
       y:bind btnDuplicar.y+15
       x:bind btnDuplicar.x+1
       font: Font { name: "Bitstream Vera Sans Bold", size: 10} fill: Color.web("#3a5833")
   }
    var transformacao = Text {
       content: "Transformations"
       y:bind area.y+17
       x:bind area.x+5
       font: Font { name: "Bitstream Vera Sans Bold", size: 10} fill: Color.web("#3a5833")
   }
   var areaDeBotoes:Group = Group {}
   
   var contornoAreaDeBotoes:Rectangle =Rectangle {
       x : bind area.x+ 100;
       y : bind area.y+1;
       width : bind (sizeof botoes)*26+10;
       height : 20;
       visible:bind (sizeof botoes)>0;
       fill: LinearGradient {
              startX: 0.0, startY: 0.7, endX: 0.0, endY: 1.5
              proportional: true
              stops: [ Stop { offset: 0.0 color: Color.web("#c8eab2") },
                       Stop { offset: 1.0 color: Color.web("#aaa") } ]
          }
      stroke:Color.web("#444")
   }
   
   
    override public function create(): Node {
	       instanciaAtual=this;
	       quantidadeTransformacoes++;
	       Group {
	           content: bind [
	           			area,
	           			transformacao,
	           			contornoAreaDeBotoes,
	           			areaDeBotoes,
	           			btnDuplicar,
	           			mais
	               ]
	       };
    }        
	public function redesenhaBarra(): Void {
	    delete areaDeBotoes.content;
	    var cont=0;
	    if(ultimo and (sizeof botoes)>2){
	        txtSinal="+";
	    }else{
	        if((sizeof MainClass.instanciaAtual.transformacoes >6)){
		        txtSinal="-";
	        }
	    }
	    for(botao in botoes){
	        var urlNova=botao.icone.image.url;
	        var alt=16;
	        var larg=24;
	        
	        var img = ImageView{
		  	   cursor:Cursor.MOVE
		  	   focusTraversable: true
               image: Image{url:urlNova  width:larg height:alt}
               y: bind area.y+3
               x:contornoAreaDeBotoes.x+5+(26*cont)
               
		    }
	        var rect = Rectangle{
		  	   cursor:Cursor.MOVE
		  	   focusTraversable: true
               y: bind area.y+3
               x:contornoAreaDeBotoes.x+5+(26*cont)
               width : larg
               height : alt
               arcWidth: 10  
               arcHeight: 10
               fill:botao.fillNormal
               stroke:Color.web("#aaa")
		    }
	       var nodo=BotaoTransformacoes{
	       	 img:img
	       	 rect:rect
	       	 btn:botao
	       }
		   var drag = DragDrop {
		      	target: nodo 
		      	maxX: 900 
		      	maxY: 874 
		      	onSoltar:trataMoverExcluir
		      }
	        insert  nodo into areaDeBotoes.content;
		    cont++;
	    }
	    Fractal.desenhoModificado = true;
	}
	public function calculaPosicaoBotao(btn:DragDrop): Void {
	    var zx = btn.tx + (btn.target as BotaoTransformacoes).rect.x;
	    var zy = btn.ty;
	    var btransAntes:ImageView;
	    var count =0;
	    for(btrans in areaDeBotoes.content){
	        if(zx <= (btrans as BotaoTransformacoes).img.x){
	            btransAntes =(btrans as BotaoTransformacoes).img;
	            break;
	        }
	        count++;
	    }
	    var botaoAtual = (btn.target as BotaoTransformacoes).btn;
	    delete botaoAtual  from botoes;  
	    insert botaoAtual before botoes[count];  
	}
	
	// trata no reposicionar e o tirar um icone da barra de formulas
	public function trataMoverExcluir(btn:DragDrop): Void {
		if(btn.estaEm(contornoAreaDeBotoes)){
	   	 	calculaPosicaoBotao(btn);
		}else{
	  	   	delete (btn.target as BotaoTransformacoes).btn from botoes;
	  	   	if(sizeof botoes == 1){
	  	   		delete ActionButton.botaoIgual from botoes;
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
