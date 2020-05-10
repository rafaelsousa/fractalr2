/**
 * Script para desenvolvimento e teste da animacao em paralelo em JavaFX
 *
 * @author rafael
 */
package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.Comando;
import ca.renardnumerique.fractalr2.ui.ApplicationCanvas;
import ca.renardnumerique.fractalr2.ui.Pencil;
import jakarta.inject.Inject;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.ArrayList;
import java.util.List;


public class FractalAnimation {

    private Double x;
    private Double y;

    @Inject
    private ApplicationCanvas applicationCanvas;

    @Inject
    private Pencil pencil;

    public List<Transition> transicoesGerais = new ArrayList<>();
    public SequentialTransition animacao = new SequentialTransition();

    public FractalAnimation() {
        initializeComponents();
    }

    private void initializeComponents() {
        animacao.getChildren().addAll(transicoesGerais);
    }

    private ApplicationLayout applicationLayout;


    public void acaoConclusaoTimeLine() {
        applicationCanvas.requestLayout();
    }

    public void inicializar(List<Comando> comandos) {
        ParallelTransition transicaoAnterior = null;
        for (int iteracao = 0; iteracao < comandos.size(); iteracao++) {
            Comando comando = comandos.get(iteracao);
            if (comando.getTipoComando() == Comando.MOVER) {
                x = comando.getCoordenadaInicial().getX();
                y = comando.getCoordenadaInicial().getY();

                Double endx = comando.getCoordenadaFinal().getX();
                Double endy = comando.getCoordenadaFinal().getY();

                Line linha = new Line(x, y, endx, endy);
                linha.setStroke(Color.BLACK);
                linha.setStrokeWidth(1);


                MoveTo moveTo = new MoveTo(x, y);
                LineTo lineTo = new LineTo((x + endx) / 2, (y + endy) / 2);
                Path caminho = new Path();
                caminho.getElements().addAll(moveTo, lineTo);

                MoveTo movetoLapis = new MoveTo(x + pencil.getPencilImage().getImage().getWidth() / 2 - 3, y - pencil.getPencilImage().getImage().getHeight() / 2);
                LineTo linetoLapis = new LineTo(endx + pencil.getPencilImage().getImage().getWidth() / 2 - 3, endy - pencil.getPencilImage().getImage().getHeight() / 2);

                Path pencilTrack = new Path(movetoLapis, linetoLapis);


                FadeTransition fadeTransition = new FadeTransition();
                fadeTransition.setDuration(comando.getDuracao());
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);

                ScaleTransition scaleTransition = new ScaleTransition();
                scaleTransition.setDuration(comando.getDuracao());
                scaleTransition.setFromX(0);
                scaleTransition.setFromY(0);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);

                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(comando.getDuracao());
                pathTransition.setPath(caminho);

                ParallelTransition transicoesDesenho = new ParallelTransition();
                transicoesDesenho.setNode(linha);
                transicoesDesenho.getChildren().addAll(fadeTransition, scaleTransition, pathTransition);

                PathTransition transicaoLapis = new PathTransition();
                transicaoLapis.setPath(pencilTrack);
                transicaoLapis.setNode(pencil.getPencilImage());
                transicaoLapis.setDuration(comando.getDuracao());

                ParallelTransition transicaoParalela = new ParallelTransition();
                transicaoParalela.getChildren().addAll(transicoesDesenho, transicaoLapis);

                //Immediate insertion happens only in the first iteration.
                if (iteracao == 0) {
                    applicationCanvas.getChildren().add(transicoesDesenho.getNode());
                }

                if (transicaoAnterior != null) {
                    transicaoAnterior.setOnFinished(e ->
                            applicationCanvas.getChildren().add(transicoesDesenho.getNode())
                    );
                }
                transicoesGerais.add(transicaoParalela);
                transicaoAnterior = transicaoParalela;
            }

            if (comando.getTipoComando() == Comando.TRANSPORTAR) {
                x = comando.getCoordenadaInicial().getX();
                y = comando.getCoordenadaInicial().getY();

                Double endx = comando.getCoordenadaFinal().getX();
                Double endy = comando.getCoordenadaFinal().getY();

                MoveTo moveTo = new MoveTo(x, y);
                LineTo lineTo = new LineTo((x + endx) / 2, (y + endy) / 2);
                Path caminho = new Path();
                caminho.getElements().addAll(moveTo, lineTo);

                Path caminhoLapis = new Path();
                caminhoLapis.getElements().addAll(
                        new MoveTo(x + pencil.getPencilImage().getImage().getWidth() / 2 - 3, y - pencil.getPencilImage().getImage().getHeight() / 2),
                        new LineTo(endx + pencil.getPencilImage().getImage().getWidth() / 2 - 3, endy - pencil.getPencilImage().getImage().getHeight() / 2)
                );

                PathTransition transicaoLapis = new PathTransition();
                transicaoLapis.setPath(caminhoLapis);
                transicaoLapis.setNode(pencil.getPencilImage());

                transicoesGerais.add(transicaoLapis);

            }
        }
    }

    public void play() {
        animacao.play();
    }

    public void pause() {
        if (animacao.getStatus().equals(Animation.Status.RUNNING)) {
            animacao.pause();
        } else {
            animacao.play();
        }
    }

    public void playFromStart() {
        animacao.playFromStart();
    }

    public Boolean running() {
        return animacao.getStatus().equals(Animation.Status.RUNNING);
    }

    public void stop() {
        animacao.stop();
    }

}

