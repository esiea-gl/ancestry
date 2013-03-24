package fr.esiea.ancestry.domain;

public class Woman extends Person {

	
	@Override
	protected void linkChild(Person child) {
		if(child.mother() != this) child.setMother(this);
	}
	
	@Override
	protected void unlinkChild(Person child) {
		if(child.mother() == this) child.setMother(null);
	}
	
	
	@Override
	protected boolean verifyChild(Person child) {
		return true;
	}
	
}