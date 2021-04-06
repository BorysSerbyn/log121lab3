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

public class ZoomPanel extends JPanel implements Observer {
    private JLabel imageLabel;
    private int imageWidth = 325;
    private int imageHeight = 375;

    public ZoomPanel() {
        super(new BorderLayout());
        initialize();
    }


    @Override
    public void update(Observable arg0, Object arg1) {
        BufferedImage image = ImageEdit.getSingleton().getZoomedImage();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
    }

    /**
     * Initialisation des composantes du panneau de zoom
     */
    public void initialize() {
        //récupère instance de l'image
        imageLabel = new JLabel();
        BufferedImage image = ImageEdit.getSingleton().getZoomedImage();
        imageHeight = (int)(((double) imageWidth/image.getWidth()) * image.getHeight());
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);

        //ajout d'un mousewheel listener au panneau courant
        this.addMouseWheelListener(new ZoomInCommand());

        //ajout de l'image et du sous panneau des bouttons au panneau zoom
        add(imageLabel, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }

}
