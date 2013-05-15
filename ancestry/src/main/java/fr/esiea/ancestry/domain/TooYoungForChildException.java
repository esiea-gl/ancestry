package fr.esiea.ancestry.domain;

public class TooYoungForChildException extends Exception {

	private static final long serialVersionUID = 7691218958657327318L;
	
	public TooYoungForChildException() {
		super("L'âge d'un des parents est trop jeune pour avoir un enfant");
	}
	
}
