package fr.esiea.ancestry.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.esiea.ancestry.data.Database;
import fr.esiea.ancestry.domain.CycleException;
import fr.esiea.ancestry.domain.InvalidGenderException;

public class GUI extends JFrame implements ActionListener {


	private static final long serialVersionUID = -4524641852995756757L;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newFile;
	private JMenuItem loadFile;
	private JMenuItem saveFile;
	private JMenuItem close;
	private JButton help;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setTitle("Arbre Généalogique");
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
		
		FirstView firstView = new FirstView(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLUE);
		setJMenuBar(menuBar);

		fileMenu = new JMenu("Fichier");	
		menuBar.add(fileMenu);

		
		help = new JButton("Aide");
		newFile = new JMenuItem("Nouveau");
		loadFile = new JMenuItem("Ouvrir");
		saveFile = new JMenuItem("Sauvegarder");
		close = new JMenuItem("Quitter");
		
		help.setFocusPainted(false);
		help.setOpaque(false);
		help.setBackground(new Color(0, 0, 0, 0));
		help.setBorderPainted(false);
		help.setMargin(new Insets(2, 2, 2, 2));
		
		newFile.addActionListener(this);
		loadFile.addActionListener(this);
		saveFile.addActionListener(this);
		close.addActionListener(this);
		help.addActionListener(this);
		
		fileMenu.add(newFile);
		fileMenu.add(loadFile);
		fileMenu.add(saveFile);
		fileMenu.add(close);
		
		menuBar.add(help);

		setContentPane(firstView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if(source == newFile) {
			moveToView(new CreateTreeView(this));
		} else if (source == loadFile) {
			CSVFileChooser fileChooser = new CSVFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				try {
					Database.getInstance().Load(fileChooser.getSelectedFile().toString());
					moveToView(new SearchView(this));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			}
		} else if (source == saveFile) {
			
			if(Database.getInstance().all().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Il n'y a rien à enregistrer");
				return;
			}
			
			CSVFileChooser fileChooser = new CSVFileChooser();
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				Database.getInstance().Save(fileChooser.getSelectedFile().toString());
			}
		} 
		else if (source == close) {
			this.dispose();
		}
		else if (source == help) {
			moveToView(new HelpView(this));
		}
	}
	
	private void moveToView(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(view);
		this.pack();
		this.repaint();
	}
	
}
