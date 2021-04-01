package ca.borysserbyn.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEdit implements Cloneable{
    private BufferedImage thumbnail;
    private BufferedImage image;
    private int translateX = 0;
    private int translateY = 0;
    private float zoomPercentage = 1;
    private Rectangle zoomRect = new Rectangle(300, 250);

    @Override
    public ImageEdit clone(){
        return new ImageEdit(thumbnail, translateX, translateY, zoomPercentage);
    }

    public ImageEdit(BufferedImage thumbnail) {
        this.image = thumbnail;
        this.thumbnail = thumbnail;
    }

    public ImageEdit(BufferedImage thumbnail, int translateX, int translateY, float zoomPercentage) {
        this.image = thumbnail;
        this.thumbnail = thumbnail;
        this.translateX = translateX;
        this.translateY = translateY;
        this.zoomPercentage = zoomPercentage;
    }

    public void createEditedImage(){
        image = thumbnail.getSubimage(translateX, translateY, zoomRect.width-20, zoomRect.height-20);
    }

    public BufferedImage createZoomedImage(){
        return thumbnail.getSubimage(0, 0, zoomRect.width-20, zoomRect.height-20);
    }

    public BufferedImage getImage() {
        return image;
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
        if(translateX+delta+zoomRect.width > image.getWidth()){
            return;
        }
        this.translateX = translateX + delta;
    }

    public void incrementY(int delta) {
        if(translateY+delta < 0){
            return;
        }
        if(translateY+delta+zoomRect.height > image.getHeight()){
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
