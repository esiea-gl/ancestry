package fr.esiea.ancestry.tree;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.joda.time.DateTime;
import org.joda.time.Years;

import fr.esiea.ancestry.domain.Person;

public class PersonComponent extends JPanel {

	private static final long serialVersionUID = 6377146311166987344L;

	private final Person person;
	
	public PersonComponent(Person person) {
		this.person = person;
		this.setSize(30, 30);
		this.setBackground(Color.LIGHT_GRAY);
		
		for(JLabel label : createLabels()) {
			this.add(label);
		}
	}
	
	private List<JLabel> createLabels() {
		
		List<JLabel> labels = new ArrayList<JLabel>();
		
		labels.add(new JLabel(person.fullName()));
		labels.add(new JLabel(personAge() + ""));
		
		return labels;
	}
	
	
	private int personAge() {
		DateTime birthDate = new DateTime(person.birthDate());
		DateTime now = new DateTime();
		Years age = Years.yearsBetween(birthDate, now);
		return age.getYears();
	}
	
}
