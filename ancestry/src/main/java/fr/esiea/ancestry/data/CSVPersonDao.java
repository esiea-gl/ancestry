package fr.esiea.ancestry.data;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;

import fr.esiea.ancestry.domain.Person;

public class CSVPersonDao implements PersonDao {

	private final List<Person> persons;
	private final PersonFormatter personFormatter;
	private final CSVStrategy strategy;
	
	public CSVPersonDao(Reader reader, char delimiter, char commentStart) throws IOException {
		persons = new ArrayList<Person>();
		personFormatter = new PersonFormatter();
		strategy = new CSVStrategy(delimiter, '\0', commentStart);	
		CSVParser parser = new CSVParser(reader, strategy);
		
		for(String[] line : parser.getAllValues()) {
			persons.add(personFormatter.parse(line));
		}
	}
	
	public Person get(int index) {
		return persons.get(index);
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
		
		for(Person p : persons) {
			printer.println(personFormatter.print(p));
		}
	}

	public List<Person> all() {
		return persons;
	}
	
	
}
