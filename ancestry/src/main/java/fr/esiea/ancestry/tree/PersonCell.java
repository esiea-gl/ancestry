package fr.esiea.ancestry.tree;



import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import fr.esiea.ancestry.domain.Person;

public class PersonCell extends mxCell{
	
	private static final long serialVersionUID = -4006398822915168889L;
	private final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	  public PersonCell(Person person,double x, double y){
		  if(person.gender().equals("M")){
			  this.setStyle("align=align_left;strokeColor=black;fontColor=black");
		  }
		  else{
			  this.setStyle("align=align_left;strokeColor=black;fillColor=pink;fontColor=black");
		  }
		  
		  
		  String texte = null;

		  if (person.lastName()!=null){
			  texte = " Nom           : " + person.lastName();
		  }else{
			  texte = " Nom           : NC";
		  }

		  if (person.firstName()!=null){
			  texte = texte + "\n Prénom      : " + person.firstName();
		  }else{
			  texte = texte + "\n Prénom      : NC";
		  }
		  
		  if (person.birthDate()!=null){
			  texte = texte + "\n Naissance : " + dateFormatter.print(person.birthDate());
		  }else{
			  texte = texte + "\n Naissance : NC";
		  }

		  if (person.deathDate()!=null){
			  texte = texte + "\n Décés        : " + dateFormatter.print(person.deathDate());
		  }else{
			  texte = texte + "\n Décés        : NC";
		  }
		  
		  this.setValue(texte);
		  
		  mxGeometry geo = new mxGeometry(x, y, 121, 70);
		  this.setGeometry(geo);
		  geo.setRelative(false);
		  this.setParent(null);
		  this.setId(null);
		  this.setVertex(true);		  
	  }       
}
