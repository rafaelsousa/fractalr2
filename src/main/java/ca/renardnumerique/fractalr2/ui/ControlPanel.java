package ca.renardnumerique.fractalr2.ui;


import ca.renardnumerique.fractalr2.Fractal;
import ca.renardnumerique.fractalr2.MainClass;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControlPanel extends FlowPane {


	public Text txtAngulo;
	public Rectangle contorno = new Rectangle();
	private final ImageView imgPlay = new ImageView();
	private Text ttPlay = new Text("Start");
	private Text ttStop = new Text("Pause");
	private ImageView imgStop = new ImageView();
	private Text ttReinicio = new Text("Restart");
	private ImageView imgReinicio = new ImageView();

	private Spinner<Integer> spinner = new Spinner<>();
	private SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20,1);

	private Integer angulo = 90;

	private Integer iteracoes = 0;
	private Integer posBotoes = 75;
	private ImageView limpar = new ImageView();
	private Rectangle designRetangulo = new Rectangle();

	private Text titulo = new Text("Iteration");

	public Slider anguloSlider;
	private FormulaPanel formulaPanel;
	private MainClass mainClass;
	private Fractal fractal;


	public ControlPanel() {
		initComponents();
		this.getChildren().addAll(txtAngulo, anguloSlider, titulo, imgPlay, ttPlay,
				imgStop, ttStop, imgReinicio, ttReinicio, spinner);
	}

	public void initComponents(){

		this.getStyleClass().add("control-panel");

		txtAngulo = new Text("Turning Angle: "+angulo);

		anguloSlider = new Slider(0,360,angulo);
		anguloSlider.setOrientation(Orientation.HORIZONTAL);

		anguloSlider.valueProperty().addListener(e->{
			String sliderNewCaption = String.format("Turning Angle: %s",String.valueOf((int)anguloSlider.getValue()));
			txtAngulo.setText(sliderNewCaption);
		});

		spinner.setValueFactory(spinnerValueFactory);
		limpar.setFocusTraversable(Boolean.TRUE);
		limpar.setImage(new Image("images/limpar.png"));
		//limpar.setY(5);
		//limpar.setX(790);
		//designRetangulo.setY(-2);
		//designRetangulo.setX(785);

		final DropShadow shadow = new DropShadow();
		shadow.setRadius(30);
		shadow.setSpread(0.45f);
		shadow.setColor(Color.web("#FF2828"));
		designRetangulo.setOnMouseEntered(e -> {
			designRetangulo.setStroke(Color.web("#444444"));
			designRetangulo.setEffect(shadow);
		});
		designRetangulo.setOnMouseExited(e -> {
			designRetangulo.setEffect(null);
			designRetangulo.setStroke(Color.web("#f8f8f8"));
		});
		designRetangulo.setOnMouseClicked(e -> {
			designRetangulo.setEffect(null);
			designRetangulo.setStroke(Color.web("#f8f8f8"));
			this.angulo = 0;
		   formulaPanel.getBotoes().clear();
		   formulaPanel.redrawBar();
		   for(Node transformation : mainClass.getTransformations().getChildren()){
				if(transformation instanceof TransformationPanel){
					TransformationPanel t  = (TransformationPanel) transformation;
					t.getBotoes().clear();
					t.redesenhaBarra();
					t.resetarBarra();

				}
			}
            mainClass.getPencil().clear();
             		   fractal.limpaCanvas();
		});
		designRetangulo.setStroke(Color.web("#f8f8f8"));
		titulo.setY(81);
		titulo.setX(840);
		titulo.setFont(Font.font("Verdana", 10));
		txtAngulo.setY(13);
		txtAngulo.setY(840);
		txtAngulo.setFont(Font.font("Verdana", FontWeight.BOLD, 10));


		imgPlay.setFocusTraversable(Boolean.TRUE);
		// imgPlay.setImage: bind Pencil.get().img;
		imgPlay.setY(posBotoes + 21);
		imgPlay.setX(840);
		imgPlay.setOnMouseClicked(e -> {
			// Fractal.iniciarDesenho();
		});
		imgPlay.setOnMouseEntered(e -> {
			DropShadow effect = new DropShadow();
			effect.setRadius(50);
			effect.setSpread(0.65);
			effect.setColor(Color.web("#A1FF6E"));
			imgPlay.setEffect(effect);
		});
		imgPlay.setOnMouseExited(e -> imgPlay.setEffect(null));

		ttPlay.setY(posBotoes + 50);
		ttPlay.setX(840);
		ttPlay.setFont(Font.font("Verdana", 8));

		ttStop.setY(posBotoes + 50);
		ttStop.setX(885);
		ttStop.setFont(Font.font("Verdana", 8));

		imgStop.setFocusTraversable(Boolean.TRUE);
		imgStop.setImage(new Image("file:images/pause.png"));
		imgStop.setY(posBotoes + 21);
		imgStop.setX(885);
		imgStop.setOnMouseClicked(e -> {
			// Fractal.pararDesenho();
		});

		imgStop.setOnMouseEntered(e -> {
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(50);
			dropShadow.setSpread(0.65);
			dropShadow.setColor(Color.web("#FF2828"));
		});
		imgStop.setOnMouseExited(e -> imgStop.setEffect(null));

		ttReinicio.setY(posBotoes + 50);
		ttReinicio.setX(925);
		ttReinicio.setFont(Font.font("Verdana", 8));
		ttReinicio.setFill(Color.web("#222"));
		ttReinicio.setOnMouseClicked(e -> fractal.reiniciarDesenho());
		imgReinicio.setFocusTraversable(Boolean.TRUE);
		imgReinicio.setImage(new Image("file:images/restart.png"));
		imgReinicio.setY(posBotoes + 21);
		imgReinicio.setX(930);
		imgReinicio.setOnMouseClicked(e -> {
			// Fractal.reiniciarDesenho();
		});

		DropShadow effect = new DropShadow(50, Color.web("#7197FF"));
		effect.setSpread(0.65);
		imgReinicio.setOnMouseEntered(e -> imgReinicio.setEffect(effect));
		imgReinicio.setOnMouseExited(e -> imgReinicio.setEffect(null));
	}


	public Integer getIteracoes() {
		return iteracoes;
	}

	public Integer getAngulo() {
		return angulo;
	}

	public void setAngulo(Integer angulo) {
		this.angulo = angulo;
	}

	public Slider getAnguloSlider() {
		return anguloSlider;
	}
}