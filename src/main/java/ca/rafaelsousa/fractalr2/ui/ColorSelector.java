package ca.rafaelsousa.fractalr2.ui;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
 class Seletor extends Rectangle  {
       public var nodo:Rectangle;
       public var idCor:Integer;
	   override public var width= 10; 
	   override public var height= 10;
	   override public var arcWidth =5;  
	   override public var arcHeight =5;
	   override public var cursor=Cursor.HAND;
	   override public var fill =Color.web("red");
	   override public var stroke=Color.web("#888");
       override public var onMouseClicked= function(e:MouseEvent):Void{
           cursor=Cursor.HAND;
           selecionado(this);
       }
 	   public var selecionado= function(cor:Seletor){} 
}
public class SeletorCores extends CustomNode  {
    public var posX:Number;
    public var posY:Number;
    public var selecionado:Seletor;
    public var idSelecionado=bind selecionado.idCor;
    public var botaoPai:ActionButton;
    public function setCor(id:Integer){ 
        if(id ==0)
        	selecionado = btnBranco;
        if(id ==1)
        	selecionado = btnVermelho;
        if(id ==2)
        	selecionado = btnVerde;
        if(id ==3)
        	selecionado = btnAzul;
        if(id ==4)
        	selecionado = btnAmarelo;
        corGradiente();
    }
    public var corGradiente= function(){ 
          botaoPai.fillNormal=LinearGradient {
                  startX: 0.0, startY: 0.7, endX: 0.0, endY: 1.5
                  proportional: true
                  stops: [ Stop { offset: 0.2 color:  selecionado.fill as Color },
                           Stop { offset: 1.0 color: Color.web("#444444") }
                          ]
             }; 
    }
    
    public var nodo:Rectangle;
	public var aberto=false;
	var txtMais =Text {
	      x: bind posX+2
	       y:bind posY+10
	       content: "+"
	       font: Font { name: "Bitstream Vera Sans Bold", size: 10} 
	       fill: Color.web("#021F77")
	}
    public var btnVermelho= Seletor{
           idCor:1
           x:bind posX+2;
           nodo:bind nodo; 
           y:bind posY
           fill:Color.web("#D7AEFF");
           visible:bind aberto; 
           selecionado:function(cor:Seletor){
               selecionado=cor;
               corGradiente();
           };
    }
    public var btnVerde= Seletor{
			idCor:2
           nodo:bind nodo; 
           x:bind posX+14
           y:bind posY
           fill:Color.web("#2EFF9E"); 
           visible:bind aberto; 
           selecionado:function(cor:Seletor){
               selecionado=cor;
               corGradiente();
           };
    }
    public var btnAzul= Seletor{
        idCor:3
           nodo:bind nodo; 
           x:bind posX+26
           y:bind posY
           fill:Color.web("#A7FFEC")
           visible:bind aberto; 
           selecionado:function(cor:Seletor){
               selecionado=cor;
               corGradiente();
           };
    }
    public var btnAmarelo= Seletor{
           idCor:4
           nodo:bind nodo; 
           x:bind posX+38
           y:bind posY
           fill:Color.web("#FEFF8D"); 
           visible:bind aberto; 
           selecionado:function(cor:Seletor){
               selecionado=cor;
               corGradiente();
           };
    }
    public var btnBranco= Seletor{
        	idCor:0
           nodo:bind nodo; 
           x:bind posX+50
           y:bind posY
           fill:Color.web("#ffffff"); 
           visible:bind aberto; 
           selecionado:function(cor:Seletor){
               selecionado=cor;
               corGradiente();
           };
    }
    public var btnControle= Rectangle{
           x:bind posX 
           y:bind posY
           width: 12 
           height: 12
           arcWidth: 8  
           arcHeight: 8
           cursor:Cursor.HAND
       	   fill :Color.TRANSPARENT;
       	    
           stroke:Color.web("#888")
           override public var onMouseEntered= function(e:MouseEvent):Void{
    	       stroke=Color.web("#444444");
           }
           override public var onMouseExited= function(e:MouseEvent):Void{
    	       stroke=Color.web("#888");
	           width =12; 
	           if(aberto)
	           	posX=posX+48;
	           aberto=false;
	           txtMais.content="+";
	           cursor=Cursor.HAND;
           }
           override public var onMouseClicked= function(e:MouseEvent):Void{
    	       stroke=Color.web("#1289FF");
	           width =60;
	           if(not aberto) 
	           	posX=posX-48;
	           aberto=true;
	           cursor=Cursor.DEFAULT;
	           txtMais.content="";
           }
       };
    
    override public function create(): Node {
           Group{
           content:[
	           btnControle,
			   txtMais,
			   btnVermelho,btnVerde,btnAzul,btnAmarelo,btnBranco
			 ]
           }
    }
}

 