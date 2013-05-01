package fr.esiea.ancestry.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreatePanel extends JPanel {

	  public CreatePanel(String nPan){
		  setLayout(new BorderLayout());
		  JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		  topPanel.add(new JLabel(nPan));
		  this.add(topPanel,BorderLayout.NORTH);
		  		  
		  JLabel n1 = new JLabel("Nom");
		  JTextField n2 = new JTextField();
		  
		  JLabel p1 = new JLabel("Prénom");
		  JTextField p2 = new JTextField();
		  
		  JLabel ddn1 = new JLabel("Date de naissance");
		  JTextField ddn2 = new JTextField();
		  
		  JLabel s1 = new JLabel("Sexe");
		  JRadioButton s2h = new JRadioButton("Masculin");
		  JRadioButton s2f = new JRadioButton("Féminin");
		  
		  ButtonGroup BGroup = new ButtonGroup();
		  BGroup.add(s2h);
		  BGroup.add(s2f);
		  
		  JPanel hf = new JPanel();
		  hf.setLayout(new GridLayout(1,2));
		  hf.add(s2h);
		  hf.add(s2f);

		  JPanel bottomPanel = new JPanel(new GridLayout(4,2));
		  bottomPanel.add(n1);
		  bottomPanel.add(n2);
		  bottomPanel.add(p1);
		  bottomPanel.add(p2);
		  bottomPanel.add(ddn1);
		  bottomPanel.add(ddn2);
		  bottomPanel.add(s1);
		  bottomPanel.add(hf);
		  
		  this.add(bottomPanel,BorderLayout.CENTER);
		  this.setSize(10, 10);
	  }       
}
