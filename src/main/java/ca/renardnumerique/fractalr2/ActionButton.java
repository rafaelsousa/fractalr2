package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;


public class ActionButton extends Group {

	private Image icone;

	public LinearGradient fillNormal;

	public ActionButton() {

		fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
		fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
		fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
	}

	public Image getIcone() {
		return this.icone;
	}

	public LinearGradient getFillNormal() {
		return this.fillNormal;
	}
}
