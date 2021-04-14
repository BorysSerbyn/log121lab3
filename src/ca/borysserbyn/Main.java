package ca.borysserbyn;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;

import ca.borysserbyn.model.Thumbnail;
import ca.borysserbyn.view.AppFrame;

import static ca.borysserbyn.FileUtils.savedWipsDir;

public class Main {
    
    public static void main(String[] args) {
        try {
            JFileChooser fileChooser = new JFileChooser(savedWipsDir);
            fileChooser.setDialogTitle("Selectionner une image");
			fileChooser.setAcceptAllFileFilterUsed(false);
            
			// Creer un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".jpg", "jpg");
		    fileChooser.addChoosableFileFilter(filtre);
			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				
				File selectedFile = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(selectedFile);
                AppFrame appFrame = new AppFrame(new Thumbnail(image));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

