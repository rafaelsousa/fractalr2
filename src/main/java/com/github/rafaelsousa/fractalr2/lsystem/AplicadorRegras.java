package com.github.rafaelsousa.fractalr2.lsystem;

import net.sourceforge.fractalr.FormulaPanel;
import net.sourceforge.fractalr.ActionButton;
import net.sourceforge.fractalr.MainClass;
import net.sourceforge.fractalr.TransformationPanel;

import java.lang.Math;
import java.lang.System;


/**
 * Aplica as regras que geram os comandos para o 
 * lapis criar o desenho do fractal
 * @author rafael
 */
 
 


public class AplicadorRegras {
}

public static var iteracao : Integer;
public static var botoesFormula:ActionButton[];

static var pilhaComandos : Comando[];
static var pilhaAcoes	 : AcaoLSystem[];

public static function gerarComandos() {
    pilhaComandos = [];
    pilhaAcoes = [];
    var cmds : Comando[] ;  //so that jfx let it be changed in other function
    botoesFormula = FormulaPanel.instanciaAtual.botoes;
    
    var tamanhoSegmento = 1;// Tamanho das linhas
        
    //Atribuir transformacoes aos botoes no painel de formulas
    var pnlTransformacoes : TransformationPanel[];
    for(pnl in MainClass.instanciaAtual.transformacoes){
            if(pnl instanceof TransformationPanel){
                insert pnl as TransformationPanel into pnlTransformacoes;
            }
    }
    
    iteracao = MainClass.instanciaAtual.pnlControle.iteracoes;
    //Fetching coordinates for scaling calculation.
    var alturaCanvas  : Number = MainClass.instanciaAtual.design.areaDesenho.height;
    var larguraCanvas : Number = MainClass.instanciaAtual.design.areaDesenho.width;
    
    var maximoXFractal : Number = java.lang.Float.NEGATIVE_INFINITY;
    var maximoYFractal : Number = java.lang.Float.NEGATIVE_INFINITY;
    var minimoXFractal : Number = java.lang.Float.POSITIVE_INFINITY;
    var minimoYFractal : Number = java.lang.Float.POSITIVE_INFINITY;
    
    var count : Integer = 0; 
    var angulo : Number = Math.toRadians(MainClass.instanciaAtual.pnlControle.anguloSlider.value);
    var anguloIncremento : Number = 0; 
	delete  cmds; 
	var primeiroComando = Comando{
	    coordenadaInicial : Ponto{
	        x:0,
	        y:0
	    }
	    tipoComando : Comando.TRANSPORTAR
	};
	//insert primeiroComando into cmds;
	var ultimaCoordenada : Ponto = primeiroComando.coordenadaInicial;
	//println("Coordenada inicial{ultimaCoordenada.toString()}");
	//println("Percorrendo o array de botoes do painel de formulas e expandindo as substituicoes");
	//println("iteracao numero {iteracao}");
    for(i in [1..iteracao]){
        var botoesExpandidos : ActionButton[];
		for(botao in botoesFormula){
		    var encontrou : Boolean = false;
			//println("Verificando se o bot�o {botao.acaoLSystem.toString()} tem expans�o em {sizeof pnlTransformacoes} transforma��es");
			for(transformacao in pnlTransformacoes){
			    var botoes = transformacao.botoes;
			    //println("Verificando {botoes[0].acaoLSystem}");
				//println("Verificando {botoes[0].acaoLSystem} da transforma��o com {botao.acaoLSystem} da f�rmula");
			    if(botoes[0].acaoLSystem.equals(botao.acaoLSystem)){
			        encontrou = true;
		            for(j in [2..((sizeof botoes)-1)]){
		            	var b  = botoes[j];
						if(b.acaoLSystem.tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR){
						    var expansaoFazerRetornar = expandeFazerRetornar(botoes[0],pnlTransformacoes);
						    if(sizeof expansaoFazerRetornar > 0){
								insert expansaoFazerRetornar into botoesExpandidos;
						    }
						}
		            	insert b into botoesExpandidos;
		       		}
			    }
			}
			if(not encontrou){
			    insert botao into botoesExpandidos;
			}
	    } 
	    botoesFormula = botoesExpandidos;
	}
	//println("Resultado ap�s expans�es");
	//for(bot in botoesFormula){
	//    println("{bot.acaoLSystem} ");
	//}
	var duracao : Duration = Duration.valueOf(1000);
	if(iteracao > 0){
		duracao = Duration.valueOf(1000 / iteracao*1.5);
	}
	//println("Numero transforma��es:{numeroTransformacoes}");
	//println("Gerando comandos para {botoesFormula.size()} botoes");
    for(botao in botoesFormula){ 
	    var tipoAcao = botao.acaoLSystem.tipoAcao;
		if(tipoAcao == AcaoLSystem.ACAO_ANDAR){
		    var novaCoordenada = Ponto{
		        x: ultimaCoordenada.x + tamanhoSegmento * Math.cos(anguloIncremento),
		        y: ultimaCoordenada.y + tamanhoSegmento * Math.sin(anguloIncremento)
		    }
		    var novoComando = Comando {
		        coordenadaInicial : Ponto{
		        	x : ultimaCoordenada.x,
		        	y : ultimaCoordenada.y
		        },
		        coordenadaFinal : novaCoordenada,
		        tipoComando : Comando.MOVER,
		        duracao : duracao;
		    }
		    insert novoComando  into cmds;
		    //println("Coordenada: {novaCoordenada}");
		    //atualizando coordenadas m�nimas;
		    if(novoComando.coordenadaInicial.x > maximoXFractal){
		        maximoXFractal = novoComando.coordenadaInicial.x;
		    }
		    if(novoComando.coordenadaInicial.x < minimoXFractal){
		        minimoXFractal = novoComando.coordenadaInicial.x;
		    }
		    if(novoComando.coordenadaInicial.y > maximoYFractal){
		        maximoYFractal = novoComando.coordenadaInicial.y;
		    }
		    if(novoComando.coordenadaInicial.y < minimoYFractal){
		        minimoYFractal = novoComando.coordenadaInicial.y;
		    }
		    
		    if(novoComando.coordenadaFinal.x > maximoXFractal){
		    	maximoXFractal = novoComando.coordenadaFinal.x;
		    }
		    if(novoComando.coordenadaFinal.x < minimoXFractal){
		    	minimoXFractal = novoComando.coordenadaFinal.x;
		    }
		    if(novoComando.coordenadaFinal.y > maximoYFractal){
		        maximoYFractal = novoComando.coordenadaFinal.y;
		    }
		    if(novoComando.coordenadaFinal.y < minimoYFractal){
		        minimoYFractal = novoComando.coordenadaFinal.y;
		    }		    
		    //println("maximoXFractal{maximoXFractal}");
		    //println("minimoXFractal{minimoXFractal}");
		    //println("maximoYFractal{maximoYFractal}");
			//println("minimoYFractal{minimoYFractal}");
		    ultimaCoordenada = novaCoordenada;
		}
		//print("{botao.acaoLSystem}");
		if(tipoAcao == AcaoLSystem.ACAO_MEMORIZAR){
		    //println("empilhando:{ultimaCoordenada}");
	        var comando = Comando {	            
		        coordenadaFinal : ultimaCoordenada;
		        tipoComando : Comando.TRANSPORTAR,
		        duracao : duracao,
		        ultimoAngulo : anguloIncremento;
		    }
		    empilhaComando(botao.acaoLSystem,comando);
		}
		if(tipoAcao == AcaoLSystem.ACAO_RESTAURAR){
		    //println("desempilhando:{ultimaCoordenada}");
		    var comando = desempilhaComando(botao.acaoLSystem);
		    comando.coordenadaInicial = ultimaCoordenada;
		    anguloIncremento = comando.ultimoAngulo;
		    ultimaCoordenada = comando.coordenadaFinal;
		    insert comando into cmds;
		}
		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_DIREITA){
		    anguloIncremento += angulo;	    
		    //println("Novo angulo: {anguloIncremento}");
		}
		if(tipoAcao == AcaoLSystem.ACAO_GIRAR_ESQUERDA){
		    anguloIncremento -= angulo;
		    //println("Novo angulo: {anguloIncremento}");		    
		}
    }  
    /*-------------------------------------
    		Ajustar as propor��es
    --------------------------------------*/
	var larguraFractal : Number = maximoXFractal - minimoXFractal;
	var alturaFractal : Number = maximoYFractal - minimoYFractal;
	
	//println("larguraFractal:{larguraFractal}");
	//println("alturaFractal:{alturaFractal}");
	
	var escalaX = ((larguraCanvas - larguraFractal)/larguraFractal);
	//println("escalaX{escalaX}");
	var escalaY = ((alturaCanvas - alturaFractal)/alturaFractal);
	//println("escalaY{escalaY}");
	
	
	var x1Canvas : Number = MainClass.instanciaAtual.design.areaDesenho.x;
	var y1Canvas : Number = MainClass.instanciaAtual.design.areaDesenho.y;
	
	//println("x1Canvas{x1Canvas}");
	//println("y1Canvas{y1Canvas}");
	
	var x2Canvas : Number = x1Canvas + MainClass.instanciaAtual.design.areaDesenho.width;
	var y2Canvas : Number = y1Canvas + MainClass.instanciaAtual.design.areaDesenho.height;
	
	//println("x2Canvas{x2Canvas}");
	//println("y2Canvas{y2Canvas}");
	
	var escala : Number;
    
    if(alturaFractal == 0) {
        escala = larguraCanvas/larguraFractal;
    } else if(larguraFractal == 0) {
        escala = alturaCanvas/alturaFractal;
    } else {
        if(larguraFractal < alturaFractal) {
            escala = alturaCanvas/alturaFractal;
        } else {
            escala = larguraCanvas/larguraFractal;
        }
    }
    
    
    //println("Escala:{escala}");
	
	var firstCorner : Ponto = null;
	
	var left : Number = Number.POSITIVE_INFINITY;
	var right : Number = Number.NEGATIVE_INFINITY;
	var top  : Number = Number.POSITIVE_INFINITY;
	var bottom : Number = Number.NEGATIVE_INFINITY;
	
		
	//REDIMENSIONA
	for(comando in cmds){
	    if(comando.tipoComando == Comando.MOVER){
	        
	        var x1Frac : Number = Math.round(comando.coordenadaInicial.x * escala);
	        var x2Frac : Number = Math.round(comando.coordenadaFinal.x * escala);     
	        var y1Frac : Number = Math.round(comando.coordenadaInicial.y * escala);
	        var y2Frac : Number = Math.round(comando.coordenadaFinal.y * escala);
	        
	        if(x1Frac < left){
	            left = x1Frac;
	        }
	        if(x1Frac > right){
	            right = x1Frac;
	        }
	        if(x2Frac < left){
	        	left = x2Frac;
			}
			if(x2Frac > right){
			   	right = x2Frac;
			}
	        if(y1Frac < top){
	            top = y1Frac;
	        }
	        if(y1Frac > bottom){
	        	bottom = y1Frac;
			}
	        if(y2Frac < top){
	        	top = y2Frac;
			}
			if(y2Frac > bottom){
				bottom = y2Frac;
			}			
	        //println("({x1Frac},{y1Frac}) - ({x2Frac},{y2Frac})");
		    
		    comando.coordenadaInicial.x = x1Frac;
		    comando.coordenadaInicial.y = y1Frac;
	        comando.coordenadaFinal.x = x2Frac;
	       	comando.coordenadaFinal.y = y2Frac;
		   	/*println("comando.coordenadaInicial.x{comando.coordenadaInicial.x}");
		   	println("comando.coordenadaInicial.y{comando.coordenadaInicial.y}");
		   	println("comando.coordenadaFinal.x{comando.coordenadaFinal.x}");
		   	println("comando.coordenadaFinal.y{comando.coordenadaFinal.y}");*/
	    }
	}		
	//DESLOCA E CENTRALIZA
		
	var deslocamentoHorizontal : Number = left - x1Canvas ;
	var deslocamentoVertical   : Number = top - y1Canvas;
	
	right += deslocamentoHorizontal;
	bottom += deslocamentoVertical;
	
	var centralizacaoHorizontal = deslocamentoHorizontal/2;
	var centralizacaoVertical = deslocamentoVertical/2;
		
	//println("deslocamentoHorizontal{deslocamentoHorizontal}");
	//println("deslocamentoVertical{deslocamentoVertical}");
	for(comando in cmds){
	    if(comando.tipoComando == Comando.MOVER){
		    comando.coordenadaInicial.x -= deslocamentoHorizontal;
		    comando.coordenadaInicial.y -= deslocamentoVertical;
	        comando.coordenadaFinal.x -= deslocamentoHorizontal;
	       	comando.coordenadaFinal.y -= deslocamentoVertical;
	    }
	}
	System.gc();
	println("");
    return cmds;
}

public static function expandeFazerRetornar(botao: ActionButton,pnlTransformacoes:TransformationPanel[]) : ActionButton[]{
    //println("Entrando recursao fazer retornar");
    var botoesExpandidos : ActionButton [];
	var botaoMemoria = ActionButton {
        nome:"Memorizar"
		acaoLSystem : AcaoLSystem{
		    tipoAcao : AcaoLSystem.ACAO_MEMORIZAR;
		}
    }
    insert botaoMemoria into botoesExpandidos;
    for(transformacao in pnlTransformacoes){
        var botoes = transformacao.botoes;
        if(botoes[0].acaoLSystem.equals(botao.acaoLSystem)){
		    for(j in [2..((sizeof botoes)-1)]){
		        var b = botoes[j];
		        if(b.acaoLSystem.tipoAcao == AcaoLSystem.ACAO_FAZER_RETORNAR){
		            insert expandeFazerRetornar(b,pnlTransformacoes) into botoesExpandidos;
		        }else{
			        insert b into botoesExpandidos;
		        }
		    }
        }
    }
    var botaoRestaurar = ActionButton {
        nome:"Restaurar"
		acaoLSystem : AcaoLSystem{
		    tipoAcao : AcaoLSystem.ACAO_RESTAURAR;
		}
    }
    insert botaoRestaurar into botoesExpandidos;    
    //println("saindo recursao fazer retornar");
    return botoesExpandidos;
}

public static function empilhaComando(acaoLSystem : AcaoLSystem,comando : Comando) : Void{
    insert comando into pilhaComandos;
}

public static function desempilhaComando(acaoLSystem : AcaoLSystem) : Comando{
    var tamPilha = sizeof pilhaComandos;
    if(tamPilha>=0){
        var c : Comando = pilhaComandos[tamPilha-1];
        delete c from pilhaComandos;
        delete acaoLSystem from pilhaAcoes;
        return c;
    }
    return null;
}

