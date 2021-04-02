package ca.borysserbyn.view;

import ca.borysserbyn.controller.TranslateController;
import ca.borysserbyn.controller.TranslateDownCommand;
import ca.borysserbyn.controller.TranslateLeftCommand;
import ca.borysserbyn.controller.TranslateRightCommand;
import ca.borysserbyn.controller.TranslateUpCommand;
import ca.borysserbyn.model.Thumbnail;
import ca.borysserbyn.model.memento.Originator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class TranslationPanel extends JPanel implements Observer {
    private Thumbnail thumbnail;
    private TranslateController translateController = new TranslateController(this);

    private JButton leftButton = new JButton("left");
    private JButton rightButton = new JButton("right");
    private JButton upButton = new JButton("up");
    private JButton downButton = new JButton("down");
    private JToolBar toolBar = new JToolBar();

    private JLabel imageLabel;

    public TranslationPanel(Thumbnail thumbnail) {
        super(new BorderLayout());
        this.thumbnail = thumbnail;
        initialize();
    }

    public void update(Observable arg0, Object arg1) {
        BufferedImage image = ((Originator) arg0).getImageEdit().getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }

    public void initialize(){
        BufferedImage image = Originator.getSingleton().getImageEdit().getImage();
        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setPreferredSize(new Dimension(300, 250));

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

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

}
