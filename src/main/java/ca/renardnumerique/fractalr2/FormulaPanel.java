package ca.renardnumerique.fractalr2;
import java.util.List;

import com.sun.prism.j2d.paint.MultipleGradientPaint.CycleMethod;
import com.sun.prism.paint.Stop;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FormulaPanel extends Group  {
	
	
	private List<ActionButton> botoes;
    private List<ImageView> iconesBotoes;
    private Text formula = new Text("Formulas"); 
    {
       formula.setY(55);
       formula.setX(170);
       formula.setFont(Font.font("Bitstream Vera Sans Bold",12));
       formula.setFill(Color.web("#000"));
   }
    public Rectangle area = new Rectangle();
    {
       area.setX(165) 
       area.setY(33)
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
    				   			new Stop(0.0,Color.web("#FFF"),
    				   			new Stop(1.0,Color.web("#AAA"));
       area.setFill(linearFill);
       area.setStroke(Color.web("#111"));
    }
   
   var areaDeBotoes:Group = Group {}
   
   var contornoAreaDeBotoes:Rectangle =Rectangle {
       x : 243;
       y : area.y+3;
       width : bind (sizeof botoes)*32+2;
       height : 25;
       visible:bind (sizeof botoes)>0;
       fill: LinearGradient {
              startX: 0.0, startY: 0.7, endX: 0.0, endY: 1.5
              proportional: true
              stops: [ Stop { offset: 0.0 color: Color.web("#FFFAC1") },
                       Stop { offset: 1.0 color: Color.web("#aaa") } ]
          }
      stroke:Color.web("#444")
   }
    override public function create(): Node {
	       instanciaAtual=this;
	       Group {
	           content: bind [
	           			area,
	           			formula,
	           			contornoAreaDeBotoes,
	           			areaDeBotoes
	               ]
	       };
    }        
	public function redesenhaBarra(): Void {
	    delete areaDeBotoes.content;
	    var cont=0;
	    for(botao in botoes){
	        var urlNova=botao.icone.image.url;
		  	var rect = Rectangle{
		  	   cursor:Cursor.MOVE
		  	   focusTraversable: true
               y: area.y+5
               x:contornoAreaDeBotoes.x+7+(30*cont)
               width : 26
               height : 22
               arcWidth: 10  
               arcHeight: 10
               fill:botao.fillNormal
               stroke:Color.web("#aaa")
		    }
		  	var img = ImageView{
		  	   cursor:Cursor.MOVE
		  	   focusTraversable: true
               image: Image{url:urlNova }
               y: area.y+6
               x:contornoAreaDeBotoes.x+10+(30*cont)
		    }
		    var nodo=BotaoFormula{
		        rect:rect
		        img:img
		        btn:botao
		    }
		    var drag = DragDrop {
		      	target: nodo 
		      	maxX: 780 
		      	maxY: 74 
		      	onSoltar:trataMoverExcluir
		      }
	        insert  nodo into areaDeBotoes.content;
		    cont++;
	    }
	    Fractal.desenhoModificado = true;
	}
	public function calculaPosicaoBotao(btn:DragDrop): Void {
	    var zx = btn.tx + (btn.target as BotaoFormula).img.x;
	    var zy = btn.ty;
	    var imgAntes:ImageView;
	    var count =0;
	    for(imagem in areaDeBotoes.content){
	        if(zx <= (imagem as BotaoFormula).img.x){
	            imgAntes =(imagem as BotaoFormula).img;
	            break;
	        }
	        count++;
	    }
	    var botaoAtual = (btn.target as BotaoFormula).btn;
	    delete botaoAtual  from botoes;  
	    insert botaoAtual  before botoes[count];  
	}
	
	// trata no reposicionar e o tirar um icone da barra de formulas
	public function trataMoverExcluir(btn:DragDrop): Void {
		if(btn.estaEm(contornoAreaDeBotoes)){
	   	 	calculaPosicaoBotao(btn);
		}else{
	  	   	delete (btn.target as BotaoFormula).btn from botoes;
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
 		delete ActionButton.botaoSeparador from TransformationPanel.instanciaAtual.botoes;
	    TransformationPanel.instanciaAtual.redesenhaBarra();
		if(btn.estaEm(area)){
		 	for(botao in botoes){
		 	    if(botao == ActionButton.botaoSeparador )
		 	    	return;
		 	}
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
		    insert  (btn.target as ActionButton).duplicar()   into botoes;
		}
	    redesenhaBarra();
	}
}
