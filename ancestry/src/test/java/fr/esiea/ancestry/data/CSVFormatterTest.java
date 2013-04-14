package fr.esiea.ancestry.data;

import org.junit.Assert;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;

public class CSVFormatterTest {

	PersonFormatter formatter;
	
	@Before
	public void setUp() {
		formatter = new PersonFormatter();
	}
	
	@Test
	public void parseTest() {
		
		String[] values = {"1", "Quentin", "Chouleur", "M", "", "", "", ""};
		Person p = formatter.parse(values);
		
		Assert.assertNotNull(p);
		Assert.assertTrue(p instanceof Man);
		Assert.assertTrue(p.firstName().equals("Quentin"));
	}
	
	@Test
	public void printTest() {
		Person p = new Person.Builder("Jean", "Jacques")
							.birthDate(new DateTime().minusYears(10))
							.deathDate(new DateTime().minusYears(2))
							.Build("M");
		
		String[] values = formatter.print(p);
		
		Assert.assertNotNull(values);
		Assert.assertTrue(values.length > 0);
		Assert.assertTrue(values[1].equals("Jean"));
	}
	
	
}
