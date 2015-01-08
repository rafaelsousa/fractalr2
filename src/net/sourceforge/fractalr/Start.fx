package net.sourceforge.fractalr;

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.paint.*;
import net.sourceforge.fractalr.*;

var mainClass = MainClass{};//para acertar o escopo das variaveis
Stage {
    title: "Fractal-R Version 0.9 - Beta"
    resizable: false
    width:  bind mainClass.larguraSistema;
    height : bind mainClass.alturaSistema;
    scene: Scene {
    	content:[
    		mainClass
    		]
		fill:Color.web("#ccc")
    }  

}