package ca.borysserbyn.view;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.model.Thumbnail;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

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
    private MenuToolBar menuFenetre;

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
        Thumbnail.setSingleton(thumbnail);
        originator.setImageEdit(imageEdit);
        imageEdit.createEditedImage();

        menuFenetre = new MenuToolBar();
		add(menuFenetre, BorderLayout.NORTH);

        translationPanel = new TranslationPanel();
        zoomPanel = new ZoomPanel();
        thumbnailPanel = new ThumbnailPanel();

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
}
