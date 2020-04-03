package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import lombok.Data;

@Data
class BotaoTransformacoes extends Group {
    public Rectangle rect;
    public ImageView img;
    public ActionButton btn;

    public BotaoTransformacoes(){
        this.getChildren().addAll(rect,img);
    }

}
