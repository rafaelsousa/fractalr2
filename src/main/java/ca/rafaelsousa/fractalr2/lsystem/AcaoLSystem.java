package ca.rafaelsousa.fractalr2.lsystem;

/**
 * @author Rafael de Andrade Sousa - rafael.and@gmail.com
 */

public class AcaoLSystem {

	private Integer tipoAcao;
	private String simbolo;
	private Integer cor;

	public static final Integer ACAO_ANDAR = 1;

	public static final Integer ACAO_EXPANDIR = 2;

	public static final Integer ACAO_FAZER_RETORNAR = 3;

	public static final Integer ACAO_GIRAR_DIREITA = 4;

	public static final Integer ACAO_GIRAR_ESQUERDA = 5;

	public static final Integer ACAO_MEMORIZAR = 6;

	public static final Integer ACAO_RESTAURAR = 7;
	
	public AcaoLSystem(){
		
	}

	public AcaoLSystem(Integer acao, String simbolo) {
		this.tipoAcao = acao;
		this.simbolo = simbolo;
	}

	@Override
	public String toString() {
		if (this.tipoAcao == AcaoLSystem.ACAO_ANDAR) {
			return String.valueOf("abcd".charAt(this.cor));
			// return "andar {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_EXPANDIR) {
			return String.valueOf("ABCD".charAt(this.cor));
			// return "expandir {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR) {
			return this.cor.toString();
			// return "fazer e retornar {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA) {
			return "+";
			// return "girar a direita {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA) {
			return "-";
			// return "girar a esquerda {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_MEMORIZAR) {
			return "[";
			// return "memorizar {cor}";
		}
		if (tipoAcao == AcaoLSystem.ACAO_RESTAURAR) {
			return "]";
			// return "restaurar {cor}";
		}
		return "Unknown action";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((tipoAcao == null) ? 0 : tipoAcao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		AcaoLSystem other = (AcaoLSystem) obj;

		if (cor == null) {
			if (other.cor != null) {
				return false;
			}
		} else if (!cor.equals(other.cor)) {
			return false;
		}
		if (tipoAcao == null) {
			if (other.tipoAcao != null) {
				return false;
			}
		} else if (!tipoAcao.equals(other.tipoAcao)) {
			return false;
		}
		return true;
	}

	public Integer getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(Integer tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Integer getCor() {
		return cor;
	}

	public void setCor(Integer cor) {
		this.cor = cor;
	}

}
