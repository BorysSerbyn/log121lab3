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
    private double zoomPercentage = 1;
    private Rectangle zoomRect;
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
        ImageEdit imageEdit = new ImageEdit(thumbnail, translateX, translateY, zoomPercentage);
        imageEdit.zoomRect = (Rectangle) zoomRect.clone();
        return imageEdit;
    }

    public ImageEdit(BufferedImage image){
        this.image = image;
        this.zoomRect = new Rectangle(image.getWidth(), image.getHeight());
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
        this.zoomRect = imageEdit.zoomRect;

        super.setChanged();
        super.notifyObservers();
    }

    public ImageEdit(BufferedImage thumbnail, int translateX, int translateY, double zoomPercentage) {
        this.image = thumbnail;
        this.thumbnail = thumbnail;
        this.translateX = translateX;
        this.translateY = translateY;
        this.zoomPercentage = zoomPercentage;
        
    }

    public void translate(int deltaX, int deltaY) {
        this.incrementX(deltaX);
        this.incrementY(deltaY);
        this.createEditedImage();
    }

    public synchronized void createEditedImage(){
        image = thumbnail.getSubimage(translateX, translateY, zoomRect.width-20, zoomRect.height-20);
        originator.setImageEdit(this);

        super.setChanged();
        super.notifyObservers();
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

    public void setZoom(double zoomPercentage) {
        
        this.zoomPercentage = zoomPercentage;
        double pourcentageHeight = zoomPercentage/100 * image.getHeight();
        double pourcentageWidht = zoomPercentage/100 * image.getWidth();
        //System.out.println("Pourcentage ajouter: " + pourcentageHeight + " " + pourcentageWidht);
        //System.out.println("Dimension precedente: " + image.getHeight() + " " + image.getWidth());
        int newHeight = (int)(zoomRect.height + (zoomPercentage/100 * zoomRect.height));
        int newWidht = (int)(zoomRect.width + (zoomPercentage/100 * zoomRect.width));

        //if (newHeight <= image.getHeight() && newWidht <= image.getWidth()) {
            zoomRect.height = newHeight;
            zoomRect.width = newWidht;
            this.createEditedImage();
            //System.out.println("Nouvelle dimension: " + newHeight + " " + newWidht);
        //}
        
    }
}
