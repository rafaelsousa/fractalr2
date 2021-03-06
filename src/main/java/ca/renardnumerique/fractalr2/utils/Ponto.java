package ca.renardnumerique.fractalr2.utils;
public class Ponto {

    private Double x;
    private Double y;

    

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X:" + x + "Y:" + y;
    }

    public Ponto(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
