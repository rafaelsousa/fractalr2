package ca.renardnumerique.fractalr2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import lombok.Data;

import java.util.List;

@Data
public class Pencil extends ImageView {


    public static final Pencil instance = new Pencil();

    private Boolean escreve = false;
    private Group canvas;
    private Integer contadorLinhas = 0;
    private List<Ponto> coordenates;
    private Boolean mudouX = Boolean.FALSE;
    private Boolean mudouY = Boolean.FALSE;

    private DropShadow efeitoEscrita = new DropShadow();

    {
        efeitoEscrita.setRadius(20);
        efeitoEscrita.setOffsetY(10);
        efeitoEscrita.setOffsetX(-5);
        efeitoEscrita.setSpread(.25);
        efeitoEscrita.setColor(Color.web("#BDBD00"));
    }

    public Boolean focusTraversable = Boolean.TRUE;
    public Image image = new Image("images/lapis.png");


    private PathTransition animacao = new PathTransition();

    private List<PathElement> linhas;
    private Image imgPlay = new Image("images/play.png");
    private Image imgPause = new Image("images/pause.png");

    private ImageView playPauseButton = new ImageView(imgPlay);

    private Pencil() {
        JavaFxObservable
                .valuesOf(animacao.statusProperty())
                .map(this::setPencilImage)
                .subscribe(playPauseButton::setImage);
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
        var inX = MainClass.getInstance().getDesign().getAreaDesenho().getX() + 10;
        var inY = MainClass.getInstance().getDesign().getAreaDesenho().getY() + 25;
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
        contadorLinhas = 0;
        this.setTranslateX(0);
        this.setTranslateY(0);
        posicionaInicio();
        linhas.clear();
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
        this.animacao.stop();
        posicionaInicio();
    }

    public void play() {
        Double tempo = 3 + 2 * (Math.log(caminho.getElements().size()) / Math.log(2)); // log2 do numero de pontos;
        constroi(new Duration(tempo * 1000));

    }

    public void constroi(Duration tempo) {
        animacao.setDuration(tempo);
        escreveFractal();
    }

    public void reset() {
        this.animacao.stop();
        this.clear();
    }

}