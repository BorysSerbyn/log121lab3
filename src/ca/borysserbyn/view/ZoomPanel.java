package ca.borysserbyn.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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

    public void initialize() {
        BufferedImage image = ImageEdit.getSingleton().getImage();
        JPanel subButtonPanel = new JPanel();
        imageLabel = new JLabel(new ImageIcon(image));
        this.addMouseWheelListener(new ZoomInCommand());

        add(imageLabel, BorderLayout.NORTH);
        subButtonPanel.add(undoZoom);
        subButtonPanel.add(redoZoom);
        subButtonPanel.add(copyEdit);
        subButtonPanel.add(pasteEdit);

        add(subButtonPanel, BorderLayout.PAGE_END);
        imageLabel.setPreferredSize(new Dimension(300, 250));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void update(Observable arg0, Object arg1) {
        /*
         * TODO: si zoom et translate view sont independant, faire ca: image =
         * ((Originator) arg0).getImageEdit().getZoomedImage();
         */
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
        Image.SCALE_SMOOTH)));
    }

}
