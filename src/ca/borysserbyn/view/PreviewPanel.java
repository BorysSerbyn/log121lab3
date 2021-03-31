package ca.borysserbyn.view;

import ca.borysserbyn.model.memento.Originator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class PreviewPanel extends JPanel implements Observer {
    private BufferedImage image;
    private JLabel imageLabel;

    public PreviewPanel(BufferedImage image) {
        super();
        this.image = image;
        initialize();
    }

    public void initialize(){
        imageLabel = new JLabel(new ImageIcon(image));
        add(imageLabel);
        imageLabel.setPreferredSize(new Dimension(300, 250));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    }

    public void update(Observable arg0, Object arg1) {
        image = ((Originator) arg0).getImageEdit().getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }
}
