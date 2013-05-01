package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {


	private JPanel firstView;
	private BorderLayout borderLayout;
	private JMenuBar menuBar;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		gui.setTitle("Arbre Généalogique");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		firstView = new FirstView(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//***************   Menu   **************** //
		JMenu menuFichier = new JMenu("Fichier");	
		JMenu menuEdition = new JMenu("Edition");
		menuBar.add(menuFichier);
		menuBar.add(menuEdition);
		
		//**************  Fin menu **************** //

		setContentPane(firstView);
		
	}



}
