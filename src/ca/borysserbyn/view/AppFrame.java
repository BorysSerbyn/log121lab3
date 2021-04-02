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
    private PreviewPanel previewPanel;
    private Thumbnail thumbnail;
    private Originator originator = Originator.getSingleton();
    private ImageEdit imageEdit;

    public AppFrame(Thumbnail thumbnail){
        super();
        this.thumbnail = thumbnail;
        initialize();
    }

    public void initialize(){
        ImageEdit initialEdit = new ImageEdit(thumbnail.getImage());
        initialEdit.createEditedImage();
        Originator.getSingleton().setImageEdit(initialEdit);
        MenuToolBar menuFenetre = new MenuToolBar();
		add(menuFenetre, BorderLayout.NORTH);

        translationPanel = new TranslationPanel(thumbnail);
        previewPanel = new PreviewPanel(thumbnail.getImage());

        //thumbnail.addObserver(translationPanel);
        originator.addObserver(previewPanel);
        originator.addObserver(translationPanel);

        add(previewPanel, BorderLayout.LINE_START);
        add(translationPanel, BorderLayout.LINE_END);


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
