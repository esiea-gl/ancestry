package fr.esiea.ancestry.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class CoupleTest {

		private Couple couple;
		
		@Before
		public void setUp() {
			couple = new Couple();
		}
		
		@Test
		public void setFatherTest() {
			
			Man father = (Man) new Person.Builder("John", "Kerry").Build("M");
			couple.setFather(father);
			
			Man coupleFather = couple.getFather();
			
			assertNotNull(coupleFather);
			assertTrue(coupleFather != Man.EMPTY);
			assertTrue(coupleFather == father);
			assertTrue(coupleFather.firstName().equals(father.firstName()));
		}
		
		@Test
		public void setMotherTest() {

			Woman mother = (Woman) new Person.Builder("Elisa", "Johnson").Build("F");
			couple.setMother(mother);
			
			Woman coupleMother = couple.getMother();
			
			assertNotNull(coupleMother);
			assertTrue(coupleMother != Woman.EMPTY);
			assertTrue(coupleMother == mother);
			assertTrue(coupleMother.firstName().equals(mother.firstName()));
			 
		}

		@Test
		public void addChildTest() {
			Person firstChild = new Woman();
			Person secondChild = new Man();
			Person thirdChild = new Woman();
			
			couple.addChild(firstChild);
			couple.addChild(secondChild);
			couple.addChild(thirdChild);
		
			assertTrue(couple.childCount() == 3);
			assertNotNull(couple.childrens());
			assertNotNull(couple.childrens().get(0) instanceof Woman);
		}

		@Test
		public void removeChildTest() {
			Person child = new Woman();
			couple.addChild(child);
			assertTrue(couple.childCount() > 0);
			couple.removeChild(child);
			assertTrue(couple.childCount() == 0);
		}

		@Test
		public void addChildWithInvalidFatherAgeTest() {
			
			Man father = (Man) new Person.Builder("John", "Kerry")
									.birthDate(new DateTime().minusYears(13))
									.Build("M");
			
			couple.setFather(father);
			couple.addChild(new Woman());
			assertTrue(couple.childCount() == 0);
		}
		
		@Test
		public void addChildWithInvalidMotherAgeTest() {
			
			Woman mother = (Woman) new Person.Builder("Elisabeth", "Kerry")
									.birthDate(new DateTime().minusYears(11))
									.Build("F");
			
			couple.setMother(mother);
			couple.addChild(new Woman());
			assertTrue(couple.childCount() == 0);
		}
}
