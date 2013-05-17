package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.esiea.ancestry.domain.Person;

public class PersonModificationFormularView extends JPanel{

	private static final long serialVersionUID = 2537484965786463541L;
	private final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	  JTextField lastNameField = null;
	  JTextField firstNameField = null;
	  JTextField birthDateField = null;
	  ButtonGroup buttonGroup = new ButtonGroup();
	  JRadioButton maleChoice;
	  JRadioButton femaleChoice;

	public PersonModificationFormularView(String personTitle, Person person){
		  setLayout(new BorderLayout());
		  JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			if(personTitle.equals("Personne :")){
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				topPanel.setBackground(Color.cyan);
			}
		  topPanel.add(new JLabel(personTitle));
		  this.add(topPanel,BorderLayout.NORTH);
		  		  
		  JLabel lastNameLabel = new JLabel("Nom");		
		  lastNameField = new JTextField(person.lastName());  
		  
		  JLabel firstNameLabel = new JLabel("Prénom");
		  firstNameField = new JTextField(person.firstName());
		  
		  JLabel birthDateLabel = new JLabel("Date de naissance");
		  if(person.birthDate()==null){
			  birthDateField = new JTextField("jj/mm/aaaa");			  
		  }else{
			  birthDateField = new JTextField(dateFormatter.print(person.birthDate()));
		  }
		  
		  JLabel sexLabel = new JLabel("Sexe");
		  maleChoice = new JRadioButton("Masculin");
		  maleChoice.setSelected(true);
		  femaleChoice = new JRadioButton("Féminin");
		  
		  ButtonGroup buttonGroup = new ButtonGroup();
		  buttonGroup.add(maleChoice);
		  buttonGroup.add(femaleChoice);
		  
		  JPanel sexChoicePanel = new JPanel();
		  sexChoicePanel.setLayout(new GridLayout(1,2));
		  sexChoicePanel.add(maleChoice);
		  sexChoicePanel.add(femaleChoice);

		  JPanel bottomPanel = new JPanel(new GridLayout(4,2));
		  bottomPanel.add(lastNameLabel);
		  bottomPanel.add(lastNameField);
		  bottomPanel.add(firstNameLabel);
		  bottomPanel.add(firstNameField);
		  bottomPanel.add(birthDateLabel);
		  bottomPanel.add(birthDateField);
		  if(personTitle.equals("Enfant :")){
			  bottomPanel.add(sexLabel);
			  bottomPanel.add(sexChoicePanel);
		  }
		  
		  this.add(bottomPanel,BorderLayout.CENTER);
	  }       
}