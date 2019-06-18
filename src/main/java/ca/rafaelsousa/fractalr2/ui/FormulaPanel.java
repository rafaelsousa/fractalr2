package ca.rafaelsousa.fractalr2.ui;
import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class FormulaPanel extends Group  {

	private Text formula;
	private Rectangle area;
	private Rectangle contornoAreaDeBotoes;
	private Group areaDeBotoes;
	
	

	private static FormulaPanel instanciaAtual;	
	private FormulaPanel(){

		//Setting title
		this.formula = new Text(170,55,"Formulas");
		this.formula.setFont(new Font("Bitstream Vera Sans Bold",12)); 
		this.formula.setFill(Color.BLACK);
		
		//Setting area
		this.area = new Rectangle();
		this.area.setX(165);
		this.area.setY(33);
		this.area.setArcWidth(10);
		this.area.setArcHeight(10);
		this.area.setWidth(660);
		this.area.setHeight(33);
		
		Stop[] stops = new Stop[] {new Stop(0,Color.WHITE),new Stop(1,Color.web("#AAA"))};		
		LinearGradient fill = new LinearGradient(0, 0.5, 0, 1.5, true, CycleMethod.NO_CYCLE, stops);
		this.area.setFill(fill);
		this.area.setStroke(Color.web("#111") );
		
		this.contornoAreaDeBotoes = new Rectangle();
		this.contornoAreaDeBotoes.setX(243);
		this.contornoAreaDeBotoes.setY(this.area.getY()+3);
		this.contornoAreaDeBotoes.setWidth(this.botoes.size()*32+2);
		this.contornoAreaDeBotoes.setHeight(25);
		this.contornoAreaDeBotoes.setVisible(botoes.size()>0);
	    this.contornoAreaDeBotoes.setFill(new LinearGradient(0, 0.7, 0, 0, Boolean.TRUE, CycleMethod.NO_CYCLE,
	    										new Stop[]{
	    												new Stop(0, Color.web("#FFFFAC1")),
	    												new Stop(1, Color.web("#AAA")),
	    										}
	    								));   
	    this.contornoAreaDeBotoes.setStroke(Color.web("#444"));
	    
	    this.getChildren().add(area);
	    this.getChildren().add(formula);
	    this.getChildren().add(contornoAreaDeBotoes);
	    this.getChildren().add(areaDeBotoes);
	      
		
	}	
	public static FormulaPanel getInstance(){
		if(instanciaAtual==null){
			instanciaAtual = new FormulaPanel();
		}
		return instanciaAtual;
	}
    	
	public void redesenhaBarra() {
	    //delete areaDeBotoes.content;
		this.areaDeBotoes.getChildren().clear();
	    Integer cont=0;
	    for(BotaoFormula botao : botoes){
	    	
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
