package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ca.borysserbyn.FileUtils;
import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class SaveCommand implements ActionListener{
    private ImageEdit imageEdit = ImageEdit.getSingleton();
    private Originator originator;

    @Override
    public void actionPerformed(ActionEvent e) {
        FileUtils.writeWithFileChooser(imageEdit);
    }
    
}