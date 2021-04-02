package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ca.borysserbyn.model.ImageEdit;

public class TranslateDownCommand implements ActionListener{
    private ImageEdit imageEdit = ImageEdit.getSingleton();
    private int magnitude = 20;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(imageEdit == null){
            JOptionPane.showMessageDialog(null,
                    "Charger une image",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            imageEdit.translate(0, magnitude);
        }

    }
    
}
