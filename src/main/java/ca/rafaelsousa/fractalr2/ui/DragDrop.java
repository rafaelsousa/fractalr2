package ca.rafaelsousa.fractalr2.ui;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
public class DragDrop {
    
    public var target: Node;
    public-init var maxX = 200;
    public-init var maxY = 200;
    public-init var iniX = 0.0;
    public-init var iniY = 0.0;
    protected var startX = 0.0;
    protected var startY = 0.0;
    public-read var desX:Number ;
    public-read var desY:Number ;
	public-read var centroXtarget:Number;
    public-read var centroYtarget:Number;
    
    public var ty = 0.0;
    public var tx = 0.0;
    init {
        target.onMousePressed = function(e:MouseEvent):Void {
            startX = e.sceneX-target.translateX;
            startY = e.sceneY-target.translateY;
            iniX=target.translateX;
            iniY=target.translateY;
            target.toFront();
            
        }
        target.onMouseReleased = function(e:MouseEvent):Void {
            onSoltar(this);
			target.translateX = 0.0;
        	target.translateY = 0.0;
        	iniX = 0.0;
            iniY = 0.0;
            startX = 0.0;
            startY = 0.0;
            centroXtarget=0.0;
            centroYtarget=0.0;
        }
        target.onMouseDragged = function(e:MouseEvent):Void {
            tx = e.sceneX-startX;
            if(tx > maxX-target.boundsInLocal.width) { tx = maxX-target.boundsInLocal.width; }
            target.translateX = tx;
            ty = e.sceneY-startY;
            if(ty > maxY-target.boundsInLocal.height) { ty = maxY-target.boundsInLocal.height; }
            target.translateY = ty;
            
            // to make available information to eventual actions.
            desX = target.translateX +iniX+ target.boundsInLocal.minX;
            desY = target.translateY +iniY+ target.boundsInLocal.minY;
            centroXtarget = desX+(target.boundsInLocal.width/2);
            centroYtarget = desY+(target.boundsInLocal.height/2);
            onChange(this);
        }
    }
    //function to be overriden
    public var onChange = function(arrastaSolta:DragDrop){}
    public var onSoltar = function(arrastaSolta:DragDrop){}
	
	/*
		Wrapping the question "is in?" passing question area coordinates.
		Partial area accepting logic can be added.
	*/
    public var estaEm = function(nd:Rectangle):Boolean{
        //println("contains {nd.boundsInLocal.contains(centroXtarget,centroYtarget)} centro - target: X:{centroXtarget} Y:{centroYtarget}");
        return nd.boundsInLocal.contains(centroXtarget,centroYtarget)
	}
}