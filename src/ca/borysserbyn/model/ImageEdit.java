package ca.borysserbyn.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

import ca.borysserbyn.model.memento.Originator;

public class ImageEdit extends Observable implements Cloneable{
    private BufferedImage thumbnail;
    private BufferedImage image;
    private int translateX = 0;
    private int translateY = 0;
    private float zoomPercentage = 1;
    private Rectangle zoomRect = new Rectangle(300, 250);
    private static ImageEdit singleton;
    private static Originator originator;

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }

    public static ImageEdit getSingleton(){
        if(singleton == null){
            singleton = new ImageEdit(new BufferedImage(0,0,1));
        }
        return singleton;
    }

    public static void setSingleton(ImageEdit imageEdit){
        singleton = imageEdit;
        originator.setImageEdit(singleton);
    }

    @Override
    public ImageEdit clone(){
        return new ImageEdit(thumbnail, translateX, translateY, zoomPercentage);
    }

    public ImageEdit(BufferedImage image){
        this.image = image;
        thumbnail = image;
    }
    public synchronized BufferedImage getImage(){
        return image;
    }

    public synchronized void setImageEdit(ImageEdit imageEdit){
        this.image = imageEdit.image;
        this.thumbnail = imageEdit.thumbnail;
        this.translateX = imageEdit.translateX;
        this.translateY = imageEdit.translateY;
        this.zoomPercentage = imageEdit.zoomPercentage;

        super.setChanged();
        super.notifyObservers();
    }

    public ImageEdit(BufferedImage thumbnail, int translateX, int translateY, float zoomPercentage) {
        this.image = thumbnail;
        this.thumbnail = thumbnail;
        this.translateX = translateX;
        this.translateY = translateY;
        this.zoomPercentage = zoomPercentage;
    }

    public synchronized void translate(int deltaX, int deltaY) {
        this.incrementX(deltaX);
        this.incrementY(deltaY);
        this.createEditedImage();

        super.setChanged();
        super.notifyObservers();
    }

    public void createEditedImage(){
        image = thumbnail.getSubimage(translateX, translateY, zoomRect.width-20, zoomRect.height-20);
        originator.setImageEdit(this);
    }

    public BufferedImage createZoomedImage(){
        return thumbnail.getSubimage(0, 0, zoomRect.width-20, zoomRect.height-20);
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void incrementX(int delta) {
        if(translateX+delta < 0){
            return;
        }
        if(translateX+delta+zoomRect.width > thumbnail.getWidth()){
            return;
        }
        this.translateX = translateX + delta;
    }

    public void incrementY(int delta) {
        if(translateY+delta < 0){
            return;
        }
        if(translateY+delta+zoomRect.height > thumbnail.getHeight()){
            return;
        }
        this.translateY = translateY + delta;
    }

    public void setZoom(float zoomPercentage) {
        this.zoomPercentage = zoomPercentage;
        zoomRect.height = (int)(zoomPercentage * image.getHeight());
        zoomRect.width = (int)(zoomPercentage * image.getWidth());
    }
}
