package fr.esiea.ancestry.data;

// Leroy was here

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;

import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.CycleException;
import fr.esiea.ancestry.domain.InvalidGenderException;
import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.TooYoungForChildException;
import fr.esiea.ancestry.domain.Woman;

public class CSVPersonDao implements PersonDao {

	private final List<Person> persons;
	private final Map<Integer, Person> personMap;
	private final PersonFormatter personFormatter;
	private final CSVStrategy strategy;
	
	public CSVPersonDao(List<Person> persons) {
		this.persons = persons;
		this.strategy = new CSVStrategy('|', '\0', '#');	
		this.personMap = new HashMap<Integer, Person>();
		this.personFormatter = new PersonFormatter();
	}
	
	public CSVPersonDao(Reader reader, char delimiter, char commentStart) 
			throws IOException, InvalidGenderException, CycleException, TooYoungForChildException {
		persons = new ArrayList<Person>();
		personMap = new HashMap<Integer, Person>();
		personFormatter = new PersonFormatter();
		
		strategy = new CSVStrategy(delimiter, '\0', commentStart);	
		CSVParser parser = new CSVParser(reader, strategy);
		
		for(String[] line : parser.getAllValues()) {
			if(line.length < 6 || line[0].contains("#")) continue;
			Person p = personFormatter.parse(line);
			persons.add(p);
			personMap.put(p.getId(), p);
		}
		
		linkPerson();
	}
	
	public Person get(int id) {
		return personMap.get(id);
	}

	public List<Person> find(String name) {
		
		String regex = "(.*?)" + name.toUpperCase() + "(.*?)";
		List<Person> results = new ArrayList<Person>();
		
		for(Person p : persons) {
			if(p.fullName().toUpperCase().matches(regex))
				results.add(p);
		}
		
		return results;
	}
		
	public void save(Writer writer, List<Person> persons) throws IOException {
		CSVPrinter printer = new CSVPrinter(writer, strategy);
		
		for(Person person : persons) {
			if(person == null) continue;
			if(person.isNew()) {
				person.setId(generateId());
				this.personMap.put(person.getId(), person);
			}
			
			printer.println(personFormatter.print(person));
		}
	}

	public List<Person> all() {
		return persons;
	}
	
	private int generateId() {
		int id = 1;
		
		while(personMap.get(id) != null) ++id;
		return id;
	}
	
	private void linkPerson() throws InvalidGenderException, CycleException, TooYoungForChildException{
		
		for(Person p : persons) {

			Man father = null;
			Woman mother = null;
			
			try {
				father = (Man) personMap.get(p.getFatherId());
				
			} catch(ClassCastException e) {
				throw new InvalidGenderException(
						personMap.get(p.getFatherId()).fullName());
			}
			
			try {
				mother = (Woman) personMap.get(p.getMotherId());
				
			} catch(ClassCastException e) {
				throw new InvalidGenderException(
						personMap.get(p.getMotherId()).fullName());
			}
			
			
			
			if(father == null || mother == null) continue;
			
			// Verifie si les parents ne sont pas dans sa déscendance 
			// ou si l'enfant est pas dans les ancestres des parents
			if(p.hasDescendant(father) || father.hasAscendant(p)) 
				throw new CycleException(father.fullName(), p.fullName());
				
			if(p.hasDescendant(mother) || p.hasDescendant(mother)) 
				throw new CycleException(mother.fullName(), p.fullName());
			
			
			// link parents
			Couple parents = new Couple();
			if(father.getCouple() != mother.getCouple()) {
				parents.setFather(father);
				parents.setMother(mother);
			}
			
			// link child
			parents.addChild(p);
			
		}
	}
	
}
