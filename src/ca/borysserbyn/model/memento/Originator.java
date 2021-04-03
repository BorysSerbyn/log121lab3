package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

import java.util.Observable;
import java.util.Observer;

public class Originator implements Observer{


    private ImageEdit imageEdit;

    public ImageEdit getImageEdit(){
        return imageEdit;
    }

    //Met la valeur de l'imageEdit dnas l'article à la nouvelle valeur
    public void setImageEdit(ImageEdit newImageEdit){
        this.imageEdit = newImageEdit;
    }

    //Crée un nouveau memento avec l'image courante
    public Memento storeInMemento(){

        return new Memento(imageEdit);
    }

    //Accède à l'imageEdit présentement dans le memento
    public ImageEdit restoreFromMemento(Memento memento){

        imageEdit = memento.getSavedImage();

        return imageEdit;

    }

    //Met la valeur de l'imageEdit dnas l'article à la nouvelle valeur
    @Override
    public void update(Observable o, Object arg) {
        
        
        
    }
}
