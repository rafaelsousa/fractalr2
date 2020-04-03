package ca.renardnumerique.fractalr2;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import lombok.Data;


@Data
public class DesktopLayout extends Group  {
   
   private static final int INICIO_DESENHO_Y = 140;
   private ImageView fundoSistema;
   {
	   fundoSistema = new ImageView(new Image("images/bg_sistema.png"));
	   fundoSistema.setFocusTraversable(Boolean.TRUE);
	   fundoSistema.setY(0.0);
	   fundoSistema.setX(0.0);
   };
  
   private Rectangle areaDesenho = new Rectangle();
    // sera usado pela classe Lapis, para limites de desenho.
   {
        areaDesenho.setX(236); 
        areaDesenho.setY(INICIO_DESENHO_Y);        
        areaDesenho.setWidth(500);  
        areaDesenho.setHeight(500);
        areaDesenho.setCursor(Cursor.DEFAULT);
        areaDesenho.setFill(Color.web("#FFF"));
    };
    private ImageView imgAnterior = new ImageView();
    {
	    imgAnterior.setOpacity(0.1);
	    imgAnterior.setFocusTraversable(Boolean.TRUE);
	    imgAnterior.setImage( new Image("images/anterior.png"));
	    imgAnterior.setX(166); 
	    imgAnterior.setY(INICIO_DESENHO_Y+200);
    }
		
    private ImageView imgProximo = new ImageView();
    {
    	imgProximo.setOpacity(0.1);
    	imgProximo.setFocusTraversable(Boolean.TRUE);
    	imgProximo.setImage(new Image("images/proximo.png"));
    	imgProximo.setY(INICIO_DESENHO_Y+200);
		imgProximo.setX(930); 
    };
    
    private Rectangle proximo = new Rectangle();
    // sera usado pela classe Lapis, para limites de desenho.
    {
        proximo.setX(940); 
        proximo.setY(INICIO_DESENHO_Y);
        proximo.setArcWidth(10);  
        proximo.setArcHeight(10);
        proximo.setWidth(20);  
        proximo.setHeight(575);
        proximo.setOpacity(0.1);
        proximo.setCursor(Cursor.HAND);
        
        LinearGradient proximoLinearGradient 
        	= new LinearGradient(0.0, 
        						0.0, 
        						0.0, 
        						0.05,
        						Boolean.TRUE, 
        						CycleMethod.NO_CYCLE,
        						new Stop(0.0,Color.web("#999")), 
        						new Stop(1.0,Color.web("green")));

        proximo.setFill(proximoLinearGradient);
        proximo.setStroke(Color.web("#888"));
        proximo.setOnMouseEntered((e)->{
        	this.proximo.setOpacity(0.2);
  	        this.proximo.setOpacity(0.8);
        	}
        );
	  	
	  	proximo.setOnMouseClicked(e->{
  	    	//ControlPanel.proxima();
  	   	});  	    
  	    proximo.setOnMouseExited(e->{
  	        this.proximo.setOpacity(0.1);  	        
  	    });
    };
    public Rectangle anterior = new Rectangle();
    {
        anterior.setX(166);
        anterior.setY(INICIO_DESENHO_Y);
        anterior.setOpacity(0.1);
        anterior.setArcWidth(10);  
        anterior.setArcHeight(10);
        anterior.setWidth(20);  
        anterior.setHeight(575);
        anterior.setCursor(Cursor.HAND);
		anterior.setFill(Color.web("#FFF"));
		anterior.setOnMouseEntered(e -> {
  	        imgAnterior.setOpacity(0.5);
	        this.anterior.setOpacity(0.2);
	    });
	  	anterior.setOnMouseClicked(e->{
	        //ControlPanel.anterior();
	    });
	    anterior.setOnMouseExited(e->{
  	        imgAnterior.setOpacity(0.1);
	        this.anterior.setOpacity(0.1);
	    });	          
	    anterior.setStroke(Color.web("#888"));
    };
    
    //logomarga do sistema
   
   private ImageView logo = new ImageView();
   {
           logo.setFocusTraversable(true);
           logo.setImage(new Image("images/logo.png"));
           logo.setY(4);        
           logo.setX(10);        
   };
 
   
   public DesktopLayout() {
	   this.getChildren()
	   	.addAll(fundoSistema,logo,areaDesenho);
   }

}
