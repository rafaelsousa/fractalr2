package ca.renardnumerique.fractalr2;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class DragDrop {
    
    public Node target;
    private double maxX = 200;
    private double maxY = 200;
    private double iniX = 0;
    private double iniY = 0;
    private double startX = 0;
    private double startY = 0;
    private double desX;
    private double desY;

    private double centroXtarget;
    private double centroYtarget;
    
    public double ty = 0;
    public double tx = 0;

    public DragDrop(Node target){
        target.setOnMousePressed(e ->  {
            startX = e.getSceneX()-target.getTranslateX();
            startY = e.getSceneY()-target.getTranslateY();
            iniX=target.getTranslateX();
            iniY=target.getTranslateY();
            target.toFront();
        });

        target.setOnMouseReleased(e -> {
			target.setTranslateX(0.0);
        	target.setTranslateY(0.0);
        	iniX = 0.0;
            iniY = 0.0;
            startX = 0.0;
            startY = 0.0;
            centroXtarget=0.0;
            centroYtarget=0.0;
        });
        target.setOnMouseDragged(e->{
            tx = e.getSceneX()-startX;
            if(tx > maxX-target.getBoundsInLocal().getWidth()) {
                tx = maxX-target.getBoundsInLocal().getWidth();
            }
            target.setTranslateX(tx);
            ty = e.getSceneY()-startY;
            if(ty > maxY-target.getBoundsInLocal().getHeight()) {
                ty = maxY-target.getBoundsInLocal().getHeight();
            }
            target.setTranslateY(ty);
            
            // to make available information to eventual actions.
            desX = target.getTranslateX() + iniX + target.getBoundsInLocal().getMinX();
            desY = target.getTranslateY() + iniY + target.getBoundsInLocal().getMinY();
            centroXtarget = desX+(target.getBoundsInLocal().getWidth()/2);
            centroYtarget = desY+(target.getBoundsInLocal().getHeight()/2);
            //onChange(this);
        });
    }
    //function to be overriden
    public void onChange(Consumer<DragDrop> arrastaSolta){
        arrastaSolta.accept(this);
    }
    public void setOnSoltar(Consumer<DragDrop> arrastaSolta){
        arrastaSolta.accept(this);
    }
	
	/*
		Wrapping the question "is in?" passing question area coordinates.
		Partial area accepting logic can be added.
	*/
    public Boolean estaEm(Rectangle nd){
        //println("contains {nd.boundsInLocal.contains(centroXtarget,centroYtarget)} centro - target: X:{centroXtarget} Y:{centroYtarget}");
        return nd.getBoundsInLocal().contains(centroXtarget,centroYtarget);
	}

    public Node getTarget() {
        return target;
    }

    public void setTarget(Group target) {
        this.target = target;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getIniX() {
        return iniX;
    }

    public void setIniX(double iniX) {
        this.iniX = iniX;
    }

    public double getIniY() {
        return iniY;
    }

    public void setIniY(double iniY) {
        this.iniY = iniY;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getDesX() {
        return desX;
    }

    public void setDesX(double desX) {
        this.desX = desX;
    }

    public double getDesY() {
        return desY;
    }

    public void setDesY(double desY) {
        this.desY = desY;
    }

    public double getCentroXtarget() {
        return centroXtarget;
    }

    public void setCentroXtarget(double centroXtarget) {
        this.centroXtarget = centroXtarget;
    }

    public double getCentroYtarget() {
        return centroYtarget;
    }

    public void setCentroYtarget(double centroYtarget) {
        this.centroYtarget = centroYtarget;
    }

    public double getTy() {
        return ty;
    }

    public void setTy(double ty) {
        this.ty = ty;
    }

    public double getTx() {
        return tx;
    }

    public void setTx(double tx) {
        this.tx = tx;
    }
}