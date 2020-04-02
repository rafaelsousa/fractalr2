/**
 * Script para desenvolvimento e teste da animacao em paralelo em JavaFX
 *
 * @author rafael
 */
package ca.renardnumerique.fractalr2;

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
    public List<Timeline> transicoesGerais;

    public SequentialTransition animacao = new SequentialTransition();

    {
        animacao.getChildren().addAll(transicoesGerais);
    }


    public void acaoConclusaoTimeLine() {
        MainClass.instance.design.canvas.requestLayout();
    }

    public function inicializar(comandos :Comando[]) :Void

    {
        var pos = 0;
        var transicaoAnterior :ParallelTransition = null;
        for (iteracao in[ 0..comandos.size() - 1]){
        var comando = comandos[iteracao];
        if (comando.tipoComando == Comando.MOVER) {

            x = comando.coordenadaInicial.x;
            y = comando.coordenadaInicial.y;

            var endx :Number = comando.coordenadaFinal.x;
            var endy :Number = comando.coordenadaFinal.y;

            var tamanhoX = Math.abs(endx - endy);
            var tamanhoY = Math.abs(endx - endy);

            linha = Line {
                startX:
                x,
                        startY:y,
                        endX:endx,
                        endY:endy,
                        strokeWidth:1,
                        stroke:Color.BLACK
            }

            var caminho = Path {
                elements: [
                MoveTo {
                    x:
                    x;
                    y:
                    y;
                },
                LineTo {
                    x:
                    (x + endx) / 2;
                    y:
                    (y + endy) / 2;
                }
					]
            }
            var caminhoLapis = Path {
                elements: [
                MoveTo {
                    x:
                    x + lapis.image.width / 2 - 3;
                    y:
                    y - lapis.image.height / 2;
                },
                LineTo {
                    x:
                    endx + lapis.image.width / 2 - 3;
                    y:
                    endy - lapis.image.height / 2;
                }
	                ]
            }
            var transicoesDesenho = ParallelTransition {
                node:
                linha,
                        content : [
                FadeTransition {
                    duration:
                    comando.duracao,
                            fromValue	:0,
                            toValue		:1
                },
                ScaleTransition {
                    duration:
                    comando.duracao,
                            fromX:0
                    fromY:
                    0
                    toX:
                    1
                    toY:
                    1;
                },
                PathTransition {
                    duration:
                    comando.duracao,
                            path:AnimationPath.createFromPath(caminho)
                }
		                ];
                action:
                acaoConclusaoTimeLine;
            }

            var transicaoLapis = PathTransition {
                path:
                AnimationPath.createFromPath(caminhoLapis);
                node:
                lapis;
                duration:
                comando.duracao
            }

            var transicaoParalela = ParallelTransition {
                content: [transicoesDesenho, transicaoLapis]
            }

            //S� insere imediatamente na primeira itera��o
            if (iteracao == 0) {
                insert transicoesDesenho.node into MainClass.instance.design.canvas.content;
            }

            if (transicaoAnterior != null) {
                transicaoAnterior.action = function() {
                    insert transicoesDesenho.node into MainClass.instance.design.canvas.content;
                }
            }

            insert transicaoParalela into transicoesGerais;

            transicaoAnterior = transicaoParalela;
        }
        if (comando.tipoComando == Comando.TRANSPORTAR) {

            x = comando.coordenadaInicial.x;
            y = comando.coordenadaInicial.y;

            var endx :Number = comando.coordenadaFinal.x;
            var endy :Number = comando.coordenadaFinal.y;

            var tamanhoX = Math.abs(endx - endy);
            var tamanhoY = Math.abs(endx - endy);


            var caminho = Path {
                elements: [
                MoveTo {
                    x:
                    x;
                    y:
                    y;
                },
                LineTo {
                    x:
                    (x + endx) / 2;
                    y:
                    (y + endy) / 2;
                }
					]
            }
            var caminhoLapis = Path {
                elements: [
                MoveTo {
                    x:
                    x + lapis.image.width / 2 - 3;
                    y:
                    y - lapis.image.height / 2;
                },
                LineTo {
                    x:
                    endx + lapis.image.width / 2 - 3;
                    y:
                    endy - lapis.image.height / 2;
                }
	                ]
            }

            var transicaoLapis = PathTransition {
                path:
                AnimationPath.createFromPath(caminhoLapis);
                node:
                lapis;
                duration:
                0 s;
            }


            insert transicaoLapis into transicoesGerais;

        }
    }
    }

    function inserirLinhaProximoComando(comando :Comando) {
        x = comando.coordenadaInicial.x;
        y = comando.coordenadaInicial.y;

        var endx :Number = comando.coordenadaFinal.x;
        var endy :Number = comando.coordenadaFinal.y;

        var tamanhoX = Math.abs(endx - endy);
        var tamanhoY = Math.abs(endx - endy);

        linha = Line {
            startX:
            x,
                    startY:y,
                    endX:endx,
                    endY:endy,
                    strokeWidth:1,
                    stroke:Color.BLACK
        }
        insert linha into MainClass.instance.design.canvas.content;
    }

    public function play() :Void

    {
        animacao.play();
    }

    public function pause() :Void

    {
        if (animacao.running) {
            animacao.pause();
        } else {
            animacao.play();
        }
    }

    public function playFromStart() :Void

    {
        animacao.playFromStart();
    }

    public function running() :Boolean

    {
        return animacao.running;
    }

    public function stop() :Void

    {
        animacao.stop();
    }


}

