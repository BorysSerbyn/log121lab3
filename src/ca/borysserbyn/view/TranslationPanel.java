package ca.borysserbyn.view;

import ca.borysserbyn.controller.TranslateController;
import ca.borysserbyn.model.Thumbnail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class TranslationPanel extends JPanel implements Observer, AdjustmentListener {
    private BufferedImage thumbnail;
    private ScrollablePicture scrollablePicture;
    private JScrollPane scrollPane;
    private TranslateController translateController = new TranslateController(this);

    public TranslationPanel(BufferedImage thumbnail) {
        super();
        this.thumbnail = thumbnail;
        initialize();
    }

    public void update(Observable arg0, Object arg1) {

        //get zoom percentage \n set scroll pane zoom

        thumbnail = ((Thumbnail) arg0).getImage();
        scrollablePicture.setIcon(new ImageIcon(thumbnail));
    }

    public void initialize(){
        scrollablePicture = new ScrollablePicture(new ImageIcon(thumbnail), 1);
        scrollPane = new JScrollPane(scrollablePicture);
        scrollPane.setPreferredSize(new Dimension(300, 250));
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));

        scrollPane.getVerticalScrollBar().addAdjustmentListener(this);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(this);

        add(scrollPane);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public Point getScrollBarPosition(){
        JViewport viewport = scrollPane.getViewport();
        return viewport.getViewPosition();
    }

    public BufferedImage getThumbnail() {
        return thumbnail;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        translateController.translate();
    }
}
