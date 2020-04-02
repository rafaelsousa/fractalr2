package ca.renardnumerique.fractalr2;

import javafx.animation.transition.*;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

import java.util.List;


public class Pencil extends ImageView  {
   private Boolean escreve=false;
   private Group canvas;
	private Integer contadorLinhas=0;
	private List<Ponto> cood;
	private Boolean mudouX = Boolean.FALSE;
	private Boolean mudouY = Boolean.FALSE;
	private DropShadow efeitoEscrita = new DropShadow();
	{
      efeitoEscrita.setRadius(20);
      efeitoEscrita.setOffsetY(10);
      efeitoEscrita.setOffsetX(-5);
      efeitoEscrita.setSpread(.25);
      efeitoEscrita.setColor(Color.web("#BDBD00" ));
	};
   private List<PathElement> linhas;
   private Image imgPlay = new Image("imagens/play.png");
   private Image imgPause = new Image("{__DIR__}imagens/pause.png");

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