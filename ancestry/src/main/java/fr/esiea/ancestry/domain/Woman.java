package fr.esiea.ancestry.domain;

public class Woman extends Person {

	private static final int AGE_TO_HAVE_CHILD = 12;
	
	public Woman() {
		
	}
	
	public Woman(Builder builder) {
		super(builder);
	}

	@Override
	protected void linkChild(Person child) {
		if(child.mother() != this) child.setMother(this);
	}
	
	@Override
	protected void unlinkChild(Person child) {
		if(child.mother() == this) child.setMother(null);
	}

	@Override
	protected int minimalAgeForChildren() {
		return AGE_TO_HAVE_CHILD;
	}
	
}
