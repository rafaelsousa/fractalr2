package ca.renardnumerique.fractalr2;

import java.util.List;

import ca.renardnumerique.fractalr2.lsystem.AplicadorRegras;
import jakarta.inject.Inject;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class Fractal {         

    MainClass mainClass;

    public void iniciarDesenho() {
        if (iteracao != mainClass.getPnlControle().getIteracoes() || desenhoModificado) {
            animacaoFractal.pause();
            iteracao = mainClass.getPnlControle().getIteracoes();
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
        for (Node content : mainClass.getDesign().getChildren()) {
            if (content instanceof Line) {
                mainClass.getDesign().getChildren().remove(content);
            }
        }

    }


    private List<TransformationPanel> pnlTransformacoes;
    private List<ActionButton> botoesTransformacoes;
    private List<ActionButton> botoesFormula;
    private List<Ponto> cood;
    private Boolean desenhoModificado = Boolean.FALSE;
    private FractalAnimation animacaoFractal = new FractalAnimation();
    private Boolean iniciado = Boolean.FALSE;
    private Integer iteracao = -1;

    public List<TransformationPanel> getPnlTransformacoes() {
        return this.pnlTransformacoes;
    }

    public void setPnlTransformacoes(List<TransformationPanel> pnlTransformacoes) {
        this.pnlTransformacoes = pnlTransformacoes;
    }

    public List<ActionButton> getBotoesTransformacoes() {
        return this.botoesTransformacoes;
    }

    public void setBotoesTransformacoes(List<ActionButton> botoesTransformacoes) {
        this.botoesTransformacoes = botoesTransformacoes;
    }

    public List<ActionButton> getBotoesFormula() {
        return this.botoesFormula;
    }

    public void setBotoesFormula(List<ActionButton> botoesFormula) {
        this.botoesFormula = botoesFormula;
    }

    public List<Ponto> getCood() {
        return this.cood;
    }

    public void setCood(List<Ponto> cood) {
        this.cood = cood;
    }

    public Boolean getDesenhoModificado() {
        return this.desenhoModificado;
    }

    public void setDesenhoModificado(Boolean desenhoModificado) {
        this.desenhoModificado = desenhoModificado;
    }

    public FractalAnimation getAnimacaoFractal() {
        return this.animacaoFractal;
    }

    public void setAnimacaoFractal(FractalAnimation animacaoFractal) {
        this.animacaoFractal = animacaoFractal;
    }

    public Boolean getIniciado() {
        return this.iniciado;
    }

    public void setIniciado(Boolean iniciado) {
        this.iniciado = iniciado;
    }

    public Integer getIteracao() {
        return this.iteracao;
    }

    public void setIteracao(Integer iteracao) {
        this.iteracao = iteracao;
    }
}
