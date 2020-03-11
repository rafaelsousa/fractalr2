package ca.renardnumerique.fractalr2;
import java.util.List;

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

public class FormulaPanel extends Group  {
	
	public static FormulaPanel instanciaAtual = new FormulaPanel();
	public List<ActionButton> botoes;
	public List<ImageView> iconesBotoes;
	public Text formula;
	public Rectangle area;
	
	public Group areaDeBotoes = new Group();
	
	public Rectangle contornoAreaDeBotoes;
	
	private FormulaPanel() {
		formula = new Text("Formulas:");
		formula.setY(55);
		formula.setX(170);
		formula.setFont(new Font("Bitstream Vera Sans Bold", 12));
		formula.setFill(Color.web("#000"));
		
		getArea();
		buildContornoAreaDeBotoes();
		
		getChildren().addAll(area,formula,contornoAreaDeBotoes,areaDeBotoes);
		
	}
	
	
	private void getArea() {
		area = new Rectangle();
		area.setX(165);
		area.setY(33);
		area.setArcWidth(10);
		area.setArcHeight(10);
	    area.setWidth(660);  
	    area.setHeight(33);
	    area.setStroke(Color.web("#111"));	    
	    LinearGradient areaFill = new LinearGradient(0.0, 
	    											0.5, 
	    											0.0, 
	    											1.5, 
	    											true, 
	    											CycleMethod.NO_CYCLE);
	    areaFill.getStops().add(new Stop(0.0, Color.web("#FFF")));
	    areaFill.getStops().add(new Stop(1.0, Color.web("#AAA")));
	    area.setFill(areaFill);
	}
	
	private void buildContornoAreaDeBotoes() {
		contornoAreaDeBotoes = new Rectangle();
		contornoAreaDeBotoes.setX(243);
		contornoAreaDeBotoes.setY(area.getY()+3);
		contornoAreaDeBotoes.setWidth(botoes.size()*32+2);
		contornoAreaDeBotoes.setHeight(25);
		contornoAreaDeBotoes.setVisible(botoes.size()>0);
		contornoAreaDeBotoes.setStroke(Color.web("#444"));
		
		LinearGradient contornoFill = new LinearGradient(0.0, 
				0.7, 
				0.0, 
				1.5, 
				true, 
				CycleMethod.NO_CYCLE);
		contornoFill.getStops().add(new Stop(0.0, Color.web("#FFFAC1")));
		contornoFill.getStops().add(new Stop(1.0, Color.web("#AAA")));
		contornoAreaDeBotoes.setFill(contornoFill);
		
	}
	
	public void redesenhaBarra() {
	    areaDeBotoes.getChildren().clear();
	    Integer cont = 0;
	    botoes.stream().forEach(botao -> {
		    	Url urlNova = botao.getIcone().getImage().getUrl();
		    	Rectangle rect = new Rectangle();
		    	rect.setCursor(Cursor.MOVE);
		    	rect.setFocusTraversable(true);
	            rect.setY(area.getY()+5);
	            rect.setX(contornoAreaDeBotoes.x+7+(30*cont));
	            rect.setWidth(26);
	            rect.setHeight(22);
	            rect.setArcWidth(10);  
	            rect.setArcHeight(10);
	            rect.setFill(botao.fillNormal);
	            rect.setStroke(Color.web("#aaa"));
			  	
	            ImageView img = new ImageView();
		  	    img.setCursor(Cursor.MOVE);
		  	    img.setFocusTraversable(true);
                img.setImage(new Image(urlNova));
                img.setY(area.getY()+6);
                img.setX(contornoAreaDeBotoes.getX()+10+(30*cont));
			    
//			    var nodo=BotaoFormula{
//			        rect:rect
//			        img:img
//			        btn:botao
//			    }
//			    var drag = DragDrop {
//			      	target: nodo 
//			      	maxX: 780 
//			      	maxY: 74 
//			      	onSoltar:trataMoverExcluir
//			      }
//		        insert nodo into areaDeBotoes.content;
			    cont++;
		    }
	    );
	    
//	    Fractal.desenhoModificado = true;
	}
	public void calculaPosicaoBotao(btn:DragDrop) {
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
