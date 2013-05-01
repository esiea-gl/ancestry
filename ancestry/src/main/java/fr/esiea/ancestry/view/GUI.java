package fr.esiea.ancestry.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.esiea.ancestry.data.Database;

public class GUI extends JFrame implements ActionListener {


	private static final long serialVersionUID = -4524641852995756757L;
	private JMenuItem newFile;
	private JMenuItem loadFile;
	private JMenuItem saveFile;
	
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		JMenu fileMenu = new JMenu("Fichier");	
		menuBar.add(fileMenu);
		
		newFile = new JMenuItem("Nouveau");
		loadFile = new JMenuItem("Ouvrir");
		saveFile = new JMenuItem("Sauvegarder");
		
		newFile.addActionListener(this);
		loadFile.addActionListener(this);
		saveFile.addActionListener(this);
		
		fileMenu.add(newFile);
		fileMenu.add(loadFile);
		fileMenu.add(saveFile);

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
				Database.getInstance().Load(fileChooser.getSelectedFile().toString());
				moveToView(new SearchView(this));
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
	}
	
	private void moveToView(JPanel view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(view);
		this.pack();
		this.repaint();
	}
	
}
