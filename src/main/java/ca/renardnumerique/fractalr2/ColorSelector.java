package ca.renardnumerique.fractalr2;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Data;

@Data
public class ColorSelector extends Group {
    private Double posX = 0.0;
    private Double posY = 0.0;
    private Selector selecionado;
    public Integer idSelecionado;
    private ActionButton botaoPai;
    private Rectangle nodo;
    public Boolean aberto = Boolean.FALSE;
    private Text txtMais;
    private Selector btnVermelho;
    private Selector btnVerde;
    private Selector btnAzul;
    private Selector btnAmarelo;
    public Selector btnBranco;
    public Rectangle btnControle;

    public ColorSelector() {
        create();
        this.getChildren().addAll(
                btnControle,
                txtMais,
                btnVermelho,
                btnVerde,
                btnAzul,
                btnAmarelo,
                btnBranco);
    }

    public void setCor(Integer id) {
        if (id == 0) {
            selecionado = btnBranco;
        }
        if (id == 1) {
            selecionado = btnVermelho;
        }
        if (id == 2) {
            selecionado = btnVerde;
        }
        if (id == 3)
            selecionado = btnAzul;
        if (id == 4)
            selecionado = btnAmarelo;
        corGradiente();
    }

    public void corGradiente() {
        botaoPai.setFillNormal(new LinearGradient(
                0.0,
                0.7,
                0.0,
                1.5,
                Boolean.TRUE,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#c8eab2")),
                new Stop(1.0, Color.web("#aaa"))));

    }


    private void create() {

        txtMais = new Text("+");
        txtMais.setX(posX + 2);
        txtMais.setY(posY + 10);
        txtMais.setFont(new Font("Bitstream Vera Sans Bold", 10));
        txtMais.setFill(Color.web("#021F77"));

        btnVermelho = new Selector();
        btnVermelho.setIdCor(1);
        btnVermelho.setX(posX + 2);
        btnVermelho.setNodo(nodo);
        btnVermelho.setY(posY);
        btnVermelho.setFill(Color.web("#D7AEFF"));
        btnVermelho.setVisible(aberto);


        btnVerde = new Selector();
        btnVerde.setIdCor(2);
        btnVerde.setNodo(nodo);
        btnVerde.setX(posX + 14);
        btnVerde.setY(posY);
        btnVerde.setFill(Color.web("#2EFF9E"));
        btnVerde.setVisible(aberto);


        btnAzul = new Selector();
        btnAzul.setIdCor(3);
        btnAzul.setNodo(nodo);
        btnAzul.setX(posX + 26);
        btnAzul.setY(posY);
        btnAzul.setFill(Color.web("#A7FFEC"));
        btnAzul.setVisible(aberto);


        btnAmarelo = new Selector();
        btnAmarelo.setIdCor(4);
        btnAmarelo.setNodo(nodo);
        btnAmarelo.setX(posX + 38);
        btnAmarelo.setY(posY);
        btnAmarelo.setFill(Color.web("#FEFF8D"));
        btnAmarelo.setVisible(aberto);

        btnBranco = new Selector();
        btnBranco.setIdCor(0);
        btnBranco.setNodo(nodo);
        btnBranco.setX(posX + 50);
        btnBranco.setY(posY);
        btnBranco.setFill(Color.web("#ffffff"));
        btnBranco.setVisible(aberto);

        btnControle = new Rectangle();
        btnControle.setX(posX);
        btnControle.setY(posY);
        btnControle.setWidth(12);
        btnControle.setHeight(12);
        btnControle.setArcWidth(8);
        btnControle.setArcHeight(8);
        btnControle.setCursor(Cursor.HAND);
        btnControle.setFill(Color.TRANSPARENT);
        btnControle.setStroke(Color.web("#888"));
        btnControle.setOnMouseEntered(e -> {
            btnControle.setStroke(Color.web("#444444"));
        });
        btnControle.setOnMouseExited(e -> {
            btnControle.setStroke(Color.web("#888"));
            btnControle.setWidth(12);
            if (aberto) {
                posX = posX + 48;
            }
            aberto = false;
            txtMais.setText("+");
            txtMais.setCursor(Cursor.HAND);
        });

        btnControle.setOnMouseClicked(e -> {
            btnControle.setStroke(Color.web("#1289FF"));
            btnControle.setWidth(60);
            if (!aberto) {
                posX = posX - 48;
            }
            aberto = true;
            btnControle.setCursor(Cursor.DEFAULT);
            txtMais.setText("");
        });
    }

    ;

}

 