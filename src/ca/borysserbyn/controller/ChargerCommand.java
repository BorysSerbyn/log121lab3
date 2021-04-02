package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class ChargerCommand implements ActionListener{
    private ImageEdit imageEdit = ImageEdit.getSingleton();
    private int magnitude = 20;

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Selectionner une image");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Cr�er un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".jpg", "jpg");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
                BufferedImage image;
                try {
                    image = ImageIO.read(selectedFile);
                    ImageEdit.getSingleton().setImageEdit(new ImageEdit(image));
                    imageEdit.createEditedImage();
                } catch (IOException e1) {
                    
                    e1.printStackTrace();
                }
                
			}
    }
    
}