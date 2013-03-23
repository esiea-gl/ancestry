package fr.esiea.ancestry.domain;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class PersonTest extends TestCase {

	private Person person;
	
	@Before
	public void setUp() {
		person = new Person();
			
	}
	
	@Test
	public void testAddChild() { 
		person.addChild(new Person());
		assertTrue(person.childCount() > 0);
	}
	
	@Test
	public void testSetMother() {
		person.setFather(new Man());
		assertNotNull(person.father());
		assertTrue(person.father().childCount() > 0);
	}
	
	@Test
	public void testSetFather(){
		person.setMother(new Woman());
		assertNotNull(person.mother());
		assertTrue(person.mother().childCount() > 0);
	}
	
}
