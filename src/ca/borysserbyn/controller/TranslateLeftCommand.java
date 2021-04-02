package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class TranslateLeftCommand implements ActionListener{
    private ImageEdit oldEdit = Originator.getSingleton().getImageEdit();
    private int magnitude = 20;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(oldEdit == null){
            JOptionPane.showMessageDialog(null,
                    "Charger une image",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            oldEdit.translate(-magnitude, 0);
        }
    }
    
}