package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

class BotaoFormula extends Group {

	private Rectangle rect;
	private ImageView img;
	private ActionButton btn;

	public BotaoFormula() {
		this.getChildren().addAll(rect, img);
	}
}