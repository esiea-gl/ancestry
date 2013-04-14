package fr.esiea.ancestry.domain;

public class Woman extends Person {

	private static final int AGE_TO_HAVE_CHILD = 12;
	public static final Woman EMPTY = new Woman.NullWoman(); 
	
	public Woman() {
		
	}
	
	public Woman(Builder builder) {
		super(builder);
	}


	@Override
	protected int minimalAgeForChildren() {
		return AGE_TO_HAVE_CHILD;
	}
	
	private static final class NullWoman extends Woman {
		
		private NullWoman() {
			
		}
		
		@Override
		protected int minimalAgeForChildren() {
			return 0;
		}
	}
	
}
