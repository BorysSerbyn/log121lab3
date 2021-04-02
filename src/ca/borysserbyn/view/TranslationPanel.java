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

    public TranslationPanel() {
        super(new BorderLayout());
        initialize();
    }

    public void update(Observable arg0, Object arg1) {
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
        Image.SCALE_SMOOTH)));
    }

    public void initialize(){
        BufferedImage image = ImageEdit.getSingleton().getImage();
        imageLabel = new JLabel(new ImageIcon(image));
        //imageLabel.setPreferredSize(new Dimension(300, 250));

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
