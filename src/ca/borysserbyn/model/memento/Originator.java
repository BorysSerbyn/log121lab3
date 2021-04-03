package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

import java.util.Observable;

public class Originator {
    private ImageEdit imageEdit;

    public ImageEdit getImageEdit(){
        return imageEdit;
    }

    public void setImageEdit(ImageEdit imageEdit){
        //add old to caretaker
    }

    @Override
    public void update(Observable o, Object arg) {
        

        
    }
}
