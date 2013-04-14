package fr.esiea.ancestry.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person woman;
	private Person man;
	private DateTime currentDateMinusTenDays;
	private DateTime currentDateMinusFiveDays;

	@Before
	public void setUp() {
		woman = new Woman();
		man = new Man();
		currentDateMinusTenDays = new DateTime().minusDays(10);
		currentDateMinusFiveDays = new DateTime().minusDays(5);
		
	}
	
		
	@Test
	public void testSetMother() {
		Couple parents = new Couple();
		parents.setMother(new Woman());
		
		man.setParents(parents);
		assertTrue(parents.childCount() == 1);
	}
	
	@Test
	public void testSetFather(){
		Couple parents = new Couple();
		parents.setFather(new Man());
		
		man.setParents(parents);
		assertTrue(parents.childCount() == 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalBirthDateAfterDeathDate() throws InterruptedException{
		
		DateTime deathDate = new DateTime().minusDays(10); 
		DateTime birthDate = deathDate.minusHours(-1);
		
		woman.setDeathDate(deathDate);
		woman.setBirthDate(birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalDeathDateBeforeBirthDate() throws InterruptedException{
		
		DateTime birthDate = new DateTime().minusDays(10);
		DateTime deathDate = birthDate.minusHours(1);
		
		woman.setBirthDate(birthDate);
		woman.setDeathDate(deathDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testIllegalDeathDateInFuture() {
		
		
		DateTime deathDate = new DateTime().minusDays(-1);
		woman.setDeathDate(deathDate);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testIllegalBirthDateInFuture() {
		
		DateTime birthDate = new DateTime().minusDays(-1);
		woman.setBirthDate(birthDate);
		
	}
	
	@Test
	public void testWomanBuild() {
		
		String firstName = "Elodie";
		String lastName = "Dupont";
		
		Person woman = new Person.Builder(firstName, lastName)
						.birthDate(currentDateMinusTenDays)
						.deathDate(currentDateMinusFiveDays)
						.Build("F");
		
		assertNotNull(woman);
		assertTrue(woman instanceof Woman);
		assertTrue(woman.birthDate().isEqual(currentDateMinusTenDays));
		assertTrue(woman.deathDate().isEqual(currentDateMinusFiveDays));
		assertTrue(woman.firstName() == firstName);
		assertTrue(woman.lastName() == lastName); 
	}
	
	@Test
	public void testManBuild() {
		
		String firstName = "Elodi";
		String lastName = "Dupond";
		
		Person man = new Person.Builder(firstName, lastName)
						.birthDate(currentDateMinusTenDays)
						.deathDate(currentDateMinusFiveDays)
						.Build("M");
		
		assertNotNull(man);
		assertTrue(man instanceof Man);
		assertTrue(man.birthDate().equals(currentDateMinusTenDays));
		assertTrue(man.deathDate().equals(currentDateMinusFiveDays));
		assertTrue(man.firstName() == firstName);
		assertTrue(man.lastName() == lastName);		
	}
	
	
}

