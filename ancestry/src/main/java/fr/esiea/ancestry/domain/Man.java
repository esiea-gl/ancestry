package fr.esiea.ancestry.domain;

public class Man extends Person {

	private static final int AGE_TO_HAVE_CHILD = 14;
	public static final Man EMPTY = new Man.NullPerson(); 
	
	public Man() {
		
	}
	
	public Man(Builder builder) {
		super(builder);
	}
	
	@Override
	protected int minimalAgeForChildren() {
		return AGE_TO_HAVE_CHILD;
	}
	
	@Override
	public String gender() {
		return "M";
	}
	
	private static class NullPerson extends Man {
	
			private NullPerson() {
				
			}
		
			@Override
			protected int minimalAgeForChildren() {
				return 0;
			}
		
	}

	
}
