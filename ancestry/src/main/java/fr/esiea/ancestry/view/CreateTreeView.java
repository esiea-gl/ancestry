package fr.esiea.ancestry.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import fr.esiea.ancestry.data.Database;
import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.TooYoungForChildException;
import fr.esiea.ancestry.domain.Woman;
import fr.esiea.ancestry.tree.TreeView;

public class CreateTreeView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4861455606745082664L;
	private JFrame frame;
	private JButton submitButton;
	PersonCreationFormularView enfant ;
	PersonCreationFormularView pere ;
	PersonCreationFormularView mere ;

	public CreateTreeView(JFrame frame) {

		this.setPreferredSize(ViewParams.VIEW_DIMENSION);
		
		this.frame = frame;
		JLabel lab = new JLabel("Création d'un arbre");
		submitButton = new JButton("OK");
		submitButton.addActionListener(this);
		enfant = new PersonCreationFormularView("Enfant :");
		pere = new PersonCreationFormularView("Père :");
		mere = new PersonCreationFormularView("Mère :");

		this.setLayout(null);
		this.add(lab);
		lab.setBounds(50, 50, 150, 10);
		this.add(submitButton);
		submitButton.setBounds(50, 330, 150, 90);
		this.add(enfant);
		enfant.setBounds(20, 120, 320, 150);
		this.add(pere);
		pere.setBounds(360, 40, 320, 150);
		this.add(mere);
		mere.setBounds(360, 210, 320, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == submitButton) {
			
			List<Person> family = new ArrayList<Person>();
			
			//Création des parents		
			Man father = (Man) new Man.Builder(pere.firstNameField.getText(), pere.lastNameField.getText()).Build("M");
			if(pere.birthDateField.getText().equals("jj/mm/aaaa")){
				father.setBirthDate(null);
			}else{
			father.setBirthDate(new DateTime( Integer.parseInt(pere.birthDateField.getText().substring(6, 10)), Integer.parseInt(pere.birthDateField.getText().substring(3, 5)), Integer.parseInt(pere.birthDateField.getText().substring(0, 2)),0,0));
			}
			family.add(father);
			
			Woman mother = (Woman) new Woman.Builder(mere.firstNameField.getText(), mere.lastNameField.getText()).Build("F");
			if(mere.birthDateField.getText().equals("jj/mm/aaaa")){
				mother.setBirthDate(null);
			}else{
			mother.setBirthDate(new DateTime( Integer.parseInt(mere.birthDateField.getText().substring(6, 10)), Integer.parseInt(mere.birthDateField.getText().substring(3, 5)), Integer.parseInt(mere.birthDateField.getText().substring(0, 2)),0,0));
			}
			family.add(mother);
			
			Person child = null;
			//Création d'un enfant
			if(!enfant.firstNameField.getText().isEmpty()){
				if(enfant.maleChoice.isSelected()){
						child = (Man) new Man.Builder(enfant.firstNameField.getText(), enfant.lastNameField.getText()).Build("M");
						if(enfant.birthDateField.getText().equals("jj/mm/aaaa")){
							child.setBirthDate(null);
						}else{
						child.setBirthDate(new DateTime( Integer.parseInt(enfant.birthDateField.getText().substring(6, 10)), Integer.parseInt(enfant.birthDateField.getText().substring(3, 5)), Integer.parseInt(enfant.birthDateField.getText().substring(0, 2)),0,0));
						}
				}
				if(enfant.femaleChoice.isSelected()){
						child = (Woman) new Woman.Builder(enfant.firstNameField.getText(), enfant.lastNameField.getText()).Build("F");
						if(enfant.birthDateField.getText().equals("jj/mm/aaaa")){
							child.setBirthDate(null);
						}else{
						child.setBirthDate(new DateTime( Integer.parseInt(enfant.birthDateField.getText().substring(6, 10)), Integer.parseInt(enfant.birthDateField.getText().substring(3, 5)), Integer.parseInt(enfant.birthDateField.getText().substring(0, 2)),0,0));
						}
				}
				family.add(child);
			}
			
			//Création des couples
			Couple parentsPere = new Couple();
			Couple parentsMere = new Couple();
			Couple parents = new Couple();

			//Création des liaisons
			parentsPere.setFather(Man.EMPTY);
			parentsPere.setMother(Woman.EMPTY);
			
			parentsMere.setFather(Man.EMPTY);
			parentsMere.setMother(Woman.EMPTY);
			
			parents.setFather(father);
			parents.setMother(mother);
			
			try {
			father.setParents(parentsPere);
			mother.setParents(parentsMere);
			
			if(child != null){
				parents.addChild(child);
			} 
			} catch(TooYoungForChildException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
			
			
			Database.getInstance().Load(family);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new TreeView(frame, father));
			frame.pack();
			frame.repaint();
		}
	}
}