/**
 * Script para desenvolvimento e teste da animacao em paralelo em JavaFX
 *
 * @author rafael
 */
package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.Comando;
import javafx.animation.*;
import jakarta.inject.Inject;
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

    public List<Transition> transicoesGerais = new ArrayList<>();

    public SequentialTransition animacao = new SequentialTransition();
    {
        animacao.getChildren().addAll(transicoesGerais);
    }

    private MainClass mainClass;



    public void acaoConclusaoTimeLine() {
        mainClass.getDesign().requestLayout();
    }

    public void inicializar(List<Comando> comandos) {
        ParallelTransition transicaoAnterior = null;
        for (int iteracao = 0; iteracao < comandos.size(); iteracao++) {
            var comando = comandos.get(iteracao);
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

                MoveTo movetoLapis = new MoveTo(x + Pencil.getInstance().getImage().getWidth() / 2 - 3, y - Pencil.getInstance().getImage().getHeight() / 2);
                LineTo linetoLapis = new LineTo(endx + Pencil.getInstance().getImage().getWidth() / 2 - 3, endy - Pencil.getInstance().getImage().getHeight() / 2);

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
                transicaoLapis.setNode(Pencil.getInstance());
                transicaoLapis.setDuration(comando.getDuracao());

                ParallelTransition transicaoParalela = new ParallelTransition();
                transicaoParalela.getChildren().addAll(transicoesDesenho, transicaoLapis);

                //Immediate insertion happens only in the first iteration.
                if (iteracao == 0) {
                    mainClass.getDesign().getChildren().add(transicoesDesenho.getNode());
                }

                if (transicaoAnterior != null) {
                    transicaoAnterior.setOnFinished(e ->
                            mainClass.getDesign().getChildren().add(transicoesDesenho.getNode())
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
                        new MoveTo(x + Pencil.getInstance().getImage().getWidth() / 2 - 3, y - Pencil.getInstance().getImage().getHeight() / 2),
                        new LineTo(endx + Pencil.getInstance().getImage().getWidth() / 2 - 3, endy - Pencil.getInstance().getImage().getHeight() / 2)
                );

                PathTransition transicaoLapis = new PathTransition();
                transicaoLapis.setPath(caminhoLapis);
                transicaoLapis.setNode(Pencil.getInstance());

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

