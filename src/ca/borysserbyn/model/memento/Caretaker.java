package ca.borysserbyn.model.memento;

import java.util.ArrayList;

public class Caretaker {

    //Liste des memento contenant des état d'image édité sauvegardé
    ArrayList<Memento> savedImage;
    
    public Caretaker(){ savedImage = new ArrayList<Memento>(); }

    //Ajoute un memento à la liste
    public void addMemento(Memento m){ savedImage.add(m); }

    //Accesseur d'un memento dans la liste
    public Memento getMemento(int index){ return savedImage.get(index); }

    //---------------------TEST CARETAKER--------------------------
    public String printArray(){

        String array = null;

        for (Memento memento : savedImage) {
            
            array += memento.toString() + " ";
        }

        return array;
    }

}
