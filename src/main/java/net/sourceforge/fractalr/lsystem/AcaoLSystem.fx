package net.sourceforge.fractalr.lsystem;

/**
 * @author rafael
 */

public class AcaoLSystem {

	public var tipoAcao : Integer;
	public var simbolo		: String;
	public var cor		: Integer;
	
	public override function toString() : String{
	    if(tipoAcao == AcaoLSystem.ACAO_ANDAR){
	        return "abcd".charAt(this.cor).toString();
	        //return "andar {cor}";
	    }
	    if(tipoAcao == AcaoLSystem.ACAO_EXPANDIR){
	        return "ABCD".charAt(this.cor).toString();
	    	//return "expandir {cor}";
	   	}
	    if(tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR){
			return this.cor.toString();
	   		//return "fazer e retornar {cor}";
	   	}
   		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA){
   		    return "+";
   			//return "girar a direita {cor}";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA){
   		    return "-";
   		   	//return "girar a esquerda {cor}";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_MEMORIZAR){
   		    return "[";
   		  	//return "memorizar {cor}";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_RESTAURAR){
   		    return "]";
			//return "restaurar {cor}";
   		}
   		return "Unknown action";
	}
	
	public  function equals(acao : AcaoLSystem) : Boolean{
	    return (this.tipoAcao == acao.tipoAcao) and (this.cor == acao.cor)
	}
}

public static var ACAO_ANDAR : Integer = 1;
		
public static var ACAO_EXPANDIR : Integer = 2;
		
public static var ACAO_FAZER_RETORNAR : Integer = 3;
		
public static var ACAO_GIRAR_DIREITA : Integer = 4;
		
public static var ACAO_GIRAR_ESQUERDA : Integer = 5;

public static var ACAO_MEMORIZAR : Integer = 6;

public static var ACAO_RESTAURAR : Integer = 7;
