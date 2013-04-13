package fr.esiea.ancestry.domain;

import java.util.List;

public class Couple {
	private Woman Mother;
	private Man Father;
	private List<Person> Childrens;
	
	public void setMother(Woman mother){
		this.Mother=mother;		
	}

	public void setFather(Man father){
		this.Father=father;		
	}

	public Man father(){
		return this.Father;	
	}

	public Woman mother(){
		return this.Mother;	
	}

	public List<Person> childs(){
		return this.Childrens;	
	}
	
	public void addchild(Person child){
		this.Childrens.add(child);	
	}

	public void removeChild(Person child){
		this.Childrens.remove(child);	
	}
}
