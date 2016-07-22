package net.sourceforge.fractalr;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.*;
import java.lang.*;
import java.util.*;
import javafx.scene.text.*;
import javafx.geometry.Point2D;
import net.sourceforge.fractalr.lsystem.AcaoLSystem;



public static var coodEx:Point2D[];
public static var qteExemplos=0;
public static var buttonHeight = 25;
class Exemplo extends CustomNode  {
    var sequencial : Integer = qteExemplos++;
    var pnl :ExamplePanel;
	var titulo:String; 
	var formula:String; 
	var transformacoes:String[]; 
	var angulo:Integer; 
    var fundo = ImageView {
           focusTraversable: true
           image: Image{url:"{__DIR__}imagens/bgexemplo.png"}
	       y : 115+(sequencial*25);
           x:4        
      };
	 var contorno:Rectangle =Rectangle {
      	          x : 7;
		       	  y : 115+(sequencial*buttonHeight);
      		      cursor:Cursor.HAND
      	          width : 150;
      	          height : 20;
      	          fill:Color.TRANSPARENT
      	      }       
	var txtTitulo = Text {
	       content:bind titulo
	       y:132+(sequencial*buttonHeight)
	       x:10
	       font: Font.font("Verdana",FontWeight.BOLD,10) 
	       fill: Color.web("#1C6AB7")
	       override public var onMouseClicked = function(e:MouseEvent):Void{
	               doFractal();
          	}   
	   }
	 public function doFractal() {
	     	pnl.exemploRodando = this;
		    var cod:Integer;
		    var mapAndar:Map=new HashMap();
		    var mapProduzir:Map=new HashMap();
		    var mapFazerRetornar:Map=new HashMap();
		    delete FormulaPanel.instanciaAtual.botoes;
		    MainClass.instanciaAtual.pnlControle.angulo=angulo;
	     	var controle ="";
	     	for(btn in formula.toCharArray()){
	     	    if(btn.toString() == " ")
	     	     continue;
		     	cod=1;
		     	var cor = 0;
	     	    if(btn.toString() == "-"){
	     	        cod = AcaoLSystem.ACAO_GIRAR_ESQUERDA;
	     	    }
	     	    else if(btn.toString() == "+"){
	     	        cod = AcaoLSystem.ACAO_GIRAR_DIREITA;
	     	    }
	     	    else{
	     	    	//Check for transformation action
	     	    	var minusculas : String = new String("abcdefghikjlmnopqrstuvxyz"); 
 	    	     	    if(minusculas.indexOf(btn.toString())>=0){
 	    	     	    	cod = AcaoLSystem.ACAO_ANDAR;
 	    	     	    	cor = minusculas.indexOf(btn.toString());
 	    	     	    }
 	    	     	    var maiusculas : String = minusculas.toUpperCase();
 	    	     	    if(maiusculas.indexOf(btn.toString())>=0){
 	     	            	cod = AcaoLSystem.ACAO_EXPANDIR;
 	     	            	cor = maiusculas.indexOf(btn.toString());
 	     	            }
 	     	            var fazerRetornar : String = new String("123456789");
						if(fazerRetornar.indexOf(btn.toString())>=0){
 	     	            	cod = AcaoLSystem.ACAO_FAZER_RETORNAR;
 	     	            	cor = fazerRetornar.indexOf(btn.toString());
 	     	            }
 	    		}
	     	    var botao =ButtonPanel.getBotao(cod);
	     	    if(cod == AcaoLSystem.ACAO_ANDAR){
	     	    	if(mapAndar.get(btn) == null){
	     	    	    mapAndar.put(btn,cor);
 	    	    	    //println("id cor:{nroCor} simbolo:{btn} {map}");
	     	    	}
	     	    	botao.coresSeletor.setCor(mapAndar.get(btn) as Integer);
	     	    }
	     	    if(cod == AcaoLSystem.ACAO_EXPANDIR){
 	    	    	if(mapProduzir.get(btn) == null){
	 	    	     	mapProduzir.put(btn,cor);
	 	     	    	//println("id cor:{nroCor} simbolo:{btn} {map}");
	 	    	     	}
 	    	     	botao.coresSeletor.setCor(mapProduzir.get(btn) as Integer);
 	    	    }
				if(cod == AcaoLSystem.ACAO_FAZER_RETORNAR){
 	    	    	if(mapFazerRetornar.get(btn) == null){
	 	    	     	mapFazerRetornar.put(btn,cor);
	 	     	    	//println("id cor:{nroCor} simbolo:{btn} {map}");
	 	    	     	}
 	    	     	botao.coresSeletor.setCor(mapFazerRetornar.get(btn) as Integer);
 	    	    }
     	    	insert botao.duplicar()  into FormulaPanel.instanciaAtual.botoes;
	     	}
	     	FormulaPanel.instanciaAtual.redesenhaBarra();
			TransformationPanel.instanciaAtual.resetarBarra();
			delete TransformationPanel.instanciaAtual.botoes;
			var count =0;
			for(transformacao in transformacoes){
			    count ++;
			    if(count>1)
		        	TransformationPanel.instanciaAtual.adicionarBarra();
		     	for(btn in transformacao.toCharArray()){
		     	    if(btn.toString() == " "){
		     	     	continue;
		     	    }
			     	cod=1;
			     	var cor = 0;
		     	    if(btn.toString() == "-"){
		     	        cod = AcaoLSystem.ACAO_GIRAR_ESQUERDA;
		     	    }
		     	    else if(btn.toString() == "+"){
		     	        cod = AcaoLSystem.ACAO_GIRAR_DIREITA;
		     	    }
		     	    else if(btn.toString() == "="){
		     	    	insert ActionButton.botaoIgual.duplicar()  into TransformationPanel.instanciaAtual.botoes;
		     	    	continue;
		     	    }else{
		     	        //check for transformation action
		     	        var minusculas : String = new String("abcdefghikjlmnopqrstuvxyz"); 
	     	            if(minusculas.indexOf(btn.toString())>0){
	     	                cod = AcaoLSystem.ACAO_ANDAR;
	     	                cor = minusculas.indexOf(btn.toString());
	     	            }
	     	            var maiusculas : String = minusculas.toUpperCase();
	     	            if(maiusculas.indexOf(btn.toString())>0){
 	            	    	cod = AcaoLSystem.ACAO_EXPANDIR;
 	            	    	cor = maiusculas.indexOf(btn.toString());
 	            	    }
 	            	    //Verificar se � uma a��o de fazer retornar
 	            	    var fazerRetornar : String = new String("123456789");
 	            	    if(fazerRetornar.indexOf(btn.toString())>=0){
 	            	        cod = AcaoLSystem.ACAO_FAZER_RETORNAR;
 	            	        cor = fazerRetornar.indexOf(btn.toString());
 	            	    }
		     	    }
		     	    var botao =ButtonPanel.getBotao(cod);
					if(cod == AcaoLSystem.ACAO_ANDAR){
		     	    	if(mapAndar.get(btn) == null){
		     	    	    mapAndar.put(btn,cor);
	 	    	    	    //println("id cor:{nroCor} simbolo:{btn} {map}");
		     	    	}
		     	    	botao.coresSeletor.setCor(mapAndar.get(btn) as Integer);
		     	    }
		     	    if(cod == AcaoLSystem.ACAO_EXPANDIR){
	 	    	    	if(mapProduzir.get(btn) == null){
		 	    	     	mapProduzir.put(btn,cor);
		 	     	    	//println("id cor:{nroCor} simbolo:{btn} {map}");
		 	    	     }
	 	    	     	botao.coresSeletor.setCor(mapProduzir.get(btn) as Integer);
	 	    	    }
					if(cod == AcaoLSystem.ACAO_FAZER_RETORNAR){
	 	    	    	if(mapFazerRetornar.get(btn) == null){
		 	    	     	mapFazerRetornar.put(btn,cor);
		 	     	    	//println("id cor:{nroCor} simbolo:{btn} {map}");
		 	    	    }
	 	    	     	botao.coresSeletor.setCor(mapFazerRetornar.get(btn) as Integer);
	 	    	    }
	     	    	insert botao.duplicar()  into TransformationPanel.instanciaAtual.botoes;
		     	}
		     	TransformationPanel.instanciaAtual.redesenhaBarra();
		     	System.gc();
			}
	     	
	     	
	}    
   	override public var onMouseClicked = function(e:MouseEvent):Void{
        doFractal();
   	}   
	override public function create(): Node {
		Group {
           content: [
           			fundo,contorno,txtTitulo
               ]
       };    
	}    
}
public static function rodarExemplo(exemplo) {
	(exemplo as Exemplo).doFractal();	  
}
public class ExamplePanel extends CustomNode  {
	public var exemploRodando=null;
   var titulo = Text {
       content: "Exemplos"
       y:100
       x:90
       font: Font.font("Verdana",FontWeight.BOLD,12) fill: Color.web("#9f4545")
   }
      var rdp = ImageView {
           focusTraversable: true
           image: Image{url:"{__DIR__}imagens/bgexemplordp.png"}
   	       y :bind 115+(qteExemplos*buttonHeight);
            x:2        
              
      }
   var flocoNeve = Exemplo {
       pnl:this
       titulo:"Floco de neve Koch "
       formula:"a--a--a"
       transformacoes:["a=a+a--a+a"]
       angulo:60
   }
   var curvaPeano = Exemplo {
       pnl:this
       titulo:"Curva de Peano "
       formula:"a"
       transformacoes:"a=a-a+a+a+a-a-a-a+a"
       angulo:90
   }
   var curvaGosper = Exemplo {
       pnl:this
       titulo:"Curva de Gosper"
       formula:"a"
       transformacoes:["a=a+b++b-a--aa-b+ ","b=-a+bb++b+a--a-b"]
       angulo:60
   }
   var fractalGrama = Exemplo {
          pnl:this
          titulo:"Fractal Grama"
          formula:"B"
          transformacoes:["B=cb-1+b2-B","1=3+B","2=+abB","3=B" ,"b=bb"];
          //transformacoes:["X=f12","1=+f","2=-f"]
          angulo:25
   }
   var curvaDragao = Exemplo {
       pnl:this
       titulo:"Curva do Dragao"
       formula:"B"
       transformacoes:["B=B+Ca+","C=-aB-C"]
       angulo:90
   }
   var trianguloSierpinski = Exemplo {
       pnl:this
       titulo:"Triangulo Sierpinski"
       formula:"aBa--aa--aa"
       transformacoes:["B=--aBa++aBa++aBa--","a=aa"]
       angulo:60
   }
    override public function create(): Node {
                   Group {
                       content: [
                       			titulo,
                       			flocoNeve,
                       			curvaPeano,
                       			curvaGosper,
                       			fractalGrama,
                       			curvaDragao,
                       			trianguloSierpinski
                       			
                           ]
                   };
               }        
}
