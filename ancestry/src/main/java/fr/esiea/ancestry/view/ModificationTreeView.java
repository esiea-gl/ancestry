package fr.esiea.ancestry.view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ModificationTreeView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4861455606745082664L;
	private JFrame frame;
	private Person person;
	private JButton submitButton;
	private PersonModificationFormularView enfant ;
	private PersonModificationFormularView pere ;
	private PersonModificationFormularView mere ;
	private PersonModificationFormularView personne ;
	List<Person> family = new ArrayList<Person>();
	
	
	//Création des couples
	private Couple parentsMere = null;
	private Couple parents = null;

	public ModificationTreeView(JFrame frame,Person person, Couple couple) {

		this.person = person;
		this.frame = frame;

		if(person == Man.EMPTY){
			person =(Man) new Person.Builder(null, null).Build("M");
			this.person = person;
			couple.setFather((Man)person);
		}
		if(person == Woman.EMPTY){
			person =(Woman) new Person.Builder(null, null).Build("F");
			this.person = person;
			couple.setMother((Woman)person);
		}
		
		frame.setPreferredSize(new Dimension(720,600));
		submitButton = new JButton("OK");
		submitButton.addActionListener(this); 
		pere = new PersonModificationFormularView("Création ou Modification Père :",person.getFather());
		mere = new PersonModificationFormularView("Création ou Modification Mère :",person.getMother());
		personne = new PersonModificationFormularView("Modification Personne :",person);
		enfant = new PersonModificationFormularView("Création Enfant :",Man.EMPTY);
		
		this.setLayout(null);
		this.add(submitButton);
		submitButton.setBounds(20, 300, 130, 90);
		this.add(pere);
		pere.setBounds(20, 20, 320, 150);
		this.add(mere);
		mere.setBounds(360, 20, 320, 150);
		this.add(personne);
		personne.setBounds(190, 190, 320, 150);
		this.add(enfant);
		enfant.setBounds(190, 360, 320, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == submitButton) {
			
			
			for (Person z : Database.getInstance().all()){
				family.add(z);
			}
			

			//Création ou Modification des parents	
			if(!pere.firstNameField.getText().isEmpty()){
				if(person.getFather() == Man.EMPTY){
					person.getParents().setFather((Man) new Man.Builder(null, null).Build("M"));
					if(person.getMother() == Woman.EMPTY){
						try {
							person.getParents().addChild(person);
						} catch (TooYoungForChildException e1) {
							e1.printStackTrace();
						}
					}					
				}else{
					family.remove(person.getParents().getFather());
				}
				
				person.getParents().getFather().setLastName(pere.lastNameField.getText());
				person.getParents().getFather().setFirstName(pere.firstNameField.getText());

				if(pere.birthDateField.getText().equals("jj/mm/aaaa") || pere.birthDateField.getText().equals("")){
					person.getParents().getFather().setBirthDate(null);
				}else{
					person.getParents().getFather().setBirthDate(new DateTime( Integer.parseInt(pere.birthDateField.getText().substring(6, 10)), Integer.parseInt(pere.birthDateField.getText().substring(3, 5)), Integer.parseInt(pere.birthDateField.getText().substring(0, 2)),0,0));
				}
				
			family.add(person.getParents().getFather());
			}
			

			if(!mere.firstNameField.getText().isEmpty()){
				if(person.getMother() == Woman.EMPTY){
					person.getParents().setMother((Woman) new Woman.Builder(null, null).Build("F"));
				}else{
				family.remove(person.getParents().getMother());
			}
				
				person.getParents().getMother().setLastName(mere.lastNameField.getText());
				person.getParents().getMother().setFirstName(mere.firstNameField.getText());

				if(mere.birthDateField.getText().equals("jj/mm/aaaa") || mere.birthDateField.getText().equals("")){
					person.getParents().getMother().setBirthDate(null);
				}else{
					person.getParents().getMother().setBirthDate(new DateTime( Integer.parseInt(mere.birthDateField.getText().substring(6, 10)), Integer.parseInt(mere.birthDateField.getText().substring(3, 5)), Integer.parseInt(mere.birthDateField.getText().substring(0, 2)),0,0));
				}
				
			family.add(person.getParents().getMother());
			}
			
			//Modification de la personne	
			if(!personne.firstNameField.getText().isEmpty()){		
				family.remove(person);
				person.setLastName(personne.lastNameField.getText());
				person.setFirstName(personne.firstNameField.getText());

				if(personne.birthDateField.getText().equals("jj/mm/aaaa") || personne.birthDateField.getText().equals("")){
					person.setBirthDate(null);
				}else{
					person.setBirthDate(new DateTime( Integer.parseInt(personne.birthDateField.getText().substring(6, 10)), Integer.parseInt(personne.birthDateField.getText().substring(3, 5)), Integer.parseInt(personne.birthDateField.getText().substring(0, 2)),0,0));
				}
				
			family.add(person);
			}
			
			//Création d'un enfant
			Person child = null;
			if(!enfant.firstNameField.getText().isEmpty()){
				if(enfant.maleChoice.isSelected()){
						child = (Man) new Man.Builder(enfant.firstNameField.getText(), enfant.lastNameField.getText()).Build("M");
						if(enfant.birthDateField.getText().equals("jj/mm/aaaa") || enfant.birthDateField.getText().equals("")){
							child.setBirthDate(null);
						}else{
						child.setBirthDate(new DateTime( Integer.parseInt(enfant.birthDateField.getText().substring(6, 10)), Integer.parseInt(enfant.birthDateField.getText().substring(3, 5)), Integer.parseInt(enfant.birthDateField.getText().substring(0, 2)),0,0));
						}
				}
				if(enfant.femaleChoice.isSelected()){
						child = (Woman) new Woman.Builder(enfant.firstNameField.getText(), enfant.lastNameField.getText()).Build("F");
						if(enfant.birthDateField.getText().equals("jj/mm/aaaa") || enfant.birthDateField.getText().equals("")){
							child.setBirthDate(null);
						}else{
						child.setBirthDate(new DateTime( Integer.parseInt(enfant.birthDateField.getText().substring(6, 10)), Integer.parseInt(enfant.birthDateField.getText().substring(3, 5)), Integer.parseInt(enfant.birthDateField.getText().substring(0, 2)),0,0));
						}
				}

				try {							
				if(child != null){
					person.getCouple().addChild(child);
				} 
				} catch(TooYoungForChildException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
				
				family.add(child);
			}
			

			
			
			
			Database.getInstance().Load(family);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new TreeView(frame, person));
			frame.pack();
			frame.repaint();
		}
		
	}

}