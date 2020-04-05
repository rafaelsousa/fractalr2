package ca.renardnumerique.fractalr2;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class ExamplePanel extends Group {

    public static ExamplePanel pnl;

    public static List<Point2D> coodEx = new ArrayList<>();
    public static Integer qteExemplos = 0;
    public static Integer buttonHeight = 25;

    public static Integer sequencial = qteExemplos++;


    public static Exemplo exemploRodando = null;

    private Text titulo = new Text("Exemplos");

    {
        titulo.setY(100);
        titulo.setX(90);
        titulo.setFont(new Font("Verdana", 12));
        titulo.setFill(Color.web("#9f4545"));
    }

    private ImageView rdp = new ImageView();

    {
        rdp.setFocusTraversable(Boolean.TRUE);
        rdp.setImage(new Image("images/bgexemplordp.png"));
        rdp.setY(115 + (qteExemplos * buttonHeight));
        rdp.setX(2);

    }

    private Exemplo flocoNeve = new Exemplo();

    {
        flocoNeve.setTitulo("Floco de neve Koch");
        flocoNeve.setFormula("a--a--a");
        flocoNeve.setTransformacoes(List.of("a=a+a--a+a"));
        flocoNeve.setAngulo(60);
    }

    Exemplo curvaPeano = new Exemplo();

    {
        curvaPeano.setTitulo("Curva de Peano");
        curvaPeano.setFormula("a");
        curvaPeano.setTransformacoes(List.of("a=a-a+a+a+a-a-a-a+a"));
        curvaPeano.setAngulo(90);
    }

    Exemplo curvaGosper = new Exemplo();

    {
        curvaGosper.setTitulo("Curva de Gosper");
        curvaGosper.setFormula("a");
        curvaGosper.setTransformacoes(List.of("a=a+b++b-a--aa-b+ ", "b=-a+bb++b+a--a-b"));
        curvaGosper.setAngulo(60);
    }

    Exemplo fractalGrama = new Exemplo();

    {
        fractalGrama.setTitulo("Fractal Grama");
        fractalGrama.setFormula("B");
        fractalGrama.setTransformacoes(List.of("B=cb-1+b2-B", "1=3+B", "2=+abB", "3=B", "b=bb"));
        fractalGrama.setAngulo(25);
    }

    Exemplo curvaDragao = new Exemplo();

    {
        curvaDragao.setTitulo("Curva do Dragao");
        curvaDragao.setFormula("B");
        curvaDragao.setTransformacoes(List.of("B=B+Ca+", "C=-aB-C"));
        curvaDragao.setAngulo(90);
    }

    Exemplo trianguloSierpinski = new Exemplo();

    {
        trianguloSierpinski.setTitulo("Triangulo Sierpinski");
        trianguloSierpinski.setFormula("aBa--aa--aa");
        trianguloSierpinski.setTransformacoes(List.of("B=--aBa++aBa++aBa--", "a=aa"));
        trianguloSierpinski.setAngulo(60);
    }

    public ExamplePanel() {
        this.getChildren().addAll(
                titulo,
                flocoNeve,
                curvaPeano,
                curvaGosper,
                fractalGrama,
                curvaDragao,
                trianguloSierpinski

        );
    }
}
