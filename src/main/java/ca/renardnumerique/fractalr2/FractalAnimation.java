/**
 * Script para desenvolvimento e teste da animacao em paralelo em JavaFX
 *
 * @author rafael
 */
package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.Comando;
import com.sun.tools.javac.Main;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.List;


public class FractalAnimation {
    private Double x;
    private Double y;

    private Line linha;
    public List<Transition> transicoesGerais;

    public SequentialTransition animacao = new SequentialTransition();

    {
        animacao.getChildren().addAll(transicoesGerais);
    }


    public void acaoConclusaoTimeLine() {
        MainClass.getInstance().getDesign().requestLayout();
    }

    public void inicializar(List<Comando> comandos) {
        Integer pos = 0;
        ParallelTransition transicaoAnterior = null;
        for (int iteracao = 0; iteracao < comandos.size(); iteracao++) {
            var comando = comandos.get(iteracao);
            if (comando.getTipoComando() == Comando.MOVER) {
                x = comando.getCoordenadaInicial().getX();
                y = comando.getCoordenadaInicial().getY();

                Double endx = comando.getCoordenadaFinal().getX();
                Double endy = comando.getCoordenadaFinal().getY();

                Double tamanhoX = Math.abs(endx - endy);
                Double tamanhoY = Math.abs(endx - endy);

                Line linha = new Line(x, y, endx, endy);
                linha.setStroke(Color.BLACK);
                linha.setStrokeWidth(1);


                MoveTo moveTo = new MoveTo(x, y);
                LineTo lineTo = new LineTo((x + endx) / 2, (y + endy) / 2);
                Path caminho = new Path();
                caminho.getElements().addAll(moveTo, lineTo);

                MoveTo movetoLapis = new MoveTo(x + Pencil.instance.image.getWidth() / 2 - 3, y - Pencil.instance.image.getHeight() / 2);
                LineTo linetoLapis = new LineTo(endx + Pencil.instance.image.getWidth() / 2 - 3, endy - Pencil.instance.image.getHeight() / 2);

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
                transicaoLapis.setNode(Pencil.instance);
                transicaoLapis.setDuration(comando.getDuracao());

                ParallelTransition transicaoParalela = new ParallelTransition();
                transicaoParalela.getChildren().addAll(transicoesDesenho, transicaoLapis);

                //Immediate insertion happens only in the first iteration.
                if (iteracao == 0) {
                    MainClass.getInstance().getDesign().getChildren().add(transicoesDesenho.getNode());
                }

                if (transicaoAnterior != null) {
                    transicaoAnterior.setOnFinished(e ->
                            MainClass.getInstance().getDesign().getChildren().add(transicoesDesenho.getNode())
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

                var tamanhoX = Math.abs(endx - endy);
                var tamanhoY = Math.abs(endx - endy);

                MoveTo moveTo = new MoveTo(x, y);
                LineTo lineTo = new LineTo((x + endx) / 2, (y + endy) / 2);
                Path caminho = new Path();
                caminho.getElements().addAll(moveTo, lineTo);

                Path caminhoLapis = new Path();
                caminhoLapis.getElements().addAll(
                        new MoveTo(x + Pencil.instance.image.getWidth() / 2 - 3, y - Pencil.instance.image.getHeight() / 2),
                        new LineTo(endx + Pencil.instance.image.getWidth() / 2 - 3, endy - Pencil.instance.image.getHeight() / 2)
                );

                PathTransition transicaoLapis = new PathTransition();
                transicaoLapis.setPath(caminhoLapis);
                transicaoLapis.setNode(Pencil.instance);

                transicoesGerais.add(transicaoLapis);

            }
        }
    }

    private void inserirLinhaProximoComando(Comando comando) {
        x = comando.getCoordenadaInicial().getX();
        y = comando.getCoordenadaInicial().getY();

        Double endx = comando.getCoordenadaFinal().getX();
        Double endy = comando.getCoordenadaFinal().getY();

        Double tamanhoX = Math.abs(endx - endy);
        Double tamanhoY = Math.abs(endx - endy);

        Line linha = new Line(x, y, endx, endy);
        linha.setStrokeWidth(1);
        linha.setStroke(Color.BLACK);
        MainClass.getInstance().getDesign().getChildren().add(linha);
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

