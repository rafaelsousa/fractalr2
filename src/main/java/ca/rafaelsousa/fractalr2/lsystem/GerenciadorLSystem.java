package ca.rafaelsousa.fractalr2.lsystem;

import java.util.ArrayList;



/**
 * This class is for managing actions.
 * 
 * @author Rafael de Andrade Sousa
 # @since  0.9
 * 
 */

public class GerenciadorLSystem {
	
	
	/*
	 * Singleton for GerenciadorLSystem
	 */
	private static GerenciadorLSystem instanciaAtual;
	
	private GerenciadorLSystem(){}
	
	public static GerenciadorLSystem getInstance(){
		if(instanciaAtual==null){
			instanciaAtual = new GerenciadorLSystem();
		}
		return instanciaAtual;
	}

	/**********************************************************
	* Os LSystems permitem que tenhamos, durante a geracao da gramatica de derivação
	* paralela, mais de um tipo de representacao para o Andar ou para o Produzir.
	* As diferenciacoes serao determinadas pela cor do botao, a ser escolhida pelo
	* usuario.
	*
	***********************************************************/
	//Array que contem as acoes andar
	private ArrayList<AcaoExpansiva> acoesAndar = new ArrayList<>();
	//Array que contem as acoes Expandir
	private ArrayList<AcaoExpansiva> acoesExpandir = new ArrayList<>(); 
	//Array que contem as acoes Fazer e retornar
	private ArrayList<AcaoExpansiva> acoesFazerRetornar = new ArrayList<>();
		
	//Instancia do botao girar a direita
	private AcaoLSystem acaoGirarDireita = new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_DIREITA,"-");
	
	//Instancia do botao girar a direita
	private AcaoLSystem acaoGirarEsquerda = new AcaoLSystem(AcaoLSystem.ACAO_GIRAR_ESQUERDA, "+");
	
	public AcaoLSystem obterAcao(Integer corBotao,Integer tipoAcao){
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
		//println("Obtida acao {acaoLSystem.tipoAcao} para o botao arrastado");
		return acaoLSystem;
	}
	
	/**
	* Obtem a instancia da acao andar relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoAndar(Integer corAcao){	    
		for(AcaoExpansiva acao : acoesAndar){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};

		AcaoExpansiva novaAcao = new AcaoExpansiva();
		novaAcao.setCor(corAcao);
		novaAcao.setTipoAcao(AcaoLSystem.ACAO_ANDAR);	
		acoesAndar.add(novaAcao);		
		return novaAcao;
	}
	
	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoExpandir(Integer corAcao){	    
		for(AcaoExpansiva acao : acoesExpandir){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};
		
		AcaoExpansiva novaAcao = new AcaoExpansiva();
		novaAcao.setCor(corAcao);
		novaAcao.setTipoAcao(AcaoExpansiva.ACAO_EXPANDIR);
		
		return novaAcao;
		    
	}
	
	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoGirarDireita(){
		return acaoGirarDireita;	    
	}
	
	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoGirarEsquerda(){
		return acaoGirarEsquerda;   
	}
	
	/**
	* Obtem a instancia da acao expandir relativa a cor escolhida pelo usuario.
	*/
	public AcaoLSystem obterAcaoFazerRetornar(Integer corAcao) {
	    for(AcaoExpansiva acao : acoesFazerRetornar){
		    if(corAcao == acao.getCor()){
		        return acao;
		    }
		};		
		AcaoExpansiva novaAcao = new AcaoExpansiva();
		novaAcao.setCor(corAcao);
		novaAcao.setTipoAcao(AcaoLSystem.ACAO_FAZER_RETORNAR);
		
		return novaAcao;
		     
	}
	
}





