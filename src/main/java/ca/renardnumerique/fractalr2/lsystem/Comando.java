package ca.renardnumerique.fractalr2.lsystem;

import ca.renardnumerique.fractalr2.Ponto;
import javafx.util.Duration;
import lombok.Data;

/**
 * Comando contendo as coordenadas passadas para o lapis
 * @author rafael
 */

@Data
public class Comando {

    public static final Integer MOVER = 1;
    public static final Integer TRANSPORTAR = 2;
    public static final Integer GIRAR_DIREITA = 3;
    public static final Integer GIRAR_ESQUERDA = 4;

    private Ponto coordenadaInicial;
    private Ponto coordenadaFinal;
    private Integer tipoComando;
    private Duration duracao;
    private Integer ultimoAngulo;

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


