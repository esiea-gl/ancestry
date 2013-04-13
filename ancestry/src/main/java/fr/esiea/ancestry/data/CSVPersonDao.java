package fr.esiea.ancestry.data;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVStrategy;

import fr.esiea.ancestry.domain.Person;

public class CSVPersonDao implements PersonDao {

	private final List<Person> persons;
	
	public CSVPersonDao(Reader reader, char delimiter, char commentStart) throws IOException {
		this.persons = new ArrayList<Person>();
		CSVParser parser = new CSVParser(reader, new CSVStrategy(delimiter, '\0', commentStart));
		
		for(String[] line : parser.getAllValues()) {
			persons.add(lineToPerson(line));
		}
	}
	
	public Person get(int index) {

		return persons.get(index);
	}

	public List<Person> find(String name) {
		
		
		return null;
	}
		
	public void save(List<Person> persons) {
		
	}

	public List<Person> all() {
		return persons;
	}
	
	private Person lineToPerson(String[] line) {
		
		String firstName = line[1];
		String lastName = line[2];
		String gender = line[3];
		
		return new Person.Builder(firstName, lastName).Build(gender);
	}
}
