package fr.esiea.ancestry.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Woman;
import fr.esiea.ancestry.tree.TreeView;

public class CreateTreeView extends JPanel implements ActionListener{

	private static final long serialVersionUID = 4861455606745082664L;
	private JFrame frame;
	JButton butOk;
	Couple couple = new Couple();
	Couple oldCouple = new Couple();
	Man father;
	Woman mother;
	Man grandFather = null;
	Woman grandMother = null;
	PersonCreationFormularView enfant ;
	PersonCreationFormularView pere ;
	PersonCreationFormularView mere ;

	public CreateTreeView(JFrame frame) {

		this.setPreferredSize(ViewParams.VIEW_DIMENSION);
		
		this.frame = frame;
		JLabel lab = new JLabel("Création d'un arbre");
		butOk = new JButton("OK");
		butOk.addActionListener(this);
		enfant = new PersonCreationFormularView("Enfant :");
		pere = new PersonCreationFormularView("Père :");
		mere = new PersonCreationFormularView("Mère :");

		this.setLayout(null);
		this.add(lab);
		lab.setBounds(50, 50, 150, 10);
		this.add(butOk);
		butOk.setBounds(50, 330, 150, 90);
		this.add(enfant);
		enfant.setBounds(20, 120, 320, 150);
		this.add(pere);
		pere.setBounds(360, 20, 320, 150);
		this.add(mere);
		mere.setBounds(360, 190, 320, 150);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if(source == butOk) {
			/*father = (Man) new Person.Builder("pere", "zdf").Build("M");
			grandFather = (Man) new Person.Builder(null, null).Build("M");
			//father.setBirthDate(new DateTime(pere.birthDateField.getText()));
			mother = (Woman) new Person.Builder("mere", "sfe").Build("F");
			grandMother = (Woman) new Person.Builder(null, null).Build("F");
			
			oldCouple.setFather(grandFather);
			oldCouple.setMother(grandMother);
			//mother.setBirthDate(new DateTime(mere.birthDateField));
			couple.setFather(father);
			father.setParents(oldCouple);
			mother.setParents(oldCouple);
			//couple.setMother(mother);*/
			
			Man p = (Man) new Man.Builder("Jesus", "Crise").Build("M");
			p.setBirthDate(null);
			p.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
			

			Woman p2 = (Woman) new Woman.Builder("jinette", "Triquite").Build("F");
			p2.setBirthDate(null);
			p2.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
			

			Woman p3 = (Woman) new Woman.Builder("Joharno", "DelaMort").Build("F");
			p3.setBirthDate(new DateTime(2003, 05, 04, 0, 0));
			p3.setDeathDate(new DateTime(2012, 8, 10, 0, 0));
			

			Man p4 = (Man) new Man.Builder("Père", "Noël").Build("M");
			p4.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
			p4.setDeathDate(new DateTime(2012, 10, 10, 0, 0));

			Woman p5 = (Woman) new Woman.Builder("woucha", "zig").Build("F");
			p5.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
			p5.setDeathDate(new DateTime(2012, 10, 10, 0, 0));

			Man p6 = (Man) new Man.Builder("Père", "Noël").Build("M");
			p6.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
			p6.setDeathDate(new DateTime(2012, 10, 10, 0, 0));
			
			Man p7 = (Man) new Man.Builder(null, null).Build("M");
			p7.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
			p7.setDeathDate(new DateTime(2012, 10, 10, 0, 0));
			
			Couple test1 = new Couple();
			Couple test2 = new Couple();
			Couple test3 = new Couple();
			test1.setFather(p);
			test1.setMother(p2);
			test2.setFather(p4);
			test2.setMother(p3);
			test1.addChild(p6);
			test2.addChild(p5);
			test3.setFather(p6);
			test3.setMother(p5);
			
			
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new TreeView(frame, p6));
			frame.pack();
			frame.repaint();
		}
	}
}