package ca.borysserbyn.controller;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import ca.borysserbyn.model.ImageEdit;

public class ZoomInCommand implements MouseWheelListener {

    private ImageEdit imageZoom = ImageEdit.getSingleton();

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        
        double mouseWheelRotation = e.getPreciseWheelRotation();
        //imageZoom.getCurseur().setX(e.getX());
        //imageZoom.getCurseur().setY(e.getY());
        //System.out.println(mouseWheelRotation);
        imageZoom.zoom(mouseWheelRotation);

    }


}
