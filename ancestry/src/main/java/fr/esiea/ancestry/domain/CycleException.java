package fr.esiea.ancestry.domain;



/**
 * Présence d'un cycle dans une famille donnée 
 * i.e. : A -> B -> A
 */
public class CycleException extends Exception {

	private static final long serialVersionUID = 875567865918357684L;

	public CycleException(String sourceFullName, String personFullName) {
		super("Il y a un cycle entre l'individu " 
				+ sourceFullName + " et " + personFullName);
	}
	
}
