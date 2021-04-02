package ca.borysserbyn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import ca.borysserbyn.model.ImageEdit;
import ca.borysserbyn.model.memento.Originator;

public class ZoomInCommand implements MouseWheelListener {

    ImageEdit imageZoom = Originator.getSingleton().getImageEdit();

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        
        
        if(e.getWheelRotation() < 0){

            System.out.println("zoom in");
        }else{

            System.out.println("zoom out");
        }
        
    }




    

    



}
