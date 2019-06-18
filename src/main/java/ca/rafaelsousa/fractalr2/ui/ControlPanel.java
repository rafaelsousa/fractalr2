package ca.rafaelsousa.fractalr2.ui;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javax.swing.*;
import javafx.scene.effect.*;
import java.awt.Dimension;

import javafx.ext.swing.SwingComponent;
import javafx.ext.swing.SwingSlider;
import net.sourceforge.fractalr.Pencil;



class IntSpinner extends SwingComponent {

  var spinner: JSpinner;

  var model: SpinnerNumberModel;

  public var minimum: Integer = 0 on replace {

    model.setMinimum(minimum);

  };

  public var maximum: Integer = 10 on replace {

    model.setMaximum(maximum);

  };

  public var value: Integer = 3 on replace {

    spinner.setValue(value);

  };

  public var stepSize: Integer = 1 on replace {

    model.setStepSize(stepSize);

  };

  public override var width = 50;

  public override var height = 25;

  public override function createJComponent(): JComponent {

    model = new SpinnerNumberModel();

    spinner = new JSpinner(model);

    spinner.setPreferredSize(new Dimension(width, height));

    spinner.addChangeListener(ChangeListener {

      public override function stateChanged(e: ChangeEvent) {

        value = spinner.getValue() as Integer;

      }

    });

    return spinner;

  }
}

public class ControlPanel extends CustomNode  {
    public var angulo = 90;
    public var iteracoes = 0;
    var posBotoes = 75;
   
   	public var limpar = ImageView {
                 focusTraversable: true
                 image: Image{url:"{__DIR__}imagens/limpar.png"}
                 y: 5;
                 x:790;
    };
   	public var designRetangulo : Rectangle = Rectangle{
                   y:-2
                   width: 30 
                   x:785 
                   height: 29
                   arcWidth: 10  
                   cursor:Cursor.HAND
                   arcHeight: 10
                   fill:Color.web("#aaa")
   	          override public var onMouseEntered = function(e:MouseEvent): Void {
        	       stroke=Color.web("#444444");
           	     effect= DropShadow{
           	          radius: 30
           	          spread: .45
           	        	color: bind Color.web("#FF2828")
           	      } 
			  };
           	  override public var onMouseExited= function(e:MouseEvent):Void{
           	    effect= null;
        	       	stroke=Color.web("#f8f8f8")
           	  };
                 override public var onMouseClicked = function(e:MouseEvent):Void{
              		    MainClass.instanciaAtual.pnlControle.angulo=0;
              		    
                        delete FormulaPanel.instanciaAtual.botoes;
              		    FormulaPanel.instanciaAtual.redesenhaBarra();
      		    		for(t in MainClass.instanciaAtual.transformacoes){
      		    		    if(t instanceof TransformationPanel){
      		    		        var transformacao : TransformationPanel = t as TransformationPanel;
	      		    			delete transformacao.botoes;
	      		    			transformacao.redesenhaBarra();
	      		    			transformacao.resetarBarra();
	      		    			
      		    		    }      		    		    
      		    		}
              		    MainClass.instanciaAtual.pencil.clear();
              		    Fractal.limpaCanvas();
                 }        
                  stroke:Color.web("#f8f8f8")
               };
   
   
   var titulo = Text {
       content: "Iteration"
       y:81
       x:840
       font: Font.font("Verdana",10) fill: Color.web("#333")
   }
  public var anguloSlider = SwingSlider{
         translateX: 835  
         translateY: 15
         minimum: 0  
         maximum: 359
 		 width:120
         value: bind angulo with inverse;
         /*
 
	        effect: DropShadow{
             radius: 50
             spread: .65
             color: bind Color.hsb(angulo, 1.0, 1.0 )
         } 
         */       
     }
   public var txtAngulo = Text {
       content: bind "Turn Angle: {angulo}"
       y:13
       x:840
       font: Font.font("Verdana",FontWeight.BOLD,10) fill: Color.web("#000")
   }
   var spinner=IntSpinner {
              minimum: 0
              maximum: 100
              value: bind iteracoes with inverse 
              translateY: 68
              translateX: 890
              width:60
              height:20
   }   
   public var contorno= Rectangle{
      y: 0
      x: 830 
      arcWidth: 10  
      arcHeight: 10
      width: 130 
      height: 134
      fill: Color.web("#eee");
      stroke:Color.web("#666")
   };
   var imgPlay = ImageView {
      focusTraversable: true
      image: bind Pencil.get().img;
      y:posBotoes+21
      x:840
      override public var onMouseClicked = function(e:MouseEvent):Void{
         Fractal.iniciarDesenho();
      }        
      override public var onMouseEntered= function(e:MouseEvent):Void{
      	     effect= DropShadow{
      	          radius: 50
      	          spread: .65
      	          color: bind Color.web("#A1FF6E")
      	      } 
      	    }
      	    override public var onMouseExited= function(e:MouseEvent):Void{
      	     	effect= null
      	    }
   }
   var ttPlay = Text {
       content: bind if(imgPlay.image.url.contains("play")) then "Start" else "Suspend";
       y:posBotoes+50
       x:840
       font: Font.font("Verdana",8) fill: Color.web("#222")
   }
   var ttStop = Text {
       content: "Pause"
       y:posBotoes+50
       x:885
       font: Font.font("Verdana",8) fill: Color.web("#222")
   }
   var imgStop = ImageView {
      focusTraversable: true
      image: Image{url:"{__DIR__}imagens/pause.png"}
      y:posBotoes+21
      x:885       
     override public var onMouseClicked = function(e:MouseEvent):Void{
        Fractal.pararDesenho();
     }
     override public var onMouseEntered= function(e:MouseEvent):Void{
	     effect= DropShadow{
	          radius: 50
	          spread: .65
	          color: bind Color.web("#FF2828")
      } 
    }
    override public var onMouseExited= function(e:MouseEvent):Void{
     	effect= null
    }
   }
   
   var ttReinicio = Text {
       content: "Restart"
       y:posBotoes+50
       x:925
       font: Font.font("Verdana",8) fill: Color.web("#222")
     override public var onMouseClicked = function(e:MouseEvent):Void{
        Fractal.reiniciarDesenho();
     }
   }
   var imgReinicio = ImageView {
      focusTraversable: true
      image: Image{url:"{__DIR__}imagens/restart.png"}
      y:posBotoes+21
      x:930  
    override public var onMouseClicked = function(e:MouseEvent):Void{
            Fractal.reiniciarDesenho();
         }  
      override public var onMouseEntered= function(e:MouseEvent):Void{
  	     effect= DropShadow{
  	          radius: 50
  	          spread: .65
  	          color: bind Color.web("#7197FF" )
  	      } 
  	    }
  	    override public var onMouseExited= function(e:MouseEvent):Void{
  	     	effect= null
  	    }    
   }
   
   override public function create(): Node {
                   Group {
                       content: [
                       			designRetangulo,
								limpar,
                       			contorno,
                       			anguloSlider,
                       			txtAngulo,
                       			titulo,
                       			imgPlay,
                       			ttPlay,
                       			imgStop,
                       			ttStop,
                       			imgReinicio,
                       			ttReinicio,
                       			spinner
                           ]
                   };
               }        
}

public static function proxima() : Void {
	MainClass.instanciaAtual.pnlControle.iteracoes++;
	Fractal.reiniciarDesenho();
}
public static function anterior() : Void {
    if(MainClass.instanciaAtual.pnlControle.iteracoes>0){
		MainClass.instanciaAtual.pnlControle.iteracoes--;
		Fractal.reiniciarDesenho();
    }
}