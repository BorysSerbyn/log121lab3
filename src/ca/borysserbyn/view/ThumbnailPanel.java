package ca.borysserbyn.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.Thumbnail;

public class ThumbnailPanel extends JPanel implements Observer {
    private JLabel imageLabel;
    private int imageWidth = 325;
    private int imageHeight = 375;
    

    public ThumbnailPanel() {
        super(new BorderLayout());
        initialize();

    }

    public void initialize() {

        //récupère instance de l'image
        imageLabel = new JLabel();
        BufferedImage image = Thumbnail.getSingleton().getImage();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        add(imageLabel, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void update(Observable arg0, Object arg1) {
        /*
         * TODO: si zoom et translate view sont independant, faire ca: image =
         * ((Originator) arg0).getImageEdit().getZoomedImage();
         */
        Thumbnail thumbnail = (Thumbnail) arg1;
        BufferedImage image = thumbnail.getImage();
        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(imageWidth, imageHeight,
        Image.SCALE_SMOOTH)));
    }

}