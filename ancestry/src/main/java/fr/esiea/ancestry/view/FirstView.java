package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.esiea.ancestry.data.Database;
import fr.esiea.ancestry.domain.CycleException;
import fr.esiea.ancestry.domain.InvalidGenderException;
import fr.esiea.ancestry.domain.TooYoungForChildException;

public class FirstView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 8530915770379792683L;
	private JButton loadFileButton, createButton;
	private JFrame frame;
	
	public FirstView(JFrame frame) {
		
		setPreferredSize(ViewParams.VIEW_DIMENSION);
		
		this.frame = frame;
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
			CSVFileChooser fileChooser = new CSVFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				try {
					Database.getInstance().Load(fileChooser.getSelectedFile().toString());
					frame.getContentPane().removeAll();
					frame.getContentPane().add(new SearchView(frame));
					frame.pack();
					frame.repaint();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		}
		else if(evt.getActionCommand().equals("Create")){
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new CreateTreeView(frame));
			frame.pack();
			frame.repaint();
		}
		
	}
}
