package ca.borysserbyn.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ca.borysserbyn.controller.ZoomInCommand;
import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class ThumbnailPanel extends JPanel implements Observer {
    private JLabel imageLabel;
    

    public ThumbnailPanel() {
        super(new BorderLayout());
        initialize();

    }

    public void initialize() {
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel = new JLabel(new ImageIcon(image));
        this.addMouseWheelListener(new ZoomInCommand());
        add(imageLabel, BorderLayout.NORTH);
        //imageLabel.setPreferredSize(new Dimension(300, 250));

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