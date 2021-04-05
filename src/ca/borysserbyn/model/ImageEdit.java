package ca.borysserbyn.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

import ca.borysserbyn.model.memento.Originator;


@SuppressWarnings( "deprecation" )
public class ImageEdit extends Observable implements Cloneable{
    private BufferedImage baseImage;
    private BufferedImage editedImage;
    private BufferedImage zoomedImage;
    private int translateX = 0;
    private int translateY = 0;
    private double zoomPercentage = 100;
    private Rectangle zoomRect;
    private static ImageEdit singleton;
    private static Originator originator;
    private int nbSavedImages = 0;
    
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
    public String toString() {
        return "ImageEdit{" +
                "translateX=" + translateX +
                ", translateY=" + translateY +
                ", zoomPercentage=" + zoomPercentage +
                '}';
    }

    @Override
    public ImageEdit clone(){
        ImageEdit imageEdit = new ImageEdit(baseImage, translateX, translateY, zoomPercentage);
        imageEdit.zoomRect = (Rectangle) zoomRect.clone();
        return imageEdit;
    }

    public ImageEdit(BufferedImage editedImage){
        this.editedImage = editedImage;
        this.zoomedImage = editedImage;
        this.zoomRect = new Rectangle(editedImage.getWidth(), editedImage.getHeight());
        baseImage = editedImage;
    }
    public synchronized BufferedImage getEditedImage(){
        return editedImage;
    }

    public synchronized BufferedImage getZoomedImage() {
        return zoomedImage;
    }

    public synchronized void loadImageEdit(ImageEdit imageEdit){
        this.baseImage = imageEdit.baseImage;
        this.translateX = imageEdit.translateX;
        this.translateY = imageEdit.translateY;
        this.zoomPercentage = imageEdit.zoomPercentage;
        this.zoomRect = imageEdit.zoomRect;

        createEditedImage();
    }

    public ImageEdit(BufferedImage baseImage, int translateX, int translateY, double zoomPercentage) {
        this.editedImage = baseImage;
        this.zoomedImage = baseImage;
        this.baseImage = baseImage;
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
        nbSavedImages++;
        editedImage = baseImage.getSubimage(translateX, translateY, zoomRect.width, zoomRect.height);
        zoomedImage = baseImage.getSubimage(0, 0, zoomRect.width, zoomRect.height);

        super.setChanged();
        super.notifyObservers();
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
        if(translateX+delta+zoomRect.width > baseImage.getWidth()){
            return;
        }
        this.translateX = translateX + delta;
    }

    public void incrementY(int delta) {
        if(translateY+delta < 0){
            return;
        }
        if(translateY+delta+zoomRect.height > baseImage.getHeight()){
            return;
        }
        this.translateY = translateY + delta;
    }

    public void zoom(double zoomDirection) {
        zoomDirection = zoomDirection * 2;

        if (zoomDirection + zoomPercentage <= 100) {
            zoomPercentage += zoomDirection;
            zoomRect.height = (int)(zoomPercentage/100 * baseImage.getHeight());
            zoomRect.width = (int)(zoomPercentage/100 * baseImage.getWidth());
            this.createEditedImage();
        }
    }

    /**
     * Methode contenant la logique permettant d'effectuer un retour à la dernière instance de l'image zoomé
     */
    public void undoZoom(){
        if (nbSavedImages >= 1) {
            //Décrémente le nb d'article dans la liste de caretakers
            nbSavedImages--;
            //Récupérer la dernière image sauvegardé dans le caretaker
            ImageEdit imageToRestore = originator.restoreFromMemento(nbSavedImages - 1);
            loadImageEdit(imageToRestore);
        }
    }

    public void copyImageEdit(){

    }
}
