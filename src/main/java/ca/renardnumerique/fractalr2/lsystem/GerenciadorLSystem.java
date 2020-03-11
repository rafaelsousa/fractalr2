//package ca.renardnumerique.fractalr2.lsystem;
//
//import net.sourceforge.fractalr.ActionButton;
//
///**
// * This class is for managing actions.
// * @author Rafael de Andrade Sousa
// # @since  0.9
// * 
// */
//
//public class GerenciadorLSystem {
//
//	/**********************************************************
//	* Os LSystems permitem que tenhamos, durante a geracao da gramatica de deriva��o
//	* paralela, mais de um tipo de representacao para o Andar ou para o Produzir.
//	* As diferenciacoes serao determinadas pela cor do botao, a ser escolhida pelo
//	* usuario.
//	*
//	***********************************************************/
//	//Array que contem as acoes andar
//	var acoesAndar : AcaoExpansiva[];
//	//Array que contem as acoes Expandir
//	var acoesExpandir : AcaoExpansiva[];
//	//Array que contem as acoes Fazer e retornar
//	var acoesFazerRetornar : AcaoExpansiva[];
//		
//	//Instancia do botao girar a direita
//	var acaoGirarDireita = AcaoLSystem{
//	    tipoAcao : AcaoLSystem.ACAO_GIRAR_DIREITA
//	    simbolo:"-"
//	};
//	//Instancia do botao girar a direita
//	var acaoGirarEsquerda = AcaoLSystem{
//		tipoAcao : AcaoLSystem.ACAO_GIRAR_ESQUERDA;
//	    simbolo:"+"
//	};
//	
//	public function obterAcao(corBotao : Integer, tipoAcao : Number) : AcaoLSystem{
//	    var acaoLSystem : AcaoLSystem;    
//		if (tipoAcao == AcaoLSystem.ACAO_ANDAR){
//			acaoLSystem = obterAcaoAndar(corBotao);
//		}
//		else if (tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA) {
//		    acaoLSystem = obterAcaoGirarDireita();
//		}
//		else if (tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA) {
//			acaoLSystem = obterAcaoGirarEsquerda();	    
//		}
//		else if (tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR) {
//			acaoLSystem = obterAcaoFazerRetornar(corBotao);	    
//		}
//		else if (tipoAcao == AcaoLSystem.ACAO_EXPANDIR) {
//			acaoLSystem = obterAcaoExpandir(corBotao);			    
//		}
//		//println("Obtida acao {acaoLSystem.tipoAcao} para o botao arrastado");
//		return acaoLSystem;
//	}
//	
//	/**
//	* Obt�m a inst�ncia da a��o andar relativa � cor escolhida pelo usu�rio.
//	*/
//	function obterAcaoAndar(corAcao : Integer) : AcaoLSystem{	    
//		for(acao in acoesAndar){
//		    if(corAcao == acao.cor){
//		        return acao;
//		    }
//		};
//		var tempAcao : AcaoLSystem;
//		def novaAcao = AcaoExpansiva {
//		    cor : corAcao;
//		    tipoAcao : tempAcao.ACAO_ANDAR;
//		}
//		insert novaAcao into acoesAndar;
//		return novaAcao;
//	}
//	
//	/**
//	* Obt�m a inst�ncia da a��o expandir relativa � cor escolhida pelo usu�rio.
//	*/
//	function obterAcaoExpandir(corAcao : Integer) : AcaoLSystem{	    
//		for(acao in acoesExpandir){
//		    if(corAcao == acao.cor){
//		        return acao;
//		    }
//		};
//		var tempAcao : AcaoLSystem;
//		def novaAcao = AcaoLSystem {
//		    cor : corAcao;
//		    tipoAcao : tempAcao.ACAO_EXPANDIR;
//		}
//	}
//	
//	/**
//	* Obt�m a inst�ncia da a��o expandir relativa � cor escolhida pelo usu�rio.
//	*/
//	function obterAcaoGirarDireita() : AcaoLSystem {
//		return acaoGirarDireita;	    
//	}
//	
//	/**
//	* Obt�m a inst�ncia da a��o expandir relativa � cor escolhida pelo usu�rio.
//	*/
//	function obterAcaoGirarEsquerda() : AcaoLSystem {
//		return acaoGirarEsquerda;   
//	}
//	
//	/**
//	* Obt�m a inst�ncia da a��o expandir relativa � cor escolhida pelo usu�rio.
//	*/
//	function obterAcaoFazerRetornar(corAcao : Integer) : AcaoLSystem {
//	    for(acao in acoesFazerRetornar){
//		    if(corAcao == acao.cor){
//		        return acao;
//		    }
//		};
//		var tempAcao : AcaoLSystem;
//		def novaAcao = AcaoLSystem {
//		    cor : corAcao;
//		    tipoAcao : tempAcao.ACAO_FAZER_RETORNAR;
//		}    
//	}
//	
//}
//
//public static var instanciaAtual = new GerenciadorLSystem();
//
//
//
