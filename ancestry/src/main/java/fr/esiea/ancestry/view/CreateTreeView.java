package fr.esiea.ancestry.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateTreeView extends JPanel {

	private static final long serialVersionUID = 4861455606745082664L;
	private JFrame frame;

	public CreateTreeView(JFrame frame) {

		this.setPreferredSize(new Dimension(700,500));
		
		this.frame = frame;
		JLabel lab = new JLabel("Création d'un arbre");
		JButton but = new JButton("OK");
		JPanel enfant = new PersonCreationFormularView("Enfant :");
		JPanel pere = new PersonCreationFormularView("Père :");
		JPanel mere = new PersonCreationFormularView("Mère :");

		this.setLayout(null);
		this.add(lab);
		lab.setBounds(50, 50, 150, 10);
		this.add(but);
		but.setBounds(50, 330, 150, 90);
		this.add(enfant);
		enfant.setBounds(20, 120, 320, 150);
		this.add(pere);
		pere.setBounds(360, 20, 320, 150);
		this.add(mere);
		mere.setBounds(360, 190, 320, 150);
	}
}