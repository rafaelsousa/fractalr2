package com.github.rafaelsousa.fractalr2.ui;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class BotaoFormula extends Group {
	
	private Rectangle rect;
	private ImageView img;
	
	public BotaoFormula(){
		this.getChildren().add(rect);
		this.getChildren().add(img);		
	}
	
	

}
