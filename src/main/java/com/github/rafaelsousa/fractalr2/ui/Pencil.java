package net.sourceforge.fractalr;
import javafx.animation.transition.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import net.sourceforge.fractalr.lsystem.*;
import javafx.scene.paint.*;
import java.lang.*;
import javafx.scene.effect.*;

protected static var lp: Lapis;

public class Lapis extends ImageView  {
   public var escreve=false;
   public var canvas:Group;
	var contadorLinhas=0;
	var cood:Ponto[];
	var mudouX=false;
	var mudouY=false;
	var efeitoEscrita =DropShadow{
		    	          radius: 20
		    	          offsetY:10
		    	          offsetX:-5
		    	          spread: .25
		    	          color: bind Color.web("#BDBD00" )
		     };
   var linhas :PathElement[];
   var imgPlay = Image{url: "{__DIR__}imagens/play.png"};
   var imgPause = Image{url: "{__DIR__}imagens/pause.png"};
   
   public var img= bind  if (animacao.running and not animacao.paused) imgPause else imgPlay;
   override public var translateX on replace{
	     //escreveRastro();
	};
   override public var translateY on replace{
	     //escreveRastro();
	};
	override public var focusTraversable= true;
	override public var  image=Image{url:"{__DIR__}imagens/lapis.png"};

    var caminho = Path {
    }
        
   public function escreveFractal(){
       var inX=MainClass.instanciaAtual.design.areaDesenho.x+10;
      var inY=MainClass.instanciaAtual.design.areaDesenho.y+25;
       clear();
  		var path = Path {
              stroke: Color.web("#29345E")
              strokeWidth: 1
              elements: bind linhas 
         }
  	   var ptAux=MoveTo { x: cood[0].x+inX   y: cood[0].y +inY};
  	   insert path into canvas.content;
	   insert ptAux into linhas;
       for(pt in cood){
           println("pt X:{ pt.x+inX  } Y:{ pt.y+inY }");
            insert LineTo { x: pt.x+inX   y: pt.y+inY } into linhas
       }
       
   }
   public function clear(){
       contadorLinhas=0;
       translateX=0;
       translateY=0;
       posicionaInicio();
       delete linhas;
       
		       
   }
   public function processaCoordenadas(coordenadas:Ponto[]){
       cood=coordenadas;
       //delete linhas;
       //delete caminho.elements;
   }
   protected function insere_caminho(coordX:Integer,coordY:Integer){    
	      insert LineTo { x:  coordX  y: coordY } into caminho.elements;
   }
   protected function posicionaInicio(){
       //this.x=MainClass.instanciaAtual.design.areaDesenho.x+10;
       //this.y=MainClass.instanciaAtual.design.areaDesenho.y-20;
       //println("inicio x: {x} y: {y} translatex: {translateX} translatey: {translateY}");
   }
	public var animacao:PathTransition ;
	
	public function stop(){
	    lp.animacao.stop();
	    posicionaInicio();
	    
	}
	public function play(){
		var tempo =3+2*(Math.log(sizeof caminho.elements)/Math.log(2)); // log2 do numero de pontos;
		///play(Duration.valueOf(tempo*1000));
		constroi(Duration.valueOf(tempo*1000));
	}
	public function constroi(tempo:Duration){
	    escreveFractal();
	}
}
public static function reset():Lapis{
    if(lp != null){
        lp.animacao.stop();
        lp.clear();
    }
    return get();
}
public static function get():Lapis{
    if(lp == null){
        lp = new Lapis();
    }
    return lp;
}