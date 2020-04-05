package ca.renardnumerique.fractalr2;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;


@Data
public class Selector extends Rectangle {

    private Rectangle nodo;
    private Integer idCor;

    public Selector() {
        setCursor(Cursor.HAND);
        setFill(Color.RED);
        setStroke(Color.web("#888"));
        setWidth(10);
        setHeight(10);
        setArcHeight(5);
        setArcWidth(5);
    }
}
