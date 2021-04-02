package ca.borysserbyn.view;

import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.borysserbyn.model.ImageEdit;

public class ZoomPanel extends JPanel implements Observer {

    private ImageEdit imageZoom;
    private JLabel imageLabel;
    private JButton undoZoom;
    private JButton redoZoom;

    public ZoomPanel(ImageEdit imageZoom) {
        super(new BorderLayout());
        this.imageZoom = imageZoom;
        this.undoZoom = new JButton("undo");
        this.redoZoom = new JButton("redo");
        initialize();

    }

    public void initialize() {
        BufferedImage image = Originator.getSingleton().getImageEdit().getImage();
        imageLabel = new JLabel(new ImageIcon(imageZoom));
        add(imageLabel, BorderLayout.NORTH);
        add(undoZoom, BorderLayout.SOUTH);
        add(redoZoom, BorderLayout.SOUTH);
        imageLabel.setPreferredSize(new Dimension(300, 250));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void update(Observable arg0, Object arg1) {
        /*
         * TODO: si zoom et translate view sont independant, faire ca: image =
         * ((Originator) arg0).getImageEdit().getZoomedImage();
         */
        imageZoom = ((Originator) arg0).getImageEdit().getImage();
        imageLabel.setIcon(new ImageIcon(imageZoom));
    }

}
