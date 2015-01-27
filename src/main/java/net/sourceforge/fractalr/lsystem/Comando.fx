package net.sourceforge.fractalr.lsystem;

/**
 * Comando contendo as coordenadas passadas para o lapis
 * @author rafael
 */

public class Comando {
    public var coordenadaInicial : Ponto;
    public var coordenadaFinal: Ponto;
    public var tipoComando : Integer;  
    public var duracao : Duration;
    public var ultimoAngulo : Number;
    
    public override function toString() : String{
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

public static var MOVER = 1;
public static var TRANSPORTAR = 2;
public static var GIRAR_DIREITA = 3;
public static var GIRAR_ESQUERDA = 4;

