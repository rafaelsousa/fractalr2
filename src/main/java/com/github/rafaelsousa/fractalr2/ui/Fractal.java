package net.sourceforge.fractalr;
import javafx.scene.shape.*;
//import lib.fractalr.*;
import net.sourceforge.fractalr.lsystem.*;

var pnlTransformacoes:TransformationPanel[];

var botoesTransformacoes:ActionButton[];
var botoesFormula:ActionButton[];
public static var cood:Ponto[];

public static var desenhoModificado : Boolean = false;

public static var animacaoFractal = new FractalAnimation();

var iniciado : Boolean = false;
var iteracao : Number = -1;

public function iniciarDesenho() : Void{
    if(iteracao != MainClass.instanciaAtual.pnlControle.iteracoes or desenhoModificado){
        animacaoFractal.pause();
        iteracao = MainClass.instanciaAtual.pnlControle.iteracoes;
        desenhoModificado = false;
        reiniciarDesenho();
        
    }
    if(not iniciado){
    	animacaoFractal.inicializar(AplicadorRegras.gerarComandos()); 
    	animacaoFractal.playFromStart();
    	iniciado = true;
    }else{
        animacaoFractal.play();
    }
	if(not animacaoFractal.animacao.running){
	    animacaoFractal.play();
	}
    
}
public function pararDesenho() : Void{
    animacaoFractal.pause();
}

public function reiniciarDesenho() : Void {
    if(animacaoFractal.running()){
        animacaoFractal.stop();
    }
    limpaCanvas();
    iniciarDesenho();
}

public function limpaCanvas() : Void{
    iniciado = false;
    animacaoFractal = null;
    animacaoFractal = new FractalAnimation();
    for(content in MainClass.instanciaAtual.design.canvas.content){
        if(content instanceof Line){
            delete content from MainClass.instanciaAtual.design.canvas.content;
        }
    }

}
