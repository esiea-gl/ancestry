package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPanel jpanelFenetreAccueil, jpanelCreationArbre, jpanelRecherchePersonne, jpanelArbreGenealogique;
	private JButton boutonChargerCSV, boutonCreerFamille;
	private GridLayout gridLayout;
	
	
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
		
		jpanelFenetreAccueil = new JPanel();
		jpanelCreationArbre = new JPanel();
		jpanelRecherchePersonne = new JPanel();
		jpanelArbreGenealogique = new JPanel();
		gridLayout = new GridLayout(5,5,5,5);
		boutonChargerCSV = new JButton("Charger un CSV");
		boutonChargerCSV.setActionCommand("ChargerCSV");
		boutonCreerFamille = new JButton("Créer une famille");
		boutonCreerFamille.setActionCommand("CreerFamille");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 521);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//***************   Menu   **************** //
		JMenuItem mntmNewMenuItem = new JMenuItem("Fichier");
		menuBar.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Edition");
		menuBar.add(mntmNewMenuItem_1);
		//**************  Fin menu **************** //

		jpanelFenetreAccueil.setBorder(new EmptyBorder(5, 5, 5, 5));
		jpanelFenetreAccueil.setLayout(gridLayout);
		jpanelFenetreAccueil.applyComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT); 
		
		jpanelFenetreAccueil.add(boutonChargerCSV);
		jpanelFenetreAccueil.add(boutonCreerFamille);
		setContentPane(jpanelFenetreAccueil);
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals("ChargerCSV"))
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv", "CSV");
			String fileName;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(filter);
			
			int retVal = fileChooser.showOpenDialog(null);
			
			if (retVal == JFileChooser.APPROVE_OPTION) {
				
				fileName = fileChooser.getSelectedFile().toString();
			
			}
		}
		else{
			
		}
		
	}

}
