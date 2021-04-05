package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

import java.util.Observable;
import java.util.Observer;

public class Originator implements Observer {


    private ImageEdit imageEdit;
    private Caretaker caretaker = new Caretaker();

    public ImageEdit getImageEdit() {
        return imageEdit;
    }

    //Met la valeur de l'imageEdit dnas l'image à la nouvelle valeur
    public void setImageEdit(ImageEdit newImageEdit) {
        this.imageEdit = newImageEdit;
    }

    //Crée un nouveau memento avec l'image courante
    public Memento storeInMemento() {

        return new Memento(imageEdit.clone());
    }

    //Accède à l'imageEdit à partir du caretaker à l'indice precisé
    public ImageEdit restoreFromMemento(int imageIndex) {
        return caretaker.getMemento(imageIndex).getSavedImage();
    }

    //Ajoute l'imageEdit courante à la liste de memento
    public void addToCaretaker() {
        caretaker.addMemento(storeInMemento());
    }

    //Met la valeur de l'imageEdit dans l'image à la nouvelle valeur
    @Override
    public void update(Observable o, Object arg) {
        addToCaretaker();
    }


    public String printCaretaker() {


        return caretaker.toString();
    }


}
