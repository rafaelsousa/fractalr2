package ca.renardnumerique.fractalr2.lsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rafael
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcaoLSystem {

    private Integer tipoAcao;
	private String simbolo;
	private Integer cor;

	public static Integer ACAO_ANDAR = 1;
	public static Integer ACAO_EXPANDIR = 2;
	public static Integer ACAO_FAZER_RETORNAR = 3;
	public static Integer ACAO_GIRAR_DIREITA = 4;
	public static Integer ACAO_GIRAR_ESQUERDA = 5;
	public static Integer ACAO_MEMORIZAR = 6;
	public static Integer ACAO_RESTAURAR = 7;
	public static Integer ACAO_IGUAL = -1;

	public AcaoLSystem(Integer tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

    @Override
	public String toString() {
	    if(tipoAcao == AcaoLSystem.ACAO_ANDAR){
	        return String.valueOf("abcd".charAt(this.cor));
	    }
	    if(tipoAcao == AcaoLSystem.ACAO_EXPANDIR){
	        return String.valueOf("ABCD".charAt(this.cor));
	   	}
	    if(tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR){
			return this.cor!=null?this.cor.toString():"";
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
		if(tipoAcao == AcaoLSystem.ACAO_IGUAL){
			return "=";
		}
   		return "Unknown action";
	}

	public Boolean equals(AcaoLSystem acao) {
	    return (this.tipoAcao == acao.tipoAcao) && (this.cor == acao.cor);
	}

}


