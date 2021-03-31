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
        this.thumbnail = thumbnail;
    }

    public ImageEdit(BufferedImage thumbnail, int translateX, int translateY, float zoomPercentage) {
        this.thumbnail = thumbnail;
        this.translateX = translateX;
        this.translateY = translateY;
        this.zoomPercentage = zoomPercentage;
    }

    public void createEditedImage(){
        /*int width = (int) (thumbnail.getWidth() * zoomPercentage);
        int height = (int) (thumbnail.getHeight() * zoomPercentage);*/
        image = thumbnail.getSubimage(translateX, translateY, zoomRect.width-20, zoomRect.height-20);
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

    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }

    public void setZoomPercentage(float zoomPercentage) {
        this.zoomPercentage = zoomPercentage;
    }
}
