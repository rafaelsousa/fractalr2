package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AplicadorRegras;
import javafx.animation.Animation;
import javafx.scene.Node;
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
            animacaoFractal.inicializar(AplicadorRegras.instance.gerarComandos());
            animacaoFractal.playFromStart();
            iniciado = true;
        } else {
            animacaoFractal.play();
        }
        if (!animacaoFractal.animacao.getStatus().equals(Animation.Status.RUNNING)) {
            animacaoFractal.play();
        }

    }

    public void pararDesenho() {
        animacaoFractal.pause();
    }

    public void reiniciarDesenho() {
        if (animacaoFractal.running()) {
            animacaoFractal.stop();
        }
        limpaCanvas();
        iniciarDesenho();
    }

    public void limpaCanvas() {
        iniciado = false;
        animacaoFractal = null;
        animacaoFractal = new FractalAnimation();
        for (Node content : MainClass.getInstance().getDesign().getChildren()) {
            if (content instanceof Line) {
                MainClass.getInstance().getDesign().getChildren().remove(content);
            }
        }

    }
}
