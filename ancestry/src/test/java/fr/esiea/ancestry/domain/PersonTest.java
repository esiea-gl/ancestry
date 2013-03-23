package fr.esiea.ancestry.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person;
	
	@Before
	public void setUp() {
		person = new Woman();
			
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
	public void testIllegalBirthDate() throws InterruptedException{
		
		Date deathDate = new Date(); 
		Thread.sleep(10);
		Date birthDate = new Date();
		
		person.setDeathDate(deathDate);
		person.setBirthDate(birthDate);
	
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalDeathDate() throws InterruptedException{
		
		Date deathDate = new Date();
		Thread.sleep(10);
		Date birthDate = new Date();
		
		person.setDeathDate(deathDate);
		person.setBirthDate(birthDate);
		
		
	}
	
}
