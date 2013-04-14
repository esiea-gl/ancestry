package fr.esiea.ancestry.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.Woman;

public class CSVPersonDaoTest {

	private PersonDao dao;
	private final String csv = "#id|Prenom|Nom|Sexe|Pere|Mere|Date de naissance\n" 
			+ "1|Paul|Dupuit|M||306|12/03/1940\n"
			+ "2|Marie|Loup|F|||26/11/1943\n"
			+ "2|Gerome|Tarot|M|||10/10/1947\n";
	
	
	@Before
	public void setUp() throws IOException {
		Reader in = new StringReader(csv);
		dao = new CSVPersonDao(in, '|', '#'); 
		in.close();
	}
	
	@Test
	public void searchTest() {
		List<Person> results = dao.find("Ta");
		
		assertNotNull(results);
		assertTrue(!results.isEmpty());
		assertTrue(results.get(0).firstName().equals("Gerome"));
	}
	
	@Test
	public void retrieveAllTest() {
		List<Person> persons = dao.all();
		
		assertNotNull(persons);
		assertTrue(!persons.isEmpty());
		assertTrue(persons.get(0).firstName().equals("Paul"));
	}
	
	@Test 
	public void retrieveTest() {
		Person man = dao.get(0);
		
		assertNotNull(man);
		assertTrue(man.firstName().equals("Paul"));
		assertTrue(man.lastName().equals("Dupuit"));
		assertTrue(man instanceof Man);
		
		Person woman = dao.get(1);
		
		assertNotNull(woman);
		assertTrue(woman.firstName().equals("Marie"));
		assertTrue(woman.lastName().equals("Loup"));
		assertTrue(woman instanceof Woman);
	}
	
	
	@Test
	public void saveTest() throws IOException {
		
		Writer writer = new FileWriter("test.csv");
		dao.save(writer, dao.all());
		writer.close();
		
		Reader reader = new FileReader("test.csv");
		PersonDao newDao = new CSVPersonDao(reader, '|', '#');
		reader.close();
		
		Assert.assertNotNull(newDao.all());
		Assert.assertTrue(newDao.get(0).firstName().equals(dao.get(0).firstName()));
		
	}
}
