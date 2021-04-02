package ca.borysserbyn.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class ZoomPanel extends JPanel implements Observer {
    private JLabel imageLabel;
    private JButton undoZoom;
    private JButton redoZoom;

    public ZoomPanel() {
        super(new BorderLayout());
        this.undoZoom = new JButton("undo");
        this.redoZoom = new JButton("redo");
        initialize();

    }

    public void initialize() {
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel, BorderLayout.NORTH);
        add(undoZoom, BorderLayout.SOUTH);
        add(redoZoom, BorderLayout.SOUTH);
        imageLabel.setPreferredSize(new Dimension(300, 250));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void update(Observable arg0, Object arg1) {
        /*
         * TODO: si zoom et translate view sont independant, faire ca: image =
         * ((Originator) arg0).getImageEdit().getZoomedImage();
         */
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }

}
