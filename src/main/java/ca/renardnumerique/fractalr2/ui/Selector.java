package ca.renardnumerique.fractalr2.ui;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    public Rectangle getNodo() {
        return this.nodo;
    }

    public void setNodo(Rectangle nodo) {
        this.nodo = nodo;
    }

    public Integer getIdCor() {
        return this.idCor;
    }

    public void setIdCor(Integer idCor) {
        this.idCor = idCor;
    }

}
