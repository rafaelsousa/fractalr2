package ca.renardnumerique.fractalr2.ui;


import ca.renardnumerique.fractalr2.Fractal;
import ca.renardnumerique.fractalr2.MainClass;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControlPanel extends FlowPane {


	public Text txtAngulo;
	public Rectangle contorno = new Rectangle();
	private ImageView playImage = new ImageView();
	private Text playText;
	private Text pauseCaption;
	private ImageView pauseImage;
	private Text restartCaption;
	private ImageView restartImage;

	private Spinner<Integer> iterationSpinner = new Spinner<>();
	private SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20,1);

	private Integer angulo = 90;

	private Integer iteracoes = 0;
	private Integer posBotoes = 75;
	private Rectangle designRetangulo = new Rectangle();

	private Text iterationSpinnerCaption = new Text("Iteration");

	public Slider anguloSlider;
	private FormulaPanel formulaPanel;
	private MainClass mainClass;
	private Fractal fractal;


	public ControlPanel() {
		initComponents();
	}

	public void initComponents(){
		this.getStyleClass().add("control-panel");


		txtAngulo = new Text("Turning Angle: " + angulo);
		anguloSlider = new Slider(0,360,angulo);
		anguloSlider.setOrientation(Orientation.HORIZONTAL);
		anguloSlider.valueProperty().addListener(e->{
			String sliderNewCaption = String.format("Turning Angle: %s",String.valueOf((int)anguloSlider.getValue()));
			txtAngulo.setText(sliderNewCaption);
		});

		iterationSpinner.setValueFactory(spinnerValueFactory);


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
		iterationSpinnerCaption.setY(81);
		iterationSpinnerCaption.setX(840);
		iterationSpinnerCaption.setFont(Font.font("Verdana", 10));
		txtAngulo.setY(13);
		txtAngulo.setY(840);
		txtAngulo.setFont(Font.font("Verdana", FontWeight.BOLD, 10));


		TilePane animationControlGrid = new TilePane(Orientation.VERTICAL);
		animationControlGrid.setTileAlignment(Pos.CENTER_LEFT);
		animationControlGrid.setPrefRows(2);

		playText = new Text("Start");
		playText.setFont(Font.font("Verdana", 8));
		animationControlGrid.getChildren().add(playText);

		playImage = new ImageView("images/play.png");
		playImage.setFocusTraversable(Boolean.TRUE);
		playImage.setOnMouseClicked(e -> fractal.iniciarDesenho());
		playImage.setOnMouseEntered(e -> {
			DropShadow effect = new DropShadow();
			effect.setRadius(50);
			effect.setSpread(0.65);
			effect.setColor(Color.web("#A1FF6E"));
			playImage.setEffect(effect);
		});
		playImage.setOnMouseExited(e -> playImage.setEffect(null));

		animationControlGrid.getChildren().add(playImage);



		pauseCaption = new Text("Pause");
		pauseCaption.setY(posBotoes + 50);
		pauseCaption.setX(885);
		pauseCaption.setFont(Font.font("Verdana", 8));
		animationControlGrid.getChildren().add(pauseCaption);

		pauseImage = new ImageView(new Image("images/pause.png"));
		pauseImage.setFocusTraversable(Boolean.TRUE);
		pauseImage.setOnMouseClicked(e -> {
			fractal.pararDesenho();
		});
		pauseImage.setOnMouseEntered(e -> {
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(50);
			dropShadow.setSpread(0.65);
			dropShadow.setColor(Color.web("#FF2828"));
		});
		pauseImage.setOnMouseExited(e -> pauseImage.setEffect(null));
		animationControlGrid.getChildren().add(pauseImage);

		restartCaption = new Text("Restart");
		restartCaption.setY(posBotoes + 50);
		restartCaption.setX(925);
		restartCaption.setFont(Font.font("Verdana", 8));
		restartCaption.setFill(Color.web("#222"));
		restartCaption.setOnMouseClicked(e -> fractal.reiniciarDesenho());
		animationControlGrid.getChildren().add(restartCaption);

		restartImage = new ImageView("images/restart.png");
		restartImage.setFocusTraversable(Boolean.TRUE);
		restartImage.setY(posBotoes + 21);
		restartImage.setX(930);
		restartImage.setOnMouseClicked(e -> {
			fractal.reiniciarDesenho();
		});
		DropShadow effect = new DropShadow(50, Color.web("#7197FF"));
		effect.setSpread(0.65);
		restartImage.setOnMouseEntered(e -> restartImage.setEffect(effect));
		restartImage.setOnMouseExited(e -> restartImage.setEffect(null));
		animationControlGrid.getChildren().add(restartImage);

		this.getChildren().addAll(
				txtAngulo,
				anguloSlider,
				iterationSpinnerCaption,
				iterationSpinner,
				animationControlGrid);
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