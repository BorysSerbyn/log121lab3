package ca.borysserbyn.view;

import ca.borysserbyn.controller.TranslateDownCommand;
import ca.borysserbyn.controller.TranslateLeftCommand;
import ca.borysserbyn.controller.TranslateRightCommand;
import ca.borysserbyn.controller.TranslateUpCommand;
import ca.borysserbyn.model.ImageEdit;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class TranslationPanel extends JPanel implements Observer {
    private JButton leftButton = new JButton("left");
    private JButton rightButton = new JButton("right");
    private JButton upButton = new JButton("up");
    private JButton downButton = new JButton("down");
    private JToolBar toolBar = new JToolBar();
    private JLabel imageLabel;

    private int imageWidth = 325;
    private int imageHeight = 375;

    public TranslationPanel() {
        super(new BorderLayout());
        initialize();
    }

    public void update(Observable arg0, Object arg1) {
        BufferedImage image = ImageEdit.getSingleton().getEditedImage();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
    }

    public void initialize(){
        imageLabel = new JLabel();
        BufferedImage image = ImageEdit.getSingleton().getZoomedImage();
        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);

        leftButton.addActionListener(new TranslateLeftCommand());
        rightButton.addActionListener(new TranslateRightCommand());
        upButton.addActionListener(new TranslateUpCommand());
        downButton.addActionListener(new TranslateDownCommand());

        toolBar.add(leftButton);
        toolBar.add(rightButton);
        toolBar.add(downButton);
        toolBar.add(upButton);

        add(toolBar, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
}
