package fr.esiea.ancestry.data;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import fr.esiea.ancestry.domain.Person;

public interface PersonDao {

		Person get(int id);
		List<Person> find(String name);
		List<Person> all();
		void save(Writer writer, List<Person> persons) throws IOException;
}
