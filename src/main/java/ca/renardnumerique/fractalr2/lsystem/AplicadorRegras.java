package ca.renardnumerique.fractalr2.lsystem;

import ca.renardnumerique.fractalr2.*;
import javafx.scene.Node;
import javafx.util.Duration;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Aplica as regras que geram os comandos para o lapis criar o desenho do
 * fractal
 *
 * @author Rafael de Andrade Sousa : rafael.and@gmail.com
 */

@Data
public class AplicadorRegras {

    public static AplicadorRegras instance = new AplicadorRegras();

    private AplicadorRegras(){

    }

    private Integer iteracao;
    private List<ActionButton> botoesFormula;

    public List<Comando> pilhaComandos;
    private List<AcaoLSystem> pilhaAcoes;

    public ArrayList<Comando> gerarComandos() {
        pilhaComandos = new ArrayList<Comando>();
        pilhaAcoes = new ArrayList<AcaoLSystem>();
        ArrayList<Comando> cmds = new ArrayList<Comando>();
        botoesFormula = FormulaPanel.getInstance().getBotoes();

        var tamanhoSegmento = 1;// Tamanho das linhas

        //Atribuir transformacoes aos botoes no painel de formulas
        List<TransformationPanel> pnlTransformacoes = new ArrayList<>();

        for (Node pnl : MainClass.getInstance().getTransformacoes().getChildren()) {
            if (pnl instanceof TransformationPanel) {
                pnlTransformacoes.add((TransformationPanel) pnl);
            }
        }

        iteracao = MainClass.getInstance().getPnlControle().getIteracoes();
        //Fetching coordinates for scaling calculation.
        Double alturaCanvas = MainClass.getInstance().getDesign().getAreaDesenho().getHeight();
        Double larguraCanvas = MainClass.getInstance().getDesign().getAreaDesenho().getWidth();

        Double maximoXFractal = java.lang.Double.NEGATIVE_INFINITY;
        Double maximoYFractal = java.lang.Double.NEGATIVE_INFINITY;
        Double minimoXFractal = java.lang.Double.POSITIVE_INFINITY;
        Double minimoYFractal = java.lang.Double.POSITIVE_INFINITY;

        Integer count = 0;
        Double angulo = Math.toRadians(MainClass.getInstance().getPnlControle().getAnguloSlider().getValue());
        Double anguloIncremento = 0.0;
        cmds.clear();


        Comando primeiroComando = new Comando();
        primeiroComando.setCoordenadaFinal(new Ponto(0.0, 0.0));
        primeiroComando.setTipoComando(Comando.TRANSPORTAR);

        //insert primeiroComando into cmds;
        Ponto ultimaCoordenada = primeiroComando.getCoordenadaInicial();
        for (int i = 1; i <= iteracao; i++) {
            List<ActionButton> botoesExpandidos = new ArrayList<>();
            for (ActionButton botao : botoesFormula) {
                Boolean encontrou = Boolean.FALSE;
                for (TransformationPanel transformacao : pnlTransformacoes) {
                    List<ActionButton> botoes = transformacao.getBotoes();
                    if (botoes.get(0).getAcaoLSystem().equals(botao.getAcaoLSystem())) {
                        encontrou = true;
                        for (int j=2; j < botoes.size(); j++) {
                            ActionButton b = botoes.get(j);
                            if (b.getAcaoLSystem().getTipoAcao().equals(AcaoLSystem.ACAO_FAZER_RETORNAR)) {
                                List<ActionButton> expansaoFazerRetornar = expandeFazerRetornar(botoes.get(0), pnlTransformacoes);
                                if (expansaoFazerRetornar.size() > 0){
                                    botoesExpandidos.addAll(expansaoFazerRetornar);;
                                }
                            }
                            botoesExpandidos.add(b);
                        }
                    }
                }
                if (!encontrou){
                    botoesExpandidos.add(botao);
                }
            }
            botoesFormula = botoesExpandidos;
        }

        Duration duracao = Duration.millis(1000);

        if (iteracao > 0) {
            duracao = Duration.millis(1000 / iteracao * 1.5);
        }

        for (ActionButton botao : botoesFormula) {
            Integer tipoAcao = botao.getAcaoLSystem().getTipoAcao();
            if (tipoAcao.equals(AcaoLSystem.ACAO_ANDAR)) {
                Ponto novaCoordenada = new Ponto(ultimaCoordenada.getX() + tamanhoSegmento * Math.cos(anguloIncremento),
                                                ultimaCoordenada.getY() + tamanhoSegmento * Math.sin(anguloIncremento));
                Comando novoComando = new Comando();
                novoComando.setCoordenadaInicial(new Ponto(ultimaCoordenada.getX(),ultimaCoordenada.getY()));
                novoComando.setCoordenadaFinal(novaCoordenada);
                novoComando.setTipoComando(Comando.MOVER);
                novoComando.setDuracao(duracao);
                cmds.add(novoComando);


                //println("Coordenada: {novaCoordenada}");
                //atualizando coordenadas minimas;
                if (novoComando.getCoordenadaInicial().getX() > maximoXFractal) {
                    maximoXFractal = novoComando.getCoordenadaInicial().getX();
                }
                if (novoComando.getCoordenadaInicial().getX() < minimoXFractal) {
                    minimoXFractal = novoComando.getCoordenadaInicial().getX();
                }
                if (novoComando.getCoordenadaInicial().getY() > maximoYFractal) {
                    maximoYFractal = novoComando.getCoordenadaInicial().getY();
                }
                if (novoComando.getCoordenadaInicial().getY() < minimoYFractal) {
                    minimoYFractal = novoComando.getCoordenadaInicial().getY();
                }

                if (novoComando.getCoordenadaFinal().getX() > maximoXFractal) {
                    maximoXFractal = novoComando.getCoordenadaFinal().getX();
                }
                if (novoComando.getCoordenadaFinal().getX() < minimoXFractal) {
                    minimoXFractal = novoComando.getCoordenadaFinal().getX();
                }
                if (novoComando.getCoordenadaFinal().getY() > maximoYFractal) {
                    maximoYFractal = novoComando.getCoordenadaFinal().getY();
                }
                if (novoComando.getCoordenadaFinal().getY() < minimoYFractal) {
                    minimoYFractal = novoComando.getCoordenadaFinal().getY();
                }
                ultimaCoordenada = novaCoordenada;
            }
            if (tipoAcao == AcaoLSystem.ACAO_MEMORIZAR) {
                Comando comando = new Comando();
                {
                    comando.setCoordenadaFinal(ultimaCoordenada);
                    comando.setTipoComando(Comando.TRANSPORTAR);
                    comando.setDuracao(duracao);
                    comando.setUltimoAngulo(anguloIncremento);
                }
                empilhaComando(botao.getAcaoLSystem(), comando);
            }
            if (tipoAcao == AcaoLSystem.ACAO_RESTAURAR) {
                //println("desempilhando:{ultimaCoordenada}");
                var comando = desempilhaComando(botao.getAcaoLSystem());
                comando.setCoordenadaFinal(ultimaCoordenada);
                anguloIncremento = comando.getUltimoAngulo();
                ultimaCoordenada = comando.getCoordenadaFinal();
                cmds.add(comando);
            }
            if (tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA) {
                anguloIncremento += angulo;
                //println("Novo angulo: {anguloIncremento}");
            }
            if (tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA) {
                anguloIncremento -= angulo;
                //println("Novo angulo: {anguloIncremento}");
            }
        }
    /*-------------------------------------
    		Ajustar as proporcoes
    --------------------------------------*/
        Double larguraFractal = maximoXFractal - minimoXFractal;
        Double alturaFractal = maximoYFractal - minimoYFractal;

        Double escalaX = ((larguraCanvas - larguraFractal) / larguraFractal);
        Double escalaY = ((alturaCanvas - alturaFractal) / alturaFractal);

        Double x1Canvas = MainClass.getInstance().getDesign().getAreaDesenho().getX();
        Double y1Canvas = MainClass.getInstance().getDesign().getAreaDesenho().getY();

        Double x2Canvas = x1Canvas + MainClass.getInstance().getDesign().getAreaDesenho().getWidth();
        Double y2Canvas = y1Canvas + MainClass.getInstance().getDesign().getAreaDesenho().getHeight();

        Double escala;

        if (alturaFractal == 0) {
            escala = larguraCanvas / larguraFractal;
        } else if (larguraFractal == 0) {
            escala = alturaCanvas / alturaFractal;
        } else {
            if (larguraFractal < alturaFractal) {
                escala = alturaCanvas / alturaFractal;
            } else {
                escala = larguraCanvas / larguraFractal;
            }
        }

        Ponto firstCorner = new Ponto(0.0,0.0);

        Double left = Double.POSITIVE_INFINITY;
        Double right = Double.NEGATIVE_INFINITY;
        Double top  = Double.POSITIVE_INFINITY;
        Double bottom  = Double.NEGATIVE_INFINITY;

        //REDIMENSIONA
        for (Comando comando : cmds) {
            if (comando.getTipoComando().equals(Comando.MOVER)) {

                Double x1Frac = Double.valueOf(Math.round(comando.getCoordenadaInicial().getX() * escala));
                Double x2Frac = Double.valueOf(Math.round(comando.getCoordenadaFinal().getX() * escala));
                Double y1Frac = Double.valueOf(Math.round(comando.getCoordenadaInicial().getY() * escala));
                Double y2Frac = Double.valueOf(Math.round(comando.getCoordenadaFinal().getY() * escala));

                if (x1Frac < left) {
                    left = x1Frac;
                }
                if (x1Frac > right) {
                    right = x1Frac;
                }
                if (x2Frac < left) {
                    left = x2Frac;
                }
                if (x2Frac > right) {
                    right = x2Frac;
                }
                if (y1Frac < top) {
                    top = y1Frac;
                }
                if (y1Frac > bottom) {
                    bottom = y1Frac;
                }
                if (y2Frac < top) {
                    top = y2Frac;
                }
                if (y2Frac > bottom) {
                    bottom = y2Frac;
                }
                //println("({x1Frac},{y1Frac}) - ({x2Frac},{y2Frac})");

                comando.getCoordenadaInicial().setX(Double.valueOf(x1Frac));
                comando.getCoordenadaInicial().setY(Double.valueOf(y1Frac));
                comando.getCoordenadaFinal().setX(Double.valueOf(x2Frac));
                comando.getCoordenadaFinal().setY(Double.valueOf(y2Frac));
            }
        }

        Double deslocamentoHorizontal = left - x1Canvas;
        Double deslocamentoVertical = top - y1Canvas;

        right += deslocamentoHorizontal;
        bottom += deslocamentoVertical;

        var centralizacaoHorizontal = deslocamentoHorizontal / 2;
        var centralizacaoVertical = deslocamentoVertical / 2;

        for (Comando comando : cmds) {
            if (comando.getTipoComando().equals(Comando.MOVER)) {
                comando.getCoordenadaInicial().setX(comando.getCoordenadaInicial().getX() - deslocamentoHorizontal);
                comando.getCoordenadaInicial().setY(comando.getCoordenadaInicial().getY() - deslocamentoVertical);
                comando.getCoordenadaFinal().setY(comando.getCoordenadaFinal().getY() - deslocamentoVertical);
                comando.getCoordenadaFinal().setX(comando.getCoordenadaFinal().getX() - deslocamentoHorizontal);
                comando.getCoordenadaFinal().setY(comando.getCoordenadaFinal().getY() - deslocamentoVertical);
            }
        }
        return cmds;
    }

    public static List<ActionButton> expandeFazerRetornar(ActionButton button, List<TransformationPanel> pnlTransformacoes) {
        List<ActionButton> botoesExpandidos = new ArrayList<>();
        ActionButton botaoMemoria = new ActionButton();
        botaoMemoria.setNome("Memorizar");
        botaoMemoria.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_MEMORIZAR));
        botoesExpandidos.add(botaoMemoria);
        for (TransformationPanel transformacao : pnlTransformacoes) {
            var botoes = transformacao.getBotoes();
            if (botoes.get(0).getAcaoLSystem().equals(button.getAcaoLSystem())) {
                for (int j = 2;j<botoes.size();j++){
                    ActionButton b = botoes.get(j);
                    if (b.getAcaoLSystem().getTipoAcao().equals(AcaoLSystem.ACAO_FAZER_RETORNAR)) {
                        botoesExpandidos.addAll(expandeFazerRetornar(b, pnlTransformacoes));
                    } else {
                        botoesExpandidos.add(b);
                    }
                }
            }
        }
        ActionButton botaoRestaurar = new ActionButton();
        botaoRestaurar.setNome("Restaurar");
        botaoRestaurar.setAcaoLSystem(new AcaoLSystem(AcaoLSystem.ACAO_RESTAURAR));
        botoesExpandidos.add(botaoRestaurar);
        return botoesExpandidos;
    }

    public void empilhaComando(AcaoLSystem acaoLSystem, Comando comando){
        pilhaComandos.add(comando);
    }

    public Comando desempilhaComando(AcaoLSystem acaoLSystem){
        Integer tamPilha = pilhaComandos.size();
        if (tamPilha >= 0) {
            Comando c  = pilhaComandos.get(tamPilha - 1);
            pilhaComandos.remove(c);
            pilhaAcoes.remove(acaoLSystem);
            return c;
        }
        return null;
    }

}
