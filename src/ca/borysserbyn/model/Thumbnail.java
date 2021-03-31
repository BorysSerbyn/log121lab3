package ca.borysserbyn.model;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class Thumbnail extends Observable {
    private BufferedImage image;

    public Thumbnail(BufferedImage image) {
        this.image = image;
    }

    public synchronized BufferedImage getImage() {
        return image;
    }

    public synchronized void setImage(BufferedImage image) {
        this.image = image;
        super.setChanged();
        super.notifyObservers();
    }
}
