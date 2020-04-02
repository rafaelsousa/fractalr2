package ca.renardnumerique.fractalr2;

import javafx.scene.shape.Line;
import lombok.Data;

import java.util.List;


@Data
public class Fractal {

    public static Fractal instance = new Fractal();

    private Fractal() {
    }

    private List<TransformationPanel> pnlTransformacoes;
    private List<ActionButton> botoesTransformacoes;
    private List<ActionButton> botoesFormula;

    private List<Ponto> cood;

    private Boolean desenhoModificado = Boolean.FALSE;

    private FractalAnimation animacaoFractal = new FractalAnimation();

    private Boolean iniciado = Boolean.FALSE;
    private Integer iteracao = -1;

    public void iniciarDesenho() {
        if (iteracao != MainClass.getInstance().getPnlControle().getIteracoes() || desenhoModificado) {
            animacaoFractal.pause();
            iteracao = MainClass.getInstance().getPnlControle().getIteracoes();
            desenhoModificado = false;
            reiniciarDesenho();
        }
        if (!iniciado) {
            animacaoFractal.inicializar(AplicadorRegras.gerarComandos());
            animacaoFractal.playFromStart();
            iniciado = true;
        } else {
            animacaoFractal.play();
        }
        if (not animacaoFractal.animacao.running){
            animacaoFractal.play();
        }

    }

    public function pararDesenho() :Void

    {
        animacaoFractal.pause();
    }

    public function reiniciarDesenho() :Void

    {
        if (animacaoFractal.running()) {
            animacaoFractal.stop();
        }
        limpaCanvas();
        iniciarDesenho();
    }

    public function limpaCanvas() :Void

    {
        iniciado = false;
        animacaoFractal = null;
        animacaoFractal = new FractalAnimation();
        for (content in MainClass.instanciaAtual.design.canvas.content) {
            if (content instanceof Line) {
                delete content from MainClass.instanciaAtual.design.canvas.content;
            }
        }

    }
}
