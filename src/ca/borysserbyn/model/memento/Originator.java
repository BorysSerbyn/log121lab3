package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

import java.util.Observable;

public class Originator {
    private ImageEdit imageEdit;

    public synchronized ImageEdit getImageEdit(){
        return imageEdit;
    }

    public void setImageEdit(ImageEdit imageEdit){
        this.imageEdit = imageEdit;
    }
}
