package ca.renardnumerique.fractalr2.ui;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ApplicationCanvas {
    
    private Integer inicioDesenhoY = 140;

    private Rectangle areaDesenho;
    private ImageView imgAnterior;
    private ImageView imgProximo;
    private Rectangle proximo;
    private Rectangle anterior;
    private ImageView logo;


    private HBox canvasContainer;

    @Inject
    private ControlPanel controlPanel;

    public ApplicationCanvas(){}


    @PostConstruct
    public void initComponents(){

        canvasContainer = new HBox(8);
        canvasContainer.setId("canvas-container");
        imgAnterior = new ImageView("images/previous.png");
        imgAnterior.setOpacity(0.5);
        areaDesenho = new Rectangle();
        areaDesenho.setWidth(400);
        areaDesenho.setHeight(400);
        areaDesenho.setFill(Color.web("#FFFFFF"));
        imgProximo = new ImageView("images/next.png");
        imgProximo.setOpacity(0.5);
        canvasContainer.getChildren().addAll(imgAnterior,areaDesenho,imgProximo);


    }
    public ObservableList<Node> getChildren() {
        return canvasContainer.getChildren();
    }

    public void requestLayout() {
        canvasContainer.requestLayout();
    }

    public Rectangle getAreaDesenho() {
        return this.areaDesenho;
    }

    public HBox getContainer() {
        return canvasContainer;
    }
}
