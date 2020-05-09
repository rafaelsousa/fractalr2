package ca.renardnumerique.fractalr2.ui;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;


public class DesktopLayout extends Group {
    
    private Integer inicioDesenhoY = 140;
    private ImageView fundoSistema;
    private Rectangle areaDesenho = new Rectangle();
    private ImageView imgAnterior = new ImageView();
    private ImageView imgProximo = new ImageView();
    private Rectangle proximo = new Rectangle();
    private Rectangle anterior = new Rectangle();
    private ImageView logo = new ImageView();
    
    public DesktopLayout(){
        
    }
    
    public void initComponents(){
        fundoSistema = new ImageView(new Image("file:images/bg_sistema.png"));
        fundoSistema.setFocusTraversable(Boolean.TRUE);
        fundoSistema.setY(0.0);
        fundoSistema.setX(0.0);

        areaDesenho.setX(236);
        areaDesenho.setY(inicioDesenhoY);
        areaDesenho.setWidth(500);
        areaDesenho.setHeight(500);
        areaDesenho.setCursor(Cursor.DEFAULT);
        areaDesenho.setFill(Color.web("#FFF"));

        imgAnterior.setOpacity(0.1);
        imgAnterior.setFocusTraversable(Boolean.TRUE);
        imgAnterior.setImage(new Image("file:images/anterior.png"));
        imgAnterior.setX(166);
        imgAnterior.setY(inicioDesenhoY + 200);

        imgProximo.setOpacity(0.1);
        imgProximo.setFocusTraversable(Boolean.TRUE);
        imgProximo.setImage(new Image("file:images/proximo.png"));
        imgProximo.setY(inicioDesenhoY + 200);
        imgProximo.setX(930);
        
        proximo.setX(940);
        proximo.setY(inicioDesenhoY);
        proximo.setArcWidth(10);
        proximo.setArcHeight(10);
        proximo.setWidth(20);
        proximo.setHeight(575);
        proximo.setOpacity(0.1);
        proximo.setCursor(Cursor.HAND);

        LinearGradient proximoLinearGradient
                = new LinearGradient(0.0,
                0.0,
                0.0,
                0.05,
                Boolean.TRUE,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#999")),
                new Stop(1.0, Color.web("green")));

        proximo.setFill(proximoLinearGradient);
        proximo.setStroke(Color.web("#888"));
        proximo.setOnMouseEntered((e) -> {
                    this.proximo.setOpacity(0.2);
                    this.proximo.setOpacity(0.8);
                }
        );

        proximo.setOnMouseClicked(e -> {
            //ControlPanel.proxima();
        });
        proximo.setOnMouseExited(e -> {
            this.proximo.setOpacity(0.1);
        });

        anterior.setX(166);
        anterior.setY(inicioDesenhoY);
        anterior.setOpacity(0.1);
        anterior.setArcWidth(10);
        anterior.setArcHeight(10);
        anterior.setWidth(20);
        anterior.setHeight(575);
        anterior.setCursor(Cursor.HAND);
        anterior.setFill(Color.web("#FFF"));
        anterior.setOnMouseEntered(e -> {
            imgAnterior.setOpacity(0.5);
            this.anterior.setOpacity(0.2);
        });
        anterior.setOnMouseClicked(e -> {
            //ControlPanel.anterior();
        });
        anterior.setOnMouseExited(e -> {
            imgAnterior.setOpacity(0.1);
            this.anterior.setOpacity(0.1);
        });
        anterior.setStroke(Color.web("#888"));

        logo.setFocusTraversable(true);
        logo.setImage(new Image("file:images/logo.png"));
        logo.setY(4);
        logo.setX(10);

        this.getChildren()
                .addAll(fundoSistema, logo, areaDesenho);
    }



    

    public Integer getInicioDesenhoY() {
        return this.inicioDesenhoY;
    }

    public void setInicioDesenhoY(Integer inicioDesenhoY) {
        this.inicioDesenhoY = inicioDesenhoY;
    }

    public ImageView getFundoSistema() {
        return this.fundoSistema;
    }

    public void setFundoSistema(ImageView fundoSistema) {
        this.fundoSistema = fundoSistema;
    }

    public Rectangle getAreaDesenho() {
        return this.areaDesenho;
    }

    public void setAreaDesenho(Rectangle areaDesenho) {
        this.areaDesenho = areaDesenho;
    }

    public ImageView getImgAnterior() {
        return this.imgAnterior;
    }

    public void setImgAnterior(ImageView imgAnterior) {
        this.imgAnterior = imgAnterior;
    }

    public ImageView getImgProximo() {
        return this.imgProximo;
    }

    public void setImgProximo(ImageView imgProximo) {
        this.imgProximo = imgProximo;
    }

    public Rectangle getProximo() {
        return this.proximo;
    }

    public void setProximo(Rectangle proximo) {
        this.proximo = proximo;
    }

    public Rectangle getAnterior() {
        return this.anterior;
    }

    public void setAnterior(Rectangle anterior) {
        this.anterior = anterior;
    }

    public ImageView getLogo() {
        return this.logo;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }



    

}
