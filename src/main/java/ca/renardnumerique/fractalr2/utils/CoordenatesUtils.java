package ca.renardnumerique.fractalr2.utils;

import jakarta.inject.Singleton;
import javafx.scene.Node;

@Singleton
public class CoordenatesUtils {

    public double getAbsoluteCoordenateX(Node node){
        return node.localToScreen(node.getBoundsInLocal()).getMinX();
    }

    public double getAbsoluteCoordenateY(Node node){
        return node.localToScreen(node.getBoundsInLocal()).getMinY();
    }

}
