package ca.renardnumerique.fractalr2;

import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionButton extends Group {

    private Image icone;
    private AcaoLSystem acaoLSystem;
    private LinearGradient fillNormal;
    public ActionButton() {

        fillNormal = new LinearGradient(0.0, 0.7, 0.0, 1.5, true, CycleMethod.NO_CYCLE);
        fillNormal.getStops().add(new Stop(0.2, Color.web("#FFFAC1")));
        fillNormal.getStops().add(new Stop(1.0, Color.web("#AAA")));
    }

    public LinearGradient getFillNormal() {
        return this.fillNormal;
    }


    public static final ActionButton BOTAO_IGUAL = new ActionButton(new Image("images/igual.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-1),null);
    public static final ActionButton BOTAO_SEPARADOR = new ActionButton(new Image("imagens/botoes/draw-arrow-back.png"), new AcaoLSystem(AcaoLSystem.ACAO_IGUAL,"+",-2),null);


    public ActionButton duplicar() {
        return null;
    }
}
