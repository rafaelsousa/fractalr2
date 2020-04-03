package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import lombok.Data;

@Data
public class ActionButton extends Group {

    private Image icone;

    public LinearGradient fillNormal;

    public ActionButton() {

        fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
        fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
        fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
    }

    public LinearGradient getFillNormal() {
        return this.fillNormal;
    }

    public static ActionButton BOTAO_IGUAL = new ActionButton("imagens/igual.png", AcaoLSystem.EQUALS);
    static {
        public static var botaoIgual = ActionButton{
            iconeUrl:"{__DIR__}"
            acaoLSystem:AcaoLSystem{
                simbolo:"=";
            }
        };
    }


}
