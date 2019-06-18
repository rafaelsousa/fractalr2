package ca.rafaelsousa.fractalr2;

import ca.rafaelsousa.fractalr2.lsystem.Ponto;
import ca.rafaelsousa.fractalr2.ui.ActionButton;
import ca.rafaelsousa.fractalr2.ui.FractalAnimation;
import ca.rafaelsousa.fractalr2.ui.TransformationPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FractalR2 extends Application {


    private TransformationPanel [] pnlTransformacoes:
    private ActionButton [] botoesTransformacoes;
    private ActionButton [] botoesFormula;
    public Ponto[] cood;
    public Boolean desenhoModificado = Boolean.FALSE;
    public FractalAnimation animacaoFractal;
    public Boolean iniciado = Boolean.FALSE;
    public Integer iteracao = -1;

    // TODO : change the logging system to standard java.util.Logging
    //private static final Logger log = LoggerFactory.getLogger(FractalR2.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
    	
    	StackPane root = new StackPane();

    	Scene scene = new Scene(root,800,600);

        stage.setTitle("Fractal by Representations - Ver 2.0");
        stage.setScene(scene);
        stage.show();
    }

    public void iniciarDesenho() {
        if(iteracao != MainClass.instanciaAtual.pnlControle.iteracoes || desenhoModificado){
            animacaoFractal.pause();
            iteracao = MainClass.instanciaAtual.pnlControle.iteracoes;
            desenhoModificado = Boolean.FALSE;
            reiniciarDesenho();

        }
        if(!iniciado){
            animacaoFractal.inicializar(AplicadorRegras.gerarComandos());
            animacaoFractal.playFromStart();
            iniciado = true;
        }else{
            animacaoFractal.play();
        }
        if(!animacaoFractal.animacao.running){
            animacaoFractal.play();
        }

    }
    public void pararDesenho() {
        animacaoFractal.pause();
    }

    public void reiniciarDesenho() {
        if(animacaoFractal.running()){
            animacaoFractal.stop();
        }
        limpaCanvas();
        iniciarDesenho();
    }

    public void limpaCanvas() {
        iniciado = false;
        animacaoFractal = null;
        animacaoFractal = new FractalAnimation();
        for(content in MainClass.instanciaAtual.design.canvas.content){
            if(content instanceof Line){
                delete content from MainClass.instanciaAtual.design.canvas.content;
            }
        }

    }


}


