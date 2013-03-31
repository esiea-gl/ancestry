package fr.esiea.ancestry.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person;
	private DateTime currentDateMinusTenDays;
	private DateTime currentDateMinusFiveDays;

	@Before
	public void setUp() {
		person = new Woman();
		currentDateMinusTenDays = new DateTime().minusDays(10);
		currentDateMinusFiveDays = new DateTime().minusDays(5);
		
	}
	
	@Test
	public void testAddChild() {
		
		Person firstChild = new Woman();
		Person secondChild = new Man();
		Person thirdChild = new Woman();
		
		person.addChild(firstChild);
		person.addChild(secondChild);
		person.addChild(thirdChild);
	
		assertTrue(person.childCount() == 3);
		assertNotNull(person.childs());
	}
	
	@Test
	public void testRemoveChild() {
		Person child = new Woman();
		person.addChild(child);
		assertTrue(person.childCount() == 1);
		person.removeChild(child);
		assertTrue(person.childCount() == 0);
	}
	
	@Test
	public void testSetMother() {
		person.setMother(new Woman());
		assertNotNull(person.mother());
		assertTrue(person.mother().childCount() > 0);
	}
	
	@Test
	public void testSetFather(){
		person.setFather(new Man());
		assertNotNull(person.father());
		assertTrue(person.father().childCount() > 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalBirthDateAfterDeathDate() throws InterruptedException{
		
		DateTime deathDate = new DateTime().minusDays(10); 
		DateTime birthDate = deathDate.minusHours(-1);
		
		person.setDeathDate(deathDate);
		person.setBirthDate(birthDate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalDeathDateBeforeBirthDate() throws InterruptedException{
		
		DateTime birthDate = new DateTime().minusDays(10);
		DateTime deathDate = birthDate.minusHours(1);
		
		person.setBirthDate(birthDate);
		person.setDeathDate(deathDate);
		
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testIllegalDeathDateInFuture() {
		
		
		DateTime deathDate = new DateTime().minusDays(-1);
		person.setDeathDate(deathDate);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testIllegalBirthDateInFuture() {
		
		DateTime birthDate = new DateTime().minusDays(-1);
		person.setBirthDate(birthDate);
		
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

