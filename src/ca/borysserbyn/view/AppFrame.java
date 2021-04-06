package ca.borysserbyn.view;

import ca.borysserbyn.controller.CopyCommand;
import ca.borysserbyn.controller.PasteCommand;
import ca.borysserbyn.controller.RedoCommand;
import ca.borysserbyn.controller.UndoCommand;
import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.model.Thumbnail;

import javax.swing.*;


import java.awt.*;

public class AppFrame extends JFrame {
    private TranslationPanel translationPanel;
    private ZoomPanel zoomPanel;
    private ThumbnailPanel thumbnailPanel;
    private Thumbnail thumbnail;
    private ImageEdit imageEdit;
    private Originator originator;
    private MenuToolBar menuFenetre;
    private EditToolBar editToolBar;

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

        editToolBar = new EditToolBar();

        add(editToolBar, BorderLayout.PAGE_END);
        add(zoomPanel, BorderLayout.LINE_START);
        add(translationPanel, BorderLayout.CENTER);
        add(thumbnailPanel, BorderLayout.LINE_END);

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
