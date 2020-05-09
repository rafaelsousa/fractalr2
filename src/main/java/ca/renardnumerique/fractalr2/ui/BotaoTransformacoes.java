package ca.renardnumerique.fractalr2.ui;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

class BotaoTransformacoes extends Group {
    
    private Rectangle rect;
    private ImageView img;
    private ActionButton btn;
   

    public BotaoTransformacoes(){
        this.getChildren().addAll(rect,img);
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public ImageView getImg() {
        return this.img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ActionButton getBtn() {
        return this.btn;
    }

    public void setBtn(ActionButton btn) {
        this.btn = btn;
    }

}
