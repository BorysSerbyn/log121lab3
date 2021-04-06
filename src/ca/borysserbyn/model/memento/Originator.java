package ca.borysserbyn.model.memento;

import ca.borysserbyn.model.ImageEdit;

public class Originator {

    private ImageEdit imageEdit;
    private History history = new History();
    private int historySize = 0;
    private int imageIndex = 0;

    public ImageEdit getImageEdit() {
        return imageEdit;
    }

    //Met la valeur de l'imageEdit dnas l'image à la nouvelle valeur
    public void setImageEdit(ImageEdit newImageEdit) {
        this.imageEdit = newImageEdit;
    }

    //Supprime l'entierete du ArrayList
    public void clearCaretaker(){
        historySize = 0;
        imageIndex = 0;
        history.getSavedImages().clear();
    }

    public boolean originatorIsEmpty(){
        return history.getSavedImages().isEmpty();
    }

    //Crée un nouveau memento avec l'image courante
    public Memento storeInMemento() {

        return new Memento(imageEdit.clone());
    }

    //Accède à l'imageEdit à partir du caretaker à l'indice precisé
    public ImageEdit restoreFromMemento(int imageIndex) {
        return history.getMemento(imageIndex).getSavedImage();
    }

    //Ajoute l'imageEdit courante à la liste de memento
    public void addToCaretaker() {
        incrementIndice();
        incrementNbSavedImages();
        history.addMemento(storeInMemento());
    }

    /**
     * Méthode contenant le modèle permettant d'effectuer un retour à la dernière instance de l'image zoomé
     */
    public void undo(){
        if (imageIndex >= 1) {
            //Décrémente le nb d'article dans la liste de caretakers
            imageIndex--;
            //Récupérer la dernière image sauvegardé dans le caretaker
            ImageEdit imageToRestore = restoreFromMemento(imageIndex);
            imageEdit.loadImageEdit(imageToRestore);
        }
    }

    /**
     * Méthode contenant le modèle permettant d'effectuer un retour à la dernière instance de l'image zoomé
     */
    public void redo(){
        if((historySize - 1) > imageIndex){
            //Incrémente l'indice de l'image affiché
            imageIndex++;

            //Recupère l'image la plus récente et l'affiche
            ImageEdit imageToRestore = restoreFromMemento(imageIndex);
            imageEdit.loadImageEdit(imageToRestore);

        }
    }

    public String printCaretaker() {
        return history.toString();
    }

    public void incrementNbSavedImages(){
        historySize++;
    }

    public void decrementNbSavedImages(){
        historySize--;
    }

    public void incrementIndice(){
        imageIndex++;
    }

    public void decrementIndice(){
        imageIndex--;
    }


}
