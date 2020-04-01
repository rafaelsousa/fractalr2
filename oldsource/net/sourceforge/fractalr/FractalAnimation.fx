/**
 * Script para desenvolvimento e teste da anima��o em paralelo em JavFX
 * @author rafael
 */
package net.sourceforge.fractalr;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.animation.transition.*;
import javafx.animation.*;
import java.lang.Math;

import net.sourceforge.fractalr.lsystem.Comando;


public class FractalAnimation{
    public var x : Number;  
    public var y : Number;  
    
    public var linha : Line;
    public var transicoesGerais : Timeline[];
    
    public var animacao = SequentialTransition{
        content : bind transicoesGerais;
    }
    
    var lapis = Pencil.get();
    
    public function acaoConclusaoTimeLine() : Void {
        MainClass.instanciaAtual.design.canvas.requestLayout();
    }
    
    public function inicializar(comandos : Comando[]) : Void{
		var pos =0;
		var transicaoAnterior : ParallelTransition = null;
		for(iteracao in [0..comandos.size()-1]){
		    var comando = comandos[iteracao];
		    if(comando.tipoComando == Comando.MOVER){
			   
			    x = comando.coordenadaInicial.x;
			    y = comando.coordenadaInicial.y;
			    
			    var endx : Number = comando.coordenadaFinal.x;
			    var endy : Number = comando.coordenadaFinal.y;
			    
			    var tamanhoX = Math.abs(endx - endy); 
			    var tamanhoY = Math.abs(endx - endy);
			    
				linha = Line {
					startX: x,
					startY: y,
					endX: endx,
					endY: endy,
					strokeWidth: 1,
					stroke: Color.BLACK
				}
		            
		       var caminho = Path{
					elements : [
					MoveTo {
						x : x;
						y : y;
					},
					       	LineTo {
					       		x : (x + endx) / 2;
					       		y : (y + endy) / 2;
					       	}    	
					]
				}        
				var caminhoLapis = Path{
	                elements : [
	                	MoveTo {
	                	    x : x + lapis.image.width/2 - 3;
	                	    y : y - lapis.image.height/2;
	                	}, 
	                	LineTo {
	                		x : endx + lapis.image.width/2 - 3;
	                		y : endy - lapis.image.height/2;
	                	}    	
	                ]
	            }			
		        var transicoesDesenho = ParallelTransition {
		            node: linha,
		        	content : [
		        			FadeTransition{
		        			    duration: comando.duracao, 
		        			    fromValue	: 0,
		        			    toValue		: 1
		        			},
		                	ScaleTransition {
		                	        duration: comando.duracao, 
		                	        fromX:0
		                	        fromY:0
		                	        toX:1
		                	        toY:1;        
		                	    },
		                	PathTransition{
		                	    duration: comando.duracao, 
		                	    path: AnimationPath.createFromPath(caminho)
		                	}
		                ];
		            action : acaoConclusaoTimeLine;
		        }
		        
		        var transicaoLapis = PathTransition {
					path : AnimationPath.createFromPath(caminhoLapis);
					node : lapis;
					duration : comando.duracao
		        }
		        
		        var transicaoParalela = ParallelTransition{
				    content : [transicoesDesenho,transicaoLapis]
				}
				
		        //S� insere imediatamente na primeira itera��o
		        if(iteracao == 0){
		        	insert transicoesDesenho.node into MainClass.instanciaAtual.design.canvas.content;
		        }
		        
	            if(transicaoAnterior != null){
	         		transicaoAnterior.action = function(){
	         			insert transicoesDesenho.node into MainClass.instanciaAtual.design.canvas.content;
	         		}   
	            }
		        
		        insert transicaoParalela into transicoesGerais;
		        
		        transicaoAnterior = transicaoParalela;
		    }
		    if(comando.tipoComando == Comando.TRANSPORTAR){
		    			   
			    x = comando.coordenadaInicial.x;
			    y = comando.coordenadaInicial.y;
			    
			    var endx : Number = comando.coordenadaFinal.x;
			    var endy : Number = comando.coordenadaFinal.y;
			    
			    var tamanhoX = Math.abs(endx - endy); 
			    var tamanhoY = Math.abs(endx - endy);
			    
		            
				var caminho = Path{
					elements : [
						MoveTo {
							x : x;
							y : y;
						},
					    LineTo {
					    	x : (x + endx) / 2;
					       	y : (y + endy) / 2;
					    }    	
					]
				}        
				var caminhoLapis = Path{
	                elements : [
	                	MoveTo {
	                	    x : x + lapis.image.width/2 - 3;
	                	    y : y - lapis.image.height/2;
	                	}, 
	                	LineTo {
	                		x : endx + lapis.image.width/2 - 3;
	                		y : endy - lapis.image.height/2;
	                	}    	
	                ]
	            }			
		        
		        var transicaoLapis = PathTransition {
					path : AnimationPath.createFromPath(caminhoLapis);
					node : lapis;
					duration : 0s;
		        }
		        
		        
		        insert transicaoLapis into transicoesGerais;
		        
		    }
		}
    }
    
    function inserirLinhaProximoComando(comando : Comando){
    	x = comando.coordenadaInicial.x;
	    y = comando.coordenadaInicial.y;
	    
	    var endx : Number = comando.coordenadaFinal.x;
	    var endy : Number = comando.coordenadaFinal.y;
	    
	    var tamanhoX = Math.abs(endx - endy); 
	    var tamanhoY = Math.abs(endx - endy);
	    
		linha = Line {
			startX: x,
			startY: y,
			endX: endx,
			endY: endy,
			strokeWidth: 1,
			stroke: Color.BLACK
		}	    
		insert linha into MainClass.instanciaAtual.design.canvas.content;
    }
    
    public function play() : Void{
        animacao.play();
    }
    
    public function pause() : Void{
        if(animacao.running){
        	animacao.pause();
        }else{
            animacao.play();
        }
    }
    
    public function playFromStart() : Void{
        animacao.playFromStart();
    }
    
    public function running() : Boolean {
        return animacao.running;
    }
    
    public function stop() : Void {
        animacao.stop();
    }
    
    
}

