package ca.borysserbyn.view;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.model.Thumbnail;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AppFrame extends JFrame {
    private TranslationPanel translationPanel;
    private ZoomPanel zoomPanel;
    private ThumbnailPanel thumbnailPanel;
    private Thumbnail thumbnail;
    private ImageEdit imageEdit;
    private Originator originator;

    public AppFrame(Thumbnail thumbnail){
        super();
        this.thumbnail = thumbnail;
        initialize();
    }

    public void initialize(){
        originator = new Originator();
        imageEdit = new ImageEdit(thumbnail.getImage());
        imageEdit.setOriginator(originator);
        ImageEdit.setSingleton(imageEdit);
        originator.setImageEdit(imageEdit);
        imageEdit.createEditedImage();

        MenuToolBar menuFenetre = new MenuToolBar();
		add(menuFenetre, BorderLayout.NORTH);

        translationPanel = new TranslationPanel();
        zoomPanel = new ZoomPanel();
        thumbnailPanel = new ThumbnailPanel();


        imageEdit.addObserver(originator);
        imageEdit.addObserver(zoomPanel);
        imageEdit.addObserver(translationPanel);
        thumbnail.addObserver(thumbnailPanel);

        add(zoomPanel, BorderLayout.LINE_START);
        add(translationPanel, BorderLayout.LINE_END);
        add(thumbnailPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public TranslationPanel getTranslationPanel() {
        return translationPanel;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("./images/cool_image.jpg"));
            AppFrame appFrame = new AppFrame(new Thumbnail(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
