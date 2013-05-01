package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PersonCreationFormularView extends JPanel {

	private static final long serialVersionUID = 2537484965786463541L;

	public PersonCreationFormularView(String personTitle){
		  setLayout(new BorderLayout());
		  JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		  topPanel.add(new JLabel(personTitle));
		  this.add(topPanel,BorderLayout.NORTH);
		  		  
		  JLabel lastNameLabel = new JLabel("Nom");
		  JTextField lastNameField = new JTextField();
		  
		  JLabel firstNameLabel = new JLabel("Prénom");
		  JTextField firstNameField = new JTextField();
		  
		  JLabel birthDateLabel = new JLabel("Date de naissance");
		  JTextField birthDateField = new JTextField();
		  
		  JLabel sexLabel = new JLabel("Sexe");
		  JRadioButton maleChoice = new JRadioButton("Masculin");
		  JRadioButton femaleChoice = new JRadioButton("Féminin");
		  
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
		  bottomPanel.add(sexLabel);
		  bottomPanel.add(sexChoicePanel);
		  
		  this.add(bottomPanel,BorderLayout.CENTER);
	  }       
}