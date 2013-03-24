package fr.esiea.ancestry.domain;

public class Man extends Person {

	public Man() {
		
	}
	
	public Man(Builder builder) {
		super(builder);
	}
	
	
	@Override
	protected void linkChild(Person child) {
		if(child.father() != this) child.setFather(this);
	}
	
	@Override
	protected void unlinkChild(Person child) {
		if(child.father() == this) child.setFather(null);
	}
	
	@Override
	protected boolean verifyChild(Person child) {
		return true;
	}
	
}
