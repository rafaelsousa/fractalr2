package ca.renardnumerique.fractalr2.lsystem;

import ca.renardnumerique.fractalr2.Ponto;
import javafx.util.Duration;

/**
 * Comando contendo as coordenadas passadas para o lapis
 * @author rafael
 */

public class Comando {

    public static final Integer MOVER = 1;
    public static final Integer TRANSPORTAR = 2;
    public static final Integer GIRAR_DIREITA = 3;
    public static final Integer GIRAR_ESQUERDA = 4;

    private Ponto coordenadaInicial;
    private Ponto coordenadaFinal;
    private Integer tipoComando;
    private Duration duracao;
    private Double ultimoAngulo;

    public Ponto getCoordenadaInicial() {
        return this.coordenadaInicial;
    }

    public void setCoordenadaInicial(Ponto coordenadaInicial) {
        this.coordenadaInicial = coordenadaInicial;
    }

    public Ponto getCoordenadaFinal() {
        return this.coordenadaFinal;
    }

    public void setCoordenadaFinal(Ponto coordenadaFinal) {
        this.coordenadaFinal = coordenadaFinal;
    }

    public Integer getTipoComando() {
        return this.tipoComando;
    }

    public void setTipoComando(Integer tipoComando) {
        this.tipoComando = tipoComando;
    }

    public Duration getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Double getUltimoAngulo() {
        return this.ultimoAngulo;
    }

    public void setUltimoAngulo(Double ultimoAngulo) {
        this.ultimoAngulo = ultimoAngulo;
    }

    public String toString() {
        if(tipoComando == Comando.MOVER){
            return "Mover";
        }
        else if(tipoComando == Comando.TRANSPORTAR){
        	return "Transportar";
        }
        else if(tipoComando == Comando.GIRAR_DIREITA){
           	return "Girar a direita";
        }
		else if(tipoComando == Comando.GIRAR_ESQUERDA){
           	return "Girar a esquerda";
        }
        return "Comando desconhecido";
    }


}


