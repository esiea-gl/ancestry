package fr.esiea.ancestry.domain;

public class Man extends Person {

	private static final int AGE_TO_HAVE_CHILD = 14;
	
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
	protected int minimalAgeForChildren() {
		return AGE_TO_HAVE_CHILD;
	}
	
	
	
}
