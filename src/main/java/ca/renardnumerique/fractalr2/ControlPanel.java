package ca.renardnumerique.fractalr2;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControlPanel extends Group {

	private Spinner<Integer> spinner = new Spinner<>();
	{
		spinner.setTranslateY(68);
		spinner.setTranslateX(890);
	}

	private SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20,
			1);
	{
		spinner.setValueFactory(spinnerValueFactory);
	}

	private Integer angulo = 90;
	private Integer iteracoes = 0;
	private Integer posBotoes = 75;

	private ImageView limpar = new ImageView();
	{
		limpar.setFocusTraversable(Boolean.TRUE);
		limpar.setImage(new Image("images/limpar.png"));
		limpar.setY(5);
		limpar.setX(790);
	};

	private Rectangle designRetangulo = new Rectangle();
	{
		designRetangulo.setY(-2);
		designRetangulo.setWidth(30);
		designRetangulo.setX(785);
		designRetangulo.setHeight(29);
		designRetangulo.setArcWidth(10);
		designRetangulo.setCursor(Cursor.HAND);
		designRetangulo.setArcHeight(10);
		designRetangulo.setFill(Color.web("#aaa"));
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
//                	   delete FormulaPanel.instanciaAtual.botoes;
//                	   FormulaPanel.instanciaAtual.redesenhaBarra();
//                	   for(t in MainClass.instanciaAtual.transformacoes){
//     		    		    if(t instanceof TransformationPanel){
//     		    		        var transformacao : TransformationPanel = t as TransformationPanel;
//	      		    			delete transformacao.botoes;
//	      		    			transformacao.redesenhaBarra();
//	      		    			transformacao.resetarBarra();
//	      		    			
//     		    		    }      		    		    
//     		    		}
//                	   MainClass.instanciaAtual.pencil.clear();
//             		   Fractal.limpaCanvas();
		});
		designRetangulo.setStroke(Color.web("#f8f8f8"));
	}

	private Text titulo = new Text("Iteration");
	{
		titulo.setY(81);
		titulo.setX(840);
		titulo.setFont(Font.font("Verdana", 10));
	}

	public Slider anguloSlider = new Slider();
	{
		anguloSlider.setTranslateX(835);
		anguloSlider.setTranslateY(15);
		anguloSlider.setMin(0);
		anguloSlider.setMax(359);
		anguloSlider.setMaxWidth(120);
		anguloSlider.setValue(angulo);
	}
	public Text txtAngulo = new Text("Turn Angle: {angulo}");
	{
		txtAngulo.setY(13);
		txtAngulo.setY(840);
		txtAngulo.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
	}
	public Rectangle contorno = new Rectangle();
	{
		contorno.setY(0);
		contorno.setX(830);
		contorno.setArcWidth(10);
		contorno.setArcHeight(10);
		contorno.setWidth(130);
		contorno.setHeight(134);
		contorno.setFill(Color.web("#eee"));
		contorno.setStroke(Color.web("#666"));
	};

	private ImageView imgPlay = new ImageView();
	{
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
	}

	private Text ttPlay = new Text("Start");
	{
		// content: bind if(imgPlay.image.url.contains("play")) then "Start" else
		// "Suspend";
		ttPlay.setY(posBotoes + 50);
		ttPlay.setX(840);
		ttPlay.setFont(Font.font("Verdana", 8));
	}

	private Text ttStop = new Text("Pause");
	{
		ttStop.setY(posBotoes + 50);
		ttStop.setX(885);
		ttStop.setFont(Font.font("Verdana", 8));
	}

	private ImageView imgStop = new ImageView();
	{
		imgStop.setFocusTraversable(Boolean.TRUE);
		imgStop.setImage(new Image("images/pause.png"));
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
		imgStop.setOnMouseExited(e -> {
			imgStop.setEffect(null);
		});
	}

	private Text ttReinicio = new Text("Restart");
	{
		ttReinicio.setY(posBotoes + 50);
		ttReinicio.setX(925);
		ttReinicio.setFont(Font.font("Verdana", 8));
		ttReinicio.setFill(Color.web("#222"));

		ttReinicio.setOnMouseClicked(e -> {
			// Fractal.reiniciarDesenho();
		});
	}

	private ImageView imgReinicio = new ImageView();
	{
		imgReinicio.setFocusTraversable(Boolean.TRUE);
		imgReinicio.setImage(new Image("images/restart.png"));
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

	public ControlPanel() {
		this.getChildren().addAll(designRetangulo, limpar, contorno, anguloSlider, txtAngulo, titulo, imgPlay, ttPlay,
				imgStop, ttStop, imgReinicio, ttReinicio, spinner);
	}
}

//public static function proxima() : Void {
//	MainClass.instanciaAtual.pnlControle.iteracoes++;
//	Fractal.reiniciarDesenho();
//}
//public static function anterior() : Void {
//    if(MainClass.instanciaAtual.pnlControle.iteracoes>0){
//		MainClass.instanciaAtual.pnlControle.iteracoes--;
//		Fractal.reiniciarDesenho();
//    }
//}