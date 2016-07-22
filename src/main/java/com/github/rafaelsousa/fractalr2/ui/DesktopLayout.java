package net.sourceforge.fractalr;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;


public class DesktopLayout extends CustomNode  {
   
   public var inicioDesenhoY=140;
   // contem o visual estatico do sistema.
  
   var fundoSistema : ImageView = ImageView {
           focusTraversable: true
           image: Image{url:"{__DIR__}imagens/bg_sistema.png"}
           y:0
           x:0        
   };
  
    
    // sera usado pela classe Lapis, para limites de desenho.
    public var areaDesenho : Rectangle = Rectangle{
        x: 236 
        y: bind inicioDesenhoY
        //arcWidth: 10  
        //arcHeight: 10
        width: 500  
        height: 500
        cursor:Cursor.DEFAULT
        
      	/*fill: LinearGradient {
	              startX: 0.0, startY: 0.0, endX: 0.0, endY: 0.05
	              proportional: true
	              stops: [ Stop { offset: 0.0 color: Color.web("#999") },
	                       Stop { offset: 1.0 color: Color.web("#fff") } ]
	          }*/
	  	fill: Color.web("#FFF");
	  	//stroke:Color.web("#000")
    };
     var imgAnterior : ImageView = ImageView {
		        opacity: 0.1
               focusTraversable: true
               image: Image{url:"{__DIR__}imagens/anterior.png"}
        	   x: 166 
               y: bind inicioDesenhoY+200
		};
		
     var imgProximo : ImageView = ImageView {
		        opacity: 0.1
               focusTraversable: true
               image: Image{url:"{__DIR__}imagens/proximo.png"}
		        y: bind inicioDesenhoY+200
		        x: 930 
    };
          
    // sera usado pela classe Lapis, para limites de desenho.
    public var proximo : Rectangle = Rectangle{
        x: 940 
        y: bind inicioDesenhoY
        arcWidth: 10  
        arcHeight: 10
        width: 20  
        height: 575
        opacity: 0.1
        cursor:Cursor.HAND
	    fill: LinearGradient {
	              startX: 0.0, startY: 0.0, endX: 0.0, endY: 0.05
	              proportional: true
	              stops: [ Stop { offset: 0.0 color: Color.web("#999") },
	                       Stop { offset: 1.0 color: Color.web("green") } ]
	          }
	  	stroke:Color.web("#888");
	  	override public var onMouseEntered= function(e:MouseEvent):Void{
  	        opacity= 0.2;
  	        imgProximo.opacity= 0.8;
  	    }
	  	override public var onMouseClicked= function(e:MouseEvent):Void{
  	    	ControlPanel.proxima();
  	   	}  	    
  	    override public var onMouseExited= function(e:MouseEvent):Void{
  	        imgProximo.opacity= 0.1;
  	        opacity= 0.1;
  	    }
    };
    public var anterior : Rectangle = Rectangle{
        x: 166 
        y: bind inicioDesenhoY
        opacity: 0.1
        arcWidth: 10  
        arcHeight: 10
        width: 20  
        height: 575
        cursor:Cursor.HAND
      /*fill: LinearGradient {
	              startX: 0.0, startY: 0.0, endX: 0.0, endY: 0.05
	              proportional: true
	              stops: [ Stop { offset: 0.0 color: Color.web("#999") },
	                       Stop { offset: 1.0 color: Color.web("red") } ]
	          }*/
		fill: Color.web("#FFF");
	  	override public var onMouseEntered= function(e:MouseEvent):Void{
  	        imgAnterior.opacity= 0.5;
	        opacity =0.2;
	    }
	  	override public var onMouseClicked= function(e:MouseEvent):Void{
	        ControlPanel.anterior();
	    }
	    override public var onMouseExited= function(e:MouseEvent):Void{
  	        imgAnterior.opacity= 0.1;
	        opacity= 0.1;
	    }	          
	  stroke:Color.web("#888")
    };
    
    //logomarga do sistema
   
   var logo = ImageView {
           focusTraversable: true
           image: Image{url:"{__DIR__}imagens/logo.png"}
           y:4        
           x:10        
      };
 
    
    //realiza o design
   public var canvas : Group = Group {
                           content: [
                           			fundoSistema,
                           			logo,
                           			areaDesenho                           			
                               ]
                               
                       };
    override public function create(): Node {
	 	return canvas;
    }        
}
