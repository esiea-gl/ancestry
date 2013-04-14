package fr.esiea.ancestry.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Couple {

	private Man father;
	private Woman mother;
	private final List<Person> childrens;
	
	public Couple() {
		this.father = Man.EMPTY;
		this.mother = Woman.EMPTY;
		this.childrens = new ArrayList<Person>();
	}
		
	
	public Couple(Man father, Woman mother) {
		this.father = father;
		this.mother = mother;
		this.childrens = new ArrayList<Person>();
	}
	
	public Man getFather() {
		return father;
	}
	
	public void setFather(Man father) {
		this.father = father;
		father.setCouple(this);
	}
	
	public Woman getMother() {
		return mother;
	}

	public void setMother(Woman mother) {
		this.mother = mother;
		mother.setCouple(this);
	}
	
	public List<Person> childrens() {
		return Collections.unmodifiableList(childrens);
	}
	
	public void addChild(Person child) {
		
		if(!canHaveChildren()) return;
		if(!childrens.contains(child)) {
			childrens.add(child);
			child.setParents(this);
		}
	}
	
	public void removeChild(Person child) {
		childrens.remove(child);
	}
	
	public int childCount() {
		return childrens.size();
	}

	private boolean canHaveChildren() {
		return father.canHaveChildren() && mother.canHaveChildren(); 
	}
}
