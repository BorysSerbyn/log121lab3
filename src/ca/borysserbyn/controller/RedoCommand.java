package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ca.borysserbyn.model.ImageEdit;

public class RedoCommand implements ActionListener{
    private ImageEdit imageEdit = ImageEdit.getSingleton();
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(imageEdit == null){
            JOptionPane.showMessageDialog(null,
                    "Charger une image avant de tenter de la copier",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }else{

            imageEdit.redoZoom();

        }
          
    }
    
}