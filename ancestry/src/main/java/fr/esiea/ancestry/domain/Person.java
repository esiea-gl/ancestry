package fr.esiea.ancestry.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Years;

public abstract class Person {
	
	private DateTime birthDate; 
	private DateTime deathDate;
	
	private String firstName;
	private String lastName;
	
	private Man father;
	private Woman mother;
	private Person spouse;  
	private List<Person> childs;
	
	public Person() {
		childs = new ArrayList<Person>();
	}
	
	public Person(Builder builder) {
		childs = new ArrayList<Person>();
		
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		setBirthDate(builder.birthDate);
		setDeathDate(builder.deathDate);
	}
	
	public void setMother(Woman mother) {
		if(mother != null) mother.removeChild(this);
		this.mother = mother;
		if(mother == null) return;
		if(!mother.childs().contains(this)) {
			mother.addChild(this);
		}
	}
	
	public Person mother() {
		return mother;
	}
	
	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public Person Spouse() {
		return this.spouse;
	}
	
	public void setFather(Man father) {
		if(father != null) father.removeChild(this);
		this.father = father;
		if(father == null) return;
		if(!father.childs().contains(this)) {
			father.addChild(this);
		}
	}
	
	public Person father() {
		return father;
	}
	
	public void addChild(Person child) {

		if(this.childs().contains(child)) return;
		if(verifyChild(child)) {
			this.childs.add(child);
			linkChild(child);
		}
	}
	
	private boolean verifyChild(Person child) {
		if(!canHaveChild()) return false;
		return true;
	}
	
	protected abstract void linkChild(Person child);
	protected abstract void unlinkChild(Person child);
	protected abstract int minimalAgeForChildren();
	
	private boolean canHaveChild() {
		if(birthDate == null) return true;
		return age() > minimalAgeForChildren();
	}
	
	public int childCount() {
		return childs.size();
	}
	
	public void removeChild(Person child) {
		unlinkChild(child);
		childs.remove(child);
	}
	
	public List<Person> childs() {
		return Collections.unmodifiableList(childs);
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
		this.birthDate = new DateTime(birthDate);
	}
	
	public void setDeathDate(DateTime deathDate) throws IllegalArgumentException {
		if(!isDeathDateValid(deathDate)) throw new IllegalArgumentException("deathDate");
		this.deathDate = new DateTime(deathDate);
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
		return firstName + lastName;
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
		return (deathDate != null) ? deathDate.toDate().before(new Date()): false; 
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
