package ca.borysserbyn.controller;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.view.TranslationPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TranslateController {
    private TranslationPanel transPanel;

    public TranslateController(TranslationPanel transPanel){
        this.transPanel = transPanel;
    }

    public void translate(){
        Point position = transPanel.getScrollBarPosition();
        ImageEdit oldEdit = Originator.getSingleton().getImageEdit();
        BufferedImage thumbnail = transPanel.getThumbnail();
        ImageEdit newEdit;
        if(oldEdit == null){
            newEdit = new ImageEdit(thumbnail);
        }else{
            newEdit = oldEdit.clone();
            newEdit.setTranslateX(position.x);
            newEdit.setTranslateY(position.y);
        }
        newEdit.createEditedImage();
        Originator.getSingleton().setImageEdit(newEdit);
    }

}
