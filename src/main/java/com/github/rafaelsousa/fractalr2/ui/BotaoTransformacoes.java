package com.github.rafaelsousa.fractalr2.ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class BotaoTransformacoes extends Group  {
	
    public Rectangle rect;
    public ImageView img;
    public ActionButton btn;

    public Node create(){
    	
    	Group group = new Group();
    	group.getChildren().add(rect);
    	group.getChildren().add(img);
    	
    	return group;
       
   }

}
