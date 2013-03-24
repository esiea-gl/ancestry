package fr.esiea.ancestry.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class Person {
	
	private Date birthDate; 
	private Date deathDate;
	
	private String firstName;
	private String lastName;
	
	private Man father;
	private Woman mother;
	private List<Person> childs;
	
	public Person() {
		childs = new ArrayList<Person>();
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
	
	protected abstract boolean verifyChild(Person child);
	protected abstract void linkChild(Person child);
	protected abstract void unlinkChild(Person child);
	
	
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
	
	public Date birthDate() {
		return (Date) birthDate.clone();
	}
	
	public Date deathDate() {
		return (Date) deathDate.clone();
	}

	public void setBirthDate(Date birthDate) throws IllegalArgumentException {
		if(!isBirthDateValid(birthDate)) throw new IllegalArgumentException("birthDate");
		this.birthDate = birthDate;
	}
	
	public void setDeathDate(Date deathDate) throws IllegalArgumentException {
		if(!isDeathDateValid(deathDate)) throw new IllegalArgumentException("deathDate");
		this.deathDate = deathDate;
	}
	
	private boolean isDeathDateValid(Date deathDate) {
		if(deathDate == null) return true;
		if(isDateInTheFuture(deathDate)) return false;
		if(birthDate != null && deathDate.before(birthDate)) return false;
		return true;
	}
	
	private boolean isBirthDateValid(Date birthDate) {
		if(birthDate == null) return true;
		if(isDateInTheFuture(birthDate)) return false;
		if(deathDate != null && birthDate.after(deathDate)) return false;
		return true;
	}

	private boolean isDateInTheFuture(Date date) {
		return date.after(new Date());
	}
	
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	public boolean isDead() {
		return (deathDate != null) ? deathDate.before(new Date()): false; 
	}

}
