package com.github.rafaelsousa.fractalr2.ui;
import javafx.scene.*;

import javafx.scene.paint.*;
import javafx.scene.shape.*;


public static var instanciaAtual:ButtonPanel;


public static function getBotao(codigoBotao:Integer): ActionButton {
    for(btn in instanciaAtual.botoes){
        if(btn.acaoLSystem.tipoAcao == codigoBotao)
        	return btn;
    }
    return null;
}

public class ButtonPanel extends CustomNode  {
  public var botoes =ActionButton.getBotao(); // Fabrica de Bot�es
      var linhaSuperior= Line {
                        startX: 0
                        startY: 0
                        endX: 972
                        endY: 0
                        stroke: Color.web("#cccccc");       
                        strokeWidth: 1
      };
      
    override public function create(): Node {
        	instanciaAtual=this;
                   Group {
                       content: [
                       			botoes,
                       			linhaSuperior
                           ]
                   };
               }        
               
}
