package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

public class Memento {

    //L'image zoomé à sauvegardé dans le memento
    private ImageEdit imageEdit;


    
    //Sauvegarde une nouvelle instance de l'image zoome au memento
    public Memento(ImageEdit imageEditSave){ this.imageEdit = imageEditSave;}

    //Retourne une valeur sauvegardé dans le memento
    public ImageEdit getSavedImage(){ return imageEdit; }

}
