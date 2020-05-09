package ca.renardnumerique.fractalr2.lsystem;


/**
 * @author rafael
 */

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

	public AcaoLSystem(Integer lSystemAction, String symbol, Integer cor) {
		this.tipoAcao = lSystemAction;
		this.simbolo = symbol;
		this.cor = cor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((simbolo == null) ? 0 : simbolo.hashCode());
		result = prime * result + ((tipoAcao == null) ? 0 : tipoAcao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcaoLSystem other = (AcaoLSystem) obj;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (simbolo == null) {
			if (other.simbolo != null)
				return false;
		} else if (!simbolo.equals(other.simbolo))
			return false;
		if (tipoAcao == null) {
			if (other.tipoAcao != null)
				return false;
		} else if (!tipoAcao.equals(other.tipoAcao))
			return false;
		return true;
	}

	public Integer getTipoAcao() {
		return this.tipoAcao;
	}

	public void setTipoAcao(Integer tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Integer getCor() {
		return this.cor;
	}

	public void setCor(Integer cor) {
		this.cor = cor;
	}
	

}


