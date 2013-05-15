package fr.esiea.ancestry.data;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.esiea.ancestry.domain.Person;

public class PersonFormatter {

	private final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	
	
	public Person parse(String[] values) {
		
		String id = values[0];
		String firstName = values[1];
		String lastName = values[2];
		String gender = values[3];
		String fatherId = values[4];
		String motherId = values[5];
		String birthDate = values[6];
		String deathDate = (values.length > 7)? values[7] : "";
		
		// required values
		Person p = new Person.Builder(firstName, lastName).Build(gender);
		p.setId(Integer.parseInt(id, 10));
		
		// optional values 
		if(!fatherId.isEmpty()) p.setFatherId(Integer.parseInt(fatherId, 10));
		if(!motherId.isEmpty()) p.setMotherId(Integer.parseInt(motherId, 10));
		if(!birthDate.isEmpty()) p.setBirthDate(dateFormatter.parseDateTime(birthDate));
		if(!deathDate.isEmpty()) p.setDeathDate(dateFormatter.parseDateTime(deathDate));
		
		return p;
	}
	
	public String[] print(Person p) {
		List<String> values = new ArrayList<String>();
		
		values.add(String.valueOf(p.getId()));
		values.add(p.firstName());
		values.add(p.lastName());
		values.add(p.gender());
		values.add(getParentId(p.getFather()));
		values.add(getParentId(p.getMother()));
		values.add(printDateTimeOrDefault(p.birthDate(), ""));
		values.add(printDateTimeOrDefault(p.deathDate(), ""));
		
		return values.toArray(new String[values.size()]);
	}
	
	
	private String printDateTimeOrDefault(DateTime date, String defaultValue) {
		if(date == null) return defaultValue;
		return dateFormatter.print(date);
	}
	
	private String getParentId(Person p) {
		return (p.getId() != 0) ? String.valueOf(p.getId()) :"";
	}
	
	
}
