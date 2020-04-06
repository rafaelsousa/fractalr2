package ca.renardnumerique.fractalr2;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import ca.renardnumerique.fractalr2.examples.CurvaDragao;
import ca.renardnumerique.fractalr2.examples.CurvaGosper;
import ca.renardnumerique.fractalr2.examples.CurvaPeano;
import ca.renardnumerique.fractalr2.examples.Exemplo;
import ca.renardnumerique.fractalr2.examples.FlocoNeve;
import ca.renardnumerique.fractalr2.examples.FractalGrama;
import ca.renardnumerique.fractalr2.examples.Sierpinski;


@Data
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
        rdp.setImage(new Image("file:images/bgexemplordp.png"));
        rdp.setY(115 + (qteExemplos * buttonHeight));
        rdp.setX(2);
    }

    public ExamplePanel() {
        this.getChildren().addAll(
                titulo,
                new FlocoNeve(),
                new CurvaPeano(),
                new CurvaGosper(),
                new FractalGrama(),
                new CurvaDragao(),
                new Sierpinski()

        );
    }
}
