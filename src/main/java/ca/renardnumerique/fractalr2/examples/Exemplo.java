package ca.renardnumerique.fractalr2.examples;

import ca.renardnumerique.fractalr2.ActionButton;
import ca.renardnumerique.fractalr2.ButtonPanel;
import ca.renardnumerique.fractalr2.ExamplePanel;
import ca.renardnumerique.fractalr2.FormulaPanel;
import ca.renardnumerique.fractalr2.MainClass;
import ca.renardnumerique.fractalr2.TransformationPanel;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class Exemplo extends Button {

	private String formula = new String();
	private List<String> transformacoes = new ArrayList<>();
	private Integer angulo;
	private ImageView fundo = new ImageView();
	private Rectangle contornoRectangle = new Rectangle();

	public Exemplo() {
		initializeComponents();
	}

	private void initializeComponents() {

		this.setFocusTraversable(true);
		this.setGraphic(new ImageView(new Image("file:images/bgexemplo.png")));
		this.setWidth(Integer.MAX_VALUE);
		this.setHeight(20);
		this.setFont(new Font("Verdana", 10));
		this.setOnMouseClicked(e -> {
			doFractal();
		});
	}

	public void doFractal() {
		ExamplePanel.exemploRodando = this;
		Integer cod;
		Map<Character, Integer> mapAndar = new HashMap<>();
		Map<Character, Integer> mapProduzir = new HashMap<>();
		Map<Character, Integer> mapFazerRetornar = new HashMap<>();
		FormulaPanel.getInstance().getBotoes().clear();
		MainClass.getInstance().getPnlControle().setAngulo(angulo);
		String controle = "";
		for (char btn : formula.toCharArray()) {
			if (btn == ' ')
				continue;
			cod = 1;
			var cor = 0;
			if (btn == '-') {
				cod = AcaoLSystem.ACAO_GIRAR_ESQUERDA;
			} else if (btn == ' ') {
				cod = AcaoLSystem.ACAO_GIRAR_DIREITA;
			} else {
				String minusculas = new String("abcdefghikjlmnopqrstuvxyz");
				if (minusculas.indexOf(btn) >= 0) {
					cod = AcaoLSystem.ACAO_ANDAR;
					cor = minusculas.indexOf(btn);
				}
				String maiusculas = minusculas.toUpperCase();
				if (maiusculas.indexOf(btn) >= 0) {
					cod = AcaoLSystem.ACAO_EXPANDIR;
					cor = maiusculas.indexOf(btn);
				}
				String fazerRetornar = new String("123456789");
				if (fazerRetornar.indexOf(btn) >= 0) {
					cod = AcaoLSystem.ACAO_FAZER_RETORNAR;
					cor = fazerRetornar.indexOf(btn);
				}
			}
			ActionButton botao = ButtonPanel.getBotao(cod);
			if (cod == AcaoLSystem.ACAO_ANDAR) {
				if (mapAndar.get(btn) == null) {
					mapAndar.put(btn, cor);
				}
				botao.coresSeletor.setCor(mapAndar.get(btn));
			}
			if (cod == AcaoLSystem.ACAO_EXPANDIR) {
				if (mapProduzir.get(btn) == null) {
					mapProduzir.put(btn, cor);
				}
				botao.coresSeletor.setCor(mapProduzir.get(btn));
			}
			if (cod == AcaoLSystem.ACAO_FAZER_RETORNAR) {
				if (mapFazerRetornar.get(btn) == null) {
					mapFazerRetornar.put(btn, cor);
				}
				botao.coresSeletor.setCor(mapFazerRetornar.get(btn));
			}
			FormulaPanel.getInstance().getBotoes().add(botao.duplicar());
		}
		FormulaPanel.getInstance().redrawBar();
		TransformationPanel.instanciaAtual.resetarBarra();
		TransformationPanel.instanciaAtual.getBotoes().clear();
		var count = 0;
		for (String transformacao : transformacoes) {
			count++;
			if (count > 1) {
				TransformationPanel.instanciaAtual.adicionarBarra();
			}
			for (Character btn : transformacao.toCharArray()) {
				if (btn.toString() == " ") {
					continue;
				}
				cod = 1;
				var cor = 0;
				if (btn.toString() == "-") {
					cod = AcaoLSystem.ACAO_GIRAR_ESQUERDA;
				} else if (btn.toString() == "+") {
					cod = AcaoLSystem.ACAO_GIRAR_DIREITA;
				} else if (btn.toString() == "=") {
					TransformationPanel.instanciaAtual.getBotoes().add(ActionButton.BOTAO_IGUAL.duplicar());
					continue;
				} else {
					String minusculas = new String("abcdefghikjlmnopqrstuvxyz");
					if (minusculas.indexOf(btn.toString()) > 0) {
						cod = AcaoLSystem.ACAO_ANDAR;
						cor = minusculas.indexOf(btn.toString());
					}
					String maiusculas = minusculas.toUpperCase();
					if (maiusculas.indexOf(btn.toString()) > 0) {
						cod = AcaoLSystem.ACAO_EXPANDIR;
						cor = maiusculas.indexOf(btn.toString());
					}
					String fazerRetornar = new String("123456789");
					if (fazerRetornar.indexOf(btn.toString()) >= 0) {
						cod = AcaoLSystem.ACAO_FAZER_RETORNAR;
						cor = fazerRetornar.indexOf(btn.toString());
					}
				}
				ActionButton botao = ButtonPanel.getBotao(cod);
				if (cod == AcaoLSystem.ACAO_ANDAR) {
					if (mapAndar.get(btn) == null) {
						mapAndar.put(btn, cor);
						// println("id cor{nroCor} simbolo{btn} {map}");
					}
					botao.coresSeletor.setCor(mapAndar.get(btn));
				}
				if (cod == AcaoLSystem.ACAO_EXPANDIR) {
					if (mapProduzir.get(btn) == null) {
						mapProduzir.put(btn, cor);
					}
					botao.coresSeletor.setCor(mapProduzir.get(btn));
				}
				if (cod == AcaoLSystem.ACAO_FAZER_RETORNAR) {
					if (mapFazerRetornar.get(btn) == null) {
						mapFazerRetornar.put(btn, cor);
					}
					botao.coresSeletor.setCor(mapFazerRetornar.get(btn));
				}
				TransformationPanel.instanciaAtual.getBotoes().add(botao.duplicar());
			}
			TransformationPanel.instanciaAtual.redesenhaBarra();
		}
	}

	public void onMouseClicked() {
		doFractal();
	}

	public static void rodarExemplo(Exemplo exemplo) {
		exemplo.doFractal();
	}
}
