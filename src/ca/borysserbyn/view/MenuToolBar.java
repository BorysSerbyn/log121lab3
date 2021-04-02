package ca.borysserbyn.view;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import ca.borysserbyn.controller.ChargerCommand;
import ca.borysserbyn.controller.SaveCommand;

public class MenuToolBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_SAVE = "Save";
	

	public MenuToolBar() {
		ajouterMenuFichier();
	}

	/**
	 * Cr�er le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuSave = new JMenuItem(MENU_FICHIER_SAVE);

		menuCharger.addActionListener(new ChargerCommand());
        menuSave.addActionListener(new SaveCommand());
        menuFichier.add(menuCharger);
		menuFichier.add(menuSave);

		add(menuFichier);

    }
}
