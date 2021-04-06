package ca.borysserbyn;

import ca.borysserbyn.model.ImageEdit;

import javax.swing.*;
import java.io.*;

public abstract class FileUtils {

    private static File savedWipsDir = new File("wip");

    public static void writeWithFileChooser(ImageEdit x) {
        try {
            JFileChooser fileChooser = new JFileChooser(savedWipsDir);
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                FileOutputStream fileOut = new FileOutputStream(fileChooser.getSelectedFile());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                x.writeImageEdit(out);
                out.close();
                fileOut.close();
                System.out.println("Serialized data saved.");
            }
        } catch (Exception e) {
            System.out.println("Cant write object");
            e.printStackTrace();
        }
    }

    public static void readWithFileChooser(FileInputStream fileIn) {
        try {

            ObjectInputStream in = new ObjectInputStream(fileIn);
            ImageEdit.getSingleton().readImageEdit(in);
            in.close();
            fileIn.close();
            System.out.println("Serialized data loaded.");

        } catch (Exception e) {
            System.out.println("Cant read object");
            e.printStackTrace();
        }
    }

}
