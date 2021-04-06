package ca.borysserbyn.model.memento;

import java.util.ArrayList;

public class History {

    //Liste des memento contenant des état d'image édité sauvegardé
    private ArrayList<Memento> savedImages;

    public void setSavedImages(ArrayList<Memento> savedImages) {
        this.savedImages = savedImages;
    }

    public History(){ savedImages = new ArrayList<Memento>(); }

    //Ajoute un memento à la liste
    public void addMemento(Memento m){ savedImages.add(m); }

    //Accesseur d'un memento dans la liste
    public Memento getMemento(int index){ return savedImages.get(index); }

    public ArrayList<Memento> getSavedImages() {
        return savedImages;
    }

    //---------------------TEST CARETAKER--------------------------
    public String printArray(){

        String array = null;

        for (Memento memento : savedImages) {
            array += memento.toString() + "\n";
        }

        return array;
    }

}
