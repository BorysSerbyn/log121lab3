package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ca.borysserbyn.FileUtils;
import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.Thumbnail;
import ca.borysserbyn.model.memento.Originator;

import static ca.borysserbyn.FileUtils.savedWipsDir;

public class ChargerCommand implements ActionListener{
    private ImageEdit imageEdit = ImageEdit.getSingleton();

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(savedWipsDir);
        fileChooser.setDialogTitle("Selectionner une image ou une sauvegarde precedente");
        fileChooser.setAcceptAllFileFilterUsed(true);

        // Creer un filtre
        FileNameExtensionFilter filtre = new FileNameExtensionFilter(".jpg", "jpg");
        //FileNameExtensionFilter filtreFichier = new FileNameExtensionFilter("Fichier", ".");
        fileChooser.addChoosableFileFilter(filtre);
        //fileChooser.addChoosableFileFilter(filtreFichier);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            try {
                Path filePath = Paths.get(selectedFile.getAbsolutePath());
                String contentType = Files.probeContentType(filePath);

                imageEdit.resetOriginator();
                if (contentType != null && contentType.equals("image/jpeg")) {
                    BufferedImage image = ImageIO.read(selectedFile);
                    ImageEdit.getSingleton().loadImageEdit(new ImageEdit(image));
                    imageEdit.createEditedImage();
                    Thumbnail.getSingleton().setImage(image);
                } else {
                    FileInputStream fileIn = new FileInputStream(selectedFile);
                    FileUtils.readWipFile(fileIn);
                }

            } catch (IOException e1) {

                e1.printStackTrace();
            }

        }
    }
}