package ca.borysserbyn.model;

import java.awt.image.BufferedImage;
import java.util.Observable;


@SuppressWarnings( "deprecation" )
public class Thumbnail extends Observable {
    private BufferedImage image;
    private static Thumbnail singleton;

    public Thumbnail(BufferedImage image) {
        this.image = image;
        super.setChanged();
        super.notifyObservers(this);
    }

    public static Thumbnail getSingleton(){
        if(singleton == null){
            singleton = new Thumbnail(new BufferedImage(0,0,1));
        }
        return singleton;
    }

    public static void setSingleton(Thumbnail thumbnail){
        singleton = thumbnail;
    }

    
    public synchronized BufferedImage getImage() {
        return image;
    }

    public synchronized void setImage(BufferedImage image) {
        this.image = image;
        super.setChanged();
        super.notifyObservers(this);
    }
}
