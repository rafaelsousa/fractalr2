package ca.renardnumerique.fractalr2;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;


@Data
public class Seletor extends Rectangle {
       private Rectangle nodo;
       private Integer idCor;
	   private Integer width= 10;
	   private Integer height= 10;
	   private Integer arcWidth =5;
	   private Integer arcHeight =5;
	   private Cursor cursor=Cursor.HAND;
	   private Color fill =Color.web("red");
	   private Color stroke=Color.web("#888");

	   public Seletor(){
		   this.setOnMouseClicked(e->{
			   cursor=Cursor.HAND;
		   });
	   }
}
