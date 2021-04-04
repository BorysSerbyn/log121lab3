package ca.borysserbyn.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ca.borysserbyn.controller.CopyCommand;
import ca.borysserbyn.controller.PasteCommand;
import ca.borysserbyn.controller.RedoCommand;
import ca.borysserbyn.controller.UndoCommand;
import ca.borysserbyn.controller.ZoomInCommand;
import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class ZoomPanel extends JPanel implements Observer {
    private JLabel imageLabel;
    private JButton undoZoom, redoZoom, copyEdit, pasteEdit;

    public ZoomPanel() {
        super(new BorderLayout());
        this.undoZoom = new JButton("undo");
        this.redoZoom = new JButton("redo");
        this.copyEdit = new JButton("copy edit");
        this.pasteEdit = new JButton("paste edit");
        initialize();

    }

    /**
     * Initialisation des composantes du panneau de zoom
     */
    public void initialize() {
        //récupère instance de l'image
        BufferedImage image = ImageEdit.getSingleton().getImage();
        JPanel subButtonPanel = new JPanel();
        imageLabel = new JLabel(new ImageIcon(image));

        //ajout d'un mousewheel listener au panneau courant
        this.addMouseWheelListener(new ZoomInCommand());

        //ajout d'un action listener sur tous les bouttons
        undoZoom.addActionListener(new UndoCommand());
        redoZoom.addActionListener(new RedoCommand());
        copyEdit.addActionListener(new CopyCommand());
        pasteEdit.addActionListener(new PasteCommand());
        
        //ajout des bouttons au sous-panneau
        subButtonPanel.add(undoZoom);
        subButtonPanel.add(redoZoom);
        subButtonPanel.add(copyEdit);
        subButtonPanel.add(pasteEdit);

        //ajout de l'image et du sous panneau des bouttons au panneau zoom
        add(imageLabel, BorderLayout.NORTH);
        add(subButtonPanel, BorderLayout.PAGE_END);
        imageLabel.setPreferredSize(new Dimension(300, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        /*
         * TODO: si zoom et translate view sont independant, faire ca: image =
         * ((Originator) arg0).getImageEdit().getZoomedImage();
         */
        BufferedImage image = ImageEdit.getSingleton().getImageZoom();
        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
        Image.SCALE_SMOOTH)));
    }

}
