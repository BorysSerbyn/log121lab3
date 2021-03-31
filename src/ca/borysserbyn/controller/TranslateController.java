package ca.borysserbyn.controller;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.Thumbnail;
import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.view.TranslationPanel;

public class TranslateController {
    private TranslationPanel transPanel;
    private int magnitude = 20;

    public TranslateController(TranslationPanel transPanel){
        this.transPanel = transPanel;
    }


   public void translateRight(){
        translate(magnitude, 0);
    }

    public void translateLeft(){
        translate(-magnitude, 0);
    }

    public void translateUp(){
        translate(0, -magnitude);
    }

    public void translateDown(){
        translate(0, magnitude);
    }

    public void translate(int deltaX, int deltaY){
        ImageEdit oldEdit = Originator.getSingleton().getImageEdit();
        Thumbnail thumbnail = transPanel.getThumbnail();
        ImageEdit newEdit;
        if(oldEdit == null){
            newEdit = new ImageEdit(thumbnail.getImage());
        }else{
            newEdit = oldEdit.clone();
            newEdit.incrementX(deltaX);
            newEdit.incrementY(deltaY);
        }
        newEdit.createEditedImage();
        Originator.getSingleton().setImageEdit(newEdit);
    }

}
