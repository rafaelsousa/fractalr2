package ca.renardnumerique.fractalr2.utils;

import jakarta.inject.Singleton;
import javafx.geometry.Point2D;
import javafx.scene.Node;

@Singleton
public class CoordinatesUtils {


    public double getAbsoluteCoordinateX(Node node){
        Point2D point2D = node.localToScene(node.getLayoutX(),node.getLayoutY());
        return point2D.getX();
    }

    public double getAbsoluteCoordinateY(Node node){
        Point2D point2D = node.localToScene(node.getLayoutX(),node.getLayoutY());
        return point2D.getY();
    }

}
