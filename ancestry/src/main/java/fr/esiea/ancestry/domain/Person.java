package fr.esiea.ancestry.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
	
	private Date birthDate; 
	private Date deathDate;
	
	private String firstName;
	private String lastName;
	
	private Man father;
	private Woman mother;
	private List<Person> childs;
	
	public Person(){
		childs = new ArrayList<Person>();
	}
	
	
	public void setMother(Woman mother){
		this.mother = mother;
		mother.addChild(this);
	}
	
	public Person mother(){
		return mother;
	}
	
	public void setFather(Man father){
		this.father = father;
		father.addChild(this);
	}
	
	public Person father(){
		return father;
	}
	
	public void addChild(Person person){
		childs.add(person);
	}
	
	public int childCount(){
		return childs.size();
	}
}
