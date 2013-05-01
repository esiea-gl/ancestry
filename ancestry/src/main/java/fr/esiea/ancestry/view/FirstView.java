package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.esiea.ancestry.data.Database;

public class FirstView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 8530915770379792683L;
	private JButton loadFileButton, createButton;
	
	public FirstView() {
		
		loadFileButton = createLoadFileButton();
		createButton = createCreateButton();
		
		BorderLayout layout = new BorderLayout();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(layout);
		this.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JPanel innerPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		innerPannel.add(loadFileButton);
		innerPannel.add(createButton);
		this.add(innerPannel);
		
	}

	private JButton createCreateButton() {
		JButton button = new JButton("Charger une famille");
		button.setActionCommand("Load");
		button.addActionListener(this);
		return button;
	}
	
	private JButton createLoadFileButton() {
		JButton button = new JButton("Créer une famille");
		button.setActionCommand("Create");
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		if(evt.getActionCommand().equals("Load"))
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv", "CSV");
			String fileName;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(filter);
			
			int retVal = fileChooser.showOpenDialog(null);
			
			if (retVal == JFileChooser.APPROVE_OPTION) {
				fileName = fileChooser.getSelectedFile().toString();
				Database instance = Database.getInstance();
				instance.Load(fileName);
				instance.find("Dupuit");
				System.out.println(instance.find("dupuit").toString());
				System.out.println("le nombre de personne est : " + instance.all().size());
			}
		}
		else if(evt.getActionCommand().equals("Create")){
			System.out.println("TODO : Creation View");
		}
		
	}
}
