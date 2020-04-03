package ca.renardnumerique.fractalr2.lsystem;

/**
 * @author rafael
 */

public class AcaoLSystem {

    public Integer tipoAcao;
	public String simbolo;
	public Integer cor;

	public static Integer ACAO_ANDAR = 1;
	public static Integer ACAO_EXPANDIR = 2;
	public static Integer ACAO_FAZER_RETORNAR = 3;
	public static Integer ACAO_GIRAR_DIREITA = 4;
	public static Integer ACAO_GIRAR_ESQUERDA = 5;
	public static Integer ACAO_MEMORIZAR = 6;
	public static Integer ACAO_RESTAURAR = 7;
	@Override
	public String toString() {
	    if(tipoAcao == AcaoLSystem.ACAO_ANDAR){
	        return String.valueOf("abcd".charAt(this.cor));
	    }
	    if(tipoAcao == AcaoLSystem.ACAO_EXPANDIR){
	        return String.valueOf("ABCD".charAt(this.cor));
	   	}
	    if(tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR){
			return this.cor.toString();
	   	}
   		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA){
   		    return "+";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA){
   		    return "-";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_MEMORIZAR){
   		    return "[";
   		}
   		if(tipoAcao == AcaoLSystem.ACAO_RESTAURAR){
   		    return "]";
   		}
   		return "Unknown action";
	}

	public Boolean equals(AcaoLSystem acao) {
	    return (this.tipoAcao == acao.tipoAcao) && (this.cor == acao.cor);
	}

}


