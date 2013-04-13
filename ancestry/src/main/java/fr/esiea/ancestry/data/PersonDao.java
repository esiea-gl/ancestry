package fr.esiea.ancestry.data;

import java.util.List;

import fr.esiea.ancestry.domain.Person;

public interface PersonDao {

		Person get(int index);
		List<Person> find(String name);
		List<Person> all();
		void save(List<Person> persons);
}
