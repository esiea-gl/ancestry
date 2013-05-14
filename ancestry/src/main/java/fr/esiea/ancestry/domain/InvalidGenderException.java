package fr.esiea.ancestry.domain;

public class InvalidGenderException extends Exception {

	private static final long serialVersionUID = -7049166898015487594L;

	public InvalidGenderException(String personFullName) {
		super("Le sexe de " + personFullName + " est incorrect");
	}
	
}
