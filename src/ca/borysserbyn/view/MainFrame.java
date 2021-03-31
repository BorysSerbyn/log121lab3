package ca.borysserbyn.view;

import ca.borysserbyn.model.memento.Originator;
import ca.borysserbyn.model.Thumbnail;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private TranslationPanel translationPanel;
    private PreviewPanel previewPanel;
    private Thumbnail thumbnail;
    private Originator originator = Originator.getSingleton();

    public MainFrame(Thumbnail thumbnail){
        super();
        this.thumbnail = thumbnail;
        initialize();
    }

    public void initialize(){
        translationPanel = new TranslationPanel(thumbnail.getImage());
        previewPanel = new PreviewPanel(thumbnail.getImage());

        thumbnail.addObserver(translationPanel);
        originator.addObserver(previewPanel);

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
            MainFrame mainFrame = new MainFrame(new Thumbnail(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
