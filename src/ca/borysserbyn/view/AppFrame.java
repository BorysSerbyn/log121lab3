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

    public static void main(String[] args) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Selectionner une image");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Creer un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".jpg", "jpg");
		    fileChooser.addChoosableFileFilter(filtre);
			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(selectedFile);
                //Garder cette ligne pour tester sans le file chooser donc plus rapidement l'enlever avant de faire le final commit
                //BufferedImage image = ImageIO.read(new File("./images/cool_image.jpg"));
                AppFrame appFrame = new AppFrame(new Thumbnail(image));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
