package fr.esiea.ancestry.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import fr.esiea.ancestry.domain.CycleException;
import fr.esiea.ancestry.domain.InvalidGenderException;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.TooYoungForChildException;

public class Database {

	private static Database _instance;
	private PersonDao _dao;

	private Database() {
		_dao = new EmptyDao();
	}

	public void Load(List<Person> persons) {
		_dao = new CSVPersonDao(persons);
	}
	
	public void Load(String path) throws InvalidGenderException, CycleException, TooYoungForChildException {
		try {
			Reader in = new BufferedReader(new FileReader(path));
			_dao = new CSVPersonDao(in, '|', '#');
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Person get(int id) {
		return _dao.get(id);
	}

	public void Save(String path) {
		try {
			Writer out = new FileWriter(path);
			_dao.save(out, _dao.all());
			out.close();
		} catch (IOException e) {

		}
	}

	public List<Person> all() {
		return _dao.all();
	}

	public List<Person> find(String name) {
		return _dao.find(name);
	}

	public static Database getInstance() {

		if (_instance == null)
			_instance = new Database();
		return _instance;
	}

}

