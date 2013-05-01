package fr.esiea.ancestry.data;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;

public class EmptyDao implements PersonDao{

	@Override
	public Person get(int id) {
		return Man.EMPTY;
	}

	@Override
	public List<Person> find(String name) {
		return new ArrayList<Person>();
	}

	@Override
	public List<Person> all() {
		return new ArrayList<Person>();
	}

	@Override
	public void save(Writer writer, List<Person> persons) throws IOException {
		return;
	}

}
