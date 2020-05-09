package ca.renardnumerique.fractalr2.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.renardnumerique.fractalr2.ActionButton;
import ca.renardnumerique.fractalr2.ButtonPanel;
import ca.renardnumerique.fractalr2.ExamplePanel;
import ca.renardnumerique.fractalr2.FormulaPanel;
import ca.renardnumerique.fractalr2.MainClass;
import ca.renardnumerique.fractalr2.TransformationPanel;
import ca.renardnumerique.fractalr2.lsystem.AcaoLSystem;
import jakarta.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Exemplo extends Button {

	private String formula = new String();
	private List<String> transformacoes = new ArrayList<>();
	private Integer angulo;
	private ImageView fundo = new ImageView();
	private Rectangle contornoRectangle = new Rectangle();
	
	
	private FormulaPanel formulaPanel;
	private MainClass mainClass;
	private TransformationPanel transformationPanel;
	private ButtonPanel buttonPanel;

	public Exemplo() {
		initializeComponents();
	}

	private void initializeComponents() {
		this.getStyleClass().add("example-button");
		this.setFocusTraversable(true);		
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
		formulaPanel.getBotoes().clear();
		mainClass.getPnlControle().setAngulo(angulo);
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
			ActionButton botao = buttonPanel.findButton(cod);
			if (cod == AcaoLSystem.ACAO_ANDAR) {
				if (mapAndar.get(btn) == null) {
					mapAndar.put(btn, cor);
				}
				botao.getCoresSeletor().setCor(mapAndar.get(btn));
			}
			if (cod == AcaoLSystem.ACAO_EXPANDIR) {
				if (mapProduzir.get(btn) == null) {
					mapProduzir.put(btn, cor);
				}
				botao.getCoresSeletor().setCor(mapProduzir.get(btn));
			}
			if (cod == AcaoLSystem.ACAO_FAZER_RETORNAR) {
				if (mapFazerRetornar.get(btn) == null) {
					mapFazerRetornar.put(btn, cor);
				}
				botao.getCoresSeletor().setCor(mapFazerRetornar.get(btn));
			}
			formulaPanel.getBotoes().add(botao.duplicar());
		}
		formulaPanel.redrawBar();
		transformationPanel.resetarBarra();
		transformationPanel.getBotoes().clear();
		var count = 0;
		for (String transformacao : transformacoes) {
			count++;
			if (count > 1) {
				transformationPanel.adicionarBarra();
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
					transformationPanel.getBotoes().add(ActionButton.BOTAO_IGUAL.duplicar());
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
				ActionButton botao = buttonPanel.findButton(cod);
				
				if (cod == AcaoLSystem.ACAO_ANDAR) {
					if (mapAndar.get(btn) == null) {
						mapAndar.put(btn, cor);
						// println("id cor{nroCor} simbolo{btn} {map}");
					}
					botao.getCoresSeletor().setCor(mapAndar.get(btn));
				}
				if (cod == AcaoLSystem.ACAO_EXPANDIR) {
					if (mapProduzir.get(btn) == null) {
						mapProduzir.put(btn, cor);
					}
					botao.getCoresSeletor().setCor(mapProduzir.get(btn));
				}
				if (cod == AcaoLSystem.ACAO_FAZER_RETORNAR) {
					if (mapFazerRetornar.get(btn) == null) {
						mapFazerRetornar.put(btn, cor);
					}
					botao.getCoresSeletor().setCor(mapFazerRetornar.get(btn));
				}
			}
			transformationPanel.redesenhaBarra();
		}
	}

	public void onMouseClicked() {
		doFractal();
	}

	public static void rodarExemplo(Exemplo exemplo) {
		exemplo.doFractal();
	}

	

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public List<String> getTransformacoes() {
		return this.transformacoes;
	}

	public void setTransformacoes(List<String> transformacoes) {
		this.transformacoes = transformacoes;
	}

	public Integer getAngulo() {
		return this.angulo;
	}

	public void setAngulo(Integer angulo) {
		this.angulo = angulo;
	}

	public ImageView getFundo() {
		return this.fundo;
	}

	public void setFundo(ImageView fundo) {
		this.fundo = fundo;
	}

	public Rectangle getContornoRectangle() {
		return this.contornoRectangle;
	}

	public void setContornoRectangle(Rectangle contornoRectangle) {
		this.contornoRectangle = contornoRectangle;
	}

	
}
