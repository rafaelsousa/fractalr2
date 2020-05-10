package ca.renardnumerique.fractalr2.ui;

import ca.renardnumerique.fractalr2.ApplicationLayout;
import ca.renardnumerique.fractalr2.utils.Ponto;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import jakarta.annotation.PostConstruct;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;

import java.util.List;

public class Pencil{

    private ApplicationCanvas applicationCanvas;
    private ApplicationLayout applicationLayout;

    private DropShadow writtingEffect;

    private Boolean focusTraversable;
    private PathTransition animation;
    private Image imgPlay;
    private List<PathElement> linhas;
    private Image imgPause;

    private Boolean escreve;
    private Group canvas;
    private Integer contadorLinhas;
    private List<Ponto> coordenates;
    private Boolean mudouX;
    private Boolean mudouY;

    private ImageView playPauseButton;
    private ImageView pencilImage;
    private Group pencilContainer;
    private Integer x;
    private Integer y;

    private Pencil() {
        writtingEffect = new DropShadow();
        focusTraversable = Boolean.TRUE;
        imgPlay = new Image("images/play.png");
        imgPause = new Image("images/pause.png");
        escreve = Boolean.FALSE;
        contadorLinhas = 0;
        mudouX = Boolean.FALSE;
        mudouY = Boolean.FALSE;
    }

    @PostConstruct
    private void initComponents() {
        animation = new PathTransition();
        playPauseButton =  new ImageView(imgPlay);
        setImage(new Image("images/lapis.png"));
        JavaFxObservable
                .valuesOf(animation.statusProperty())
                .map(this::setPencilImage)
                .subscribe(playPauseButton::setImage);
        writtingEffect.setRadius(20);
        writtingEffect.setOffsetY(10);
        writtingEffect.setOffsetX(-5);
        writtingEffect.setSpread(.25);
        writtingEffect.setColor(Color.web("#BDBD00"));

    }


    private void setImage(Image image) {
        pencilImage = new ImageView(image);
        pencilImage.setY(0);
        pencilImage.setX(0);
        this.pencilContainer = new Group();
        this.pencilContainer.getChildren().add(pencilImage);
    }

    private Image setPencilImage(Animation.Status status) {
        switch (status) {
            case RUNNING:
                return imgPause;
            default:
                return imgPlay;
        }
    }

    private Path caminho = new Path();



    public void escreveFractal() {
        var inX = applicationCanvas.getAreaDesenho().getX() + 10;
        var inY = applicationCanvas.getAreaDesenho().getY() + 25;
        clear();
        Path path = new Path();
        path.setStroke(Color.web("#29345E"));
        path.setStrokeWidth(1);
        path.getElements().addAll(linhas);

        MoveTo ptAux = new MoveTo(coordenates.get(0).getX() + inX, coordenates.get(0).getY() + inY);

        canvas.getChildren().addAll(path);
        linhas.add(ptAux);
        coordenates.stream().forEach(pt ->
                {
                    System.out.println(pt);
                    linhas.add(new LineTo(pt.getX(), pt.getY()));
                }
        );
    }

    public void clear() {
//        contadorLinhas = 0;
//        this.pencilContainer.getChildren().get(0).setTranslateX(0);
//        this.pencilContainer.getChildren().get(0).setTranslateY(0);
//        posicionaInicio();
//        linhas.clear();
    }

    public void processaCoordenadas(List<Ponto> coordenadas) {
        coordenates = coordenadas;
    }

    protected void insere_caminho(Integer coordX, Integer coordY) {
        caminho.getElements().add(new LineTo(coordX, coordY));
    }

    protected void posicionaInicio() {
        //this.x=MainClass.instanciaAtual.design.areaDesenho.x+10;
        //this.y=MainClass.instanciaAtual.design.areaDesenho.y-20;
        //println("inicio x: {x} y: {y} translatex: {translateX} translatey: {translateY}");
    }


    public void stop() {
        this.animation.stop();
        posicionaInicio();
    }

    public void play() {
        Double tempo = 3 + 2 * (Math.log(caminho.getElements().size()) / Math.log(2)); // log2 do numero de pontos;
        constroi(new Duration(tempo * 1000));

    }

    public void constroi(Duration tempo) {
        animation.setDuration(tempo);
        escreveFractal();
    }

    public void reset() {
        this.animation.stop();
        this.clear();
    }

    public void setX(int x) {
        pencilImage.setX(x);
    }
    public void setY(int y) {
        pencilImage.setY(y);
    }

    public Group getPencilContainer() {
        return pencilContainer;
    }

    public Double getHeight() {
        return pencilImage.getImage().getHeight();
    }


    public Double getWidth() {
        return pencilImage.getImage().getWidth();
    }

    public void toFront() {
        pencilContainer.toFront();
    }
}