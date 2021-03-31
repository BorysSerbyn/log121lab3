package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

import java.util.Observable;

public class Originator extends Observable {
    private ImageEdit imageEdit = null;
    private static Originator singleton;

    public static Originator getSingleton(){
        if(singleton == null){
            singleton = new Originator();
        }
        return singleton;
    }

    public synchronized ImageEdit getImageEdit(){
        return imageEdit;
    }

    public synchronized void setImageEdit(ImageEdit imageEdit){
        this.imageEdit = imageEdit;
        super.setChanged();
        super.notifyObservers();
    }
}
