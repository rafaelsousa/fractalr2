package ca.renardnumerique.fractalr2.ui;

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


	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public ActionButton getBtn() {
		return btn;
	}

	public void setBtn(ActionButton btn) {
		this.btn = btn;
	}
}