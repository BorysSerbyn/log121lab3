package ca.borysserbyn.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;


import ca.borysserbyn.model.memento.Originator;

import javax.imageio.ImageIO;


@SuppressWarnings( "deprecation" )
public class ImageEdit extends Observable implements Cloneable, Serializable {
    private transient BufferedImage baseImage;
    private transient BufferedImage editedImage;
    private transient BufferedImage zoomedImage;
    private int translateX = 0;
    private int translateY = 0;
    private double zoomPercentage = 100;
    private Rectangle zoomRect;
    private static ImageEdit singleton;
    private static Originator originator;

    public void writeImageEdit(ObjectOutputStream out) throws IOException {
        out.writeObject(this);
        ImageIO.write(baseImage, "png", out);
        ImageIO.write(editedImage, "png", out);
        ImageIO.write(zoomedImage, "png", out);
    }

    public void readImageEdit(ObjectInputStream in) throws  IOException, ClassNotFoundException{
        ImageEdit readImage = (ImageEdit) in.readObject();
        readImage.baseImage = ImageIO.read(in);
        readImage.editedImage = ImageIO.read(in);
        readImage.zoomedImage = ImageIO.read(in);
        Thumbnail.getSingleton().setImage(readImage.baseImage);
        loadImageEdit(readImage);
    }
    
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

    public ImageEdit(BufferedImage editedImage) {

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

    public synchronized Originator getOriginator(){
        return originator;
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
        originator.addToCaretaker();
    }

    public synchronized void createEditedImage(){
        editedImage = baseImage.getSubimage(translateX, translateY, zoomRect.width, zoomRect.height);
        zoomedImage = baseImage.getSubimage(0,0 , zoomRect.width, zoomRect.height);
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

        int newHeight = (int)((zoomPercentage + zoomDirection)/100 * baseImage.getHeight());
        int newWidth = (int)((zoomPercentage + zoomDirection)/100 * baseImage.getWidth());

        int maxWidth = baseImage.getWidth() - translateX;
        int maxHeight = baseImage.getHeight() - translateY;

        if (newWidth <= maxWidth && newHeight <= maxHeight) {
            if(zoomDirection>0){
                int x = (int) ((Math.log(0.1/translateX))/Math.log(zoomDirection));
                int y = (int) ((Math.log(0.1/translateY))/Math.log(zoomDirection));
                translateX += translateX/x;
                translateY += translateY/y;
            }

            zoomPercentage += zoomDirection;
            zoomRect.height = newHeight;
            zoomRect.width = newWidth;
            createEditedImage();

            originator.addToCaretaker();
        }
    }

    public void copyImageEdit(){

    }

    public void undo(){
        originator.undo();
    }

    public void redo(){
        originator.redo();
    }

    public void resetOriginator(){
        originator.clearCaretaker();
    }


}
