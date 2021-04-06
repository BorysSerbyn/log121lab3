package ca.borysserbyn.view;

import ca.borysserbyn.controller.CopyCommand;
import ca.borysserbyn.controller.PasteCommand;
import ca.borysserbyn.controller.RedoCommand;
import ca.borysserbyn.controller.UndoCommand;

import javax.swing.*;
import java.awt.*;

public class EditToolBar extends JToolBar {
    private JButton undoZoom, redoZoom, copyEdit, pasteEdit;

    public EditToolBar(){
        super();
        initialize();
    }

    public void initialize(){
        //initialization des boutons
        this.undoZoom = new JButton("undo");
        this.redoZoom = new JButton("redo");
        this.copyEdit = new JButton("copy edit");
        this.pasteEdit = new JButton("paste edit");

        //ajout d'un action listener sur tous les bouttons
        undoZoom.addActionListener(new UndoCommand());
        redoZoom.addActionListener(new RedoCommand());
        copyEdit.addActionListener(new CopyCommand());
        pasteEdit.addActionListener(new PasteCommand());

        //ajout des bouttons au sous-panneau
        add(undoZoom);
        add(redoZoom);
        add(copyEdit);
        add(pasteEdit);
    }

}
