package fr.esiea.ancestry.domain;


import org.joda.time.DateTime;
import org.joda.time.Years;

public abstract class Person {
	
	private DateTime birthDate; 
	private DateTime deathDate;
	
	private String firstName;
	private String lastName;
	
	private Couple parents;
	private Couple itsCouple;  
	
	private int id;
	

	private int fatherId;
	private int motherId;
	
	
	public Person() { }
	
	public Person(Builder builder) {
		
		itsCouple = new Couple();
		parents = new Couple();
		
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		setBirthDate(builder.birthDate);
		setDeathDate(builder.deathDate);
	}
	
	public boolean canHaveChildren() {
		if(this.birthDate == null) return true;
		return age() >= minimalAgeForChildren();
	}
	
	protected abstract int minimalAgeForChildren();
	
	public abstract String gender();
	
	
	public boolean isNew() {
		return id == 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getMotherId() {
		return motherId;
	}

	public void setMotherId(int motherId) {
		this.motherId = motherId;
	}
	
	
	// Vérifie si un individu est un de ses ancêtres
	public boolean hasAscendant(Person person) {
		
		if(this.getFather() == null 
				|| this.getMother() == null) return false;
		
		// Vérifie si l'individu fait parti des ses parents
		boolean isParent = 
				this.getFather() == person 
				|| this.getMother() == person;
		
		if(isParent) return true;
		
		return this.getFather().hasAscendant(person) 
				|| this.getMother().hasAscendant(person);
	}
	
	// Vérifie si un individu est dans sa descendance
	public boolean hasDescendant(Person person) {
		
		if(this.getCouple() == null) return false;
		
		// Verifie si l'individu est dans ses enfants
		if(this.getCouple().childrens().contains(person)) return true;
		
		// Vérifie si l'un de ses enfants est parent de l'individu
		for(Person child : this.getCouple().childrens()) {
			if(child.hasDescendant(person)) return true;
		}
		
		return false;
	}
	
	public Couple getCouple() {
		return itsCouple;
	}
	
	public void setCouple(Couple couple) {
		itsCouple = couple;
	}
		
	public void setParents(Couple parents) {
		parents.addChild(this);
		this.parents = parents;
	}
	
	public Man getFather() {
		if(parents == null) return null;
		return parents.getFather();
	}
	
	public Woman getMother() {
		if(parents == null) return null;
		return parents.getMother();
	}
	
	public Couple getParents() {
		return parents;
	}
	
	public DateTime birthDate() {
		if(birthDate == null) return null;
		return new DateTime(birthDate);
	}
	
	public DateTime deathDate() {
		if(deathDate == null) return null;
		return new DateTime(deathDate);
	}

	public void setBirthDate(DateTime birthDate) throws IllegalArgumentException {
		if(!isBirthDateValid(birthDate)) throw new IllegalArgumentException("birthDate");
		this.birthDate = (birthDate == null) ? null : new DateTime(birthDate);
	}
	
	public void setDeathDate(DateTime deathDate) throws IllegalArgumentException {
		if(!isDeathDateValid(deathDate)) throw new IllegalArgumentException("deathDate");
		this.deathDate = (deathDate == null) ? null : new DateTime(deathDate);
	}
	
	private boolean isDeathDateValid(DateTime deathDate) {
		if(deathDate == null) return true;
		if(isDateInTheFuture(deathDate)) return false;
		if(birthDate != null && deathDate.isBefore(birthDate)) return false;
		return true;
	}
	
	private boolean isBirthDateValid(DateTime birthDate) {
		if(birthDate == null) return true;
		if(isDateInTheFuture(birthDate)) return false;
		if(deathDate != null && birthDate.isAfter(deathDate)) return false;
		return true;
	}

	private boolean isDateInTheFuture(DateTime date) {
		return date.isAfter(new DateTime());
	}
	
	public String fullName() {
		return firstName + " " + lastName;
	}
	
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	protected int age() {
		if(this.birthDate == null) return 0;
		return Years.yearsBetween(this.birthDate, new DateTime()).getYears();
	}
	
	public boolean isDead() {
		return (deathDate != null) ? !deathDate.isAfter(new DateTime()): false; 
	}

	public static class Builder {
		
		private final String firstName;
		private final String lastName;
		private DateTime birthDate;
		private DateTime deathDate;
		
		public Builder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public Builder birthDate(DateTime date){
			this.birthDate = date;
			return this;
		}
		
		public Builder deathDate(DateTime date){
			this.deathDate = date;
			return this;
		}
		
		public Person Build(String gender) {
			
			if(gender.equals("M")) return new Man(this);
			if(gender.equals("F")) return new Woman(this);
			throw new IllegalArgumentException("gender");
		}
		
		
	}
}
