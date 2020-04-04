package ca.renardnumerique.fractalr2.lsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for managing actions.
 * @author Rafael de Andrade Sousa
 # @since  0.9
 *
 */

public class GerenciadorLSystem {

	/**********************************************************
	* Os LSystems permitem que tenhamos, durante a geracao da gramatica de derivacao
	* paralela, mais de um tipo de representacao para o Andar ou para o Produzir.
	* As diferenciacoes serao determinadas pela cor do botao, a ser escolhida pelo
	* usuario.
	*
	***********************************************************/
	private List<AcaoExpansiva> acoesAndar = new ArrayList<>();
    private List<AcaoExpansiva> acoesExpandir = new ArrayList<>();
    private List<AcaoExpansiva> acoesFazerRetornar = new ArrayList<>();

	//Instancia do botao girar a direita
	private AcaoLSystem acaoGirarDireita = new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_DIREITA,"-",null);
    //Instancia do botao girar a direita
    private AcaoLSystem acaoGirarEsquerda = new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_ESQUERDA,"+",null);


	public AcaoLSystem obterAcao(Integer corBotao, Integer tipoAcao){
	    AcaoLSystem acaoLSystem = null;
		if (tipoAcao == AcaoLSystem.ACAO_ANDAR){
			acaoLSystem = obterAcaoAndar(corBotao);
		}
		else if (tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA) {
		    acaoLSystem = obterAcaoGirarDireita();
		}
		else if (tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA) {
			acaoLSystem = obterAcaoGirarEsquerda();
		}
		else if (tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR) {
			acaoLSystem = obterAcaoFazerRetornar(corBotao);
		}
		else if (tipoAcao == AcaoLSystem.ACAO_EXPANDIR) {
			acaoLSystem = obterAcaoExpandir(corBotao);
		}
		return acaoLSystem;
	}

	/**
	* Obtem a instancia da acao andar relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoAndar(Integer corAcao){
		for(AcaoLSystem acao : acoesAndar){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};
		AcaoLSystem tempAcao;
		AcaoExpansiva novaAcao = new AcaoExpansiva();
        novaAcao.setCor(corAcao);
        novaAcao.setTipoAcao(AcaoLSystem.ACAO_ANDAR);
		acoesAndar.add(novaAcao);
		return novaAcao;
	}

	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	private AcaoLSystem obterAcaoExpandir(Integer corAcao) {
		for(AcaoLSystem acao : acoesExpandir){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};
		AcaoLSystem novaAcao = new AcaoExpansiva();
        novaAcao.setCor(corAcao);
        novaAcao.setTipoAcao(AcaoLSystem.ACAO_EXPANDIR);
        return novaAcao;
	}

	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	private AcaoLSystem obterAcaoGirarDireita(){
		return acaoGirarDireita;
	}

	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	private AcaoLSystem obterAcaoGirarEsquerda(){
		return acaoGirarEsquerda;
	}

	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	private AcaoLSystem obterAcaoFazerRetornar(Integer corAcao){
	    for(AcaoLSystem acao : acoesFazerRetornar){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};
		return new AcaoLSystem(AcaoLSystem.ACAO_FAZER_RETORNAR,"+",corAcao);
	}

}




