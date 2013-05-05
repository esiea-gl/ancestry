package fr.esiea.ancestry.tree;



import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import fr.esiea.ancestry.domain.Person;

public class PersonCell extends mxCell{
	private final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	  public PersonCell(Person person,int x, int y){
		  if(person.gender().equals("M")){
			  this.setStyle("align=align_left;strokeColor=black;fontColor=black");
		  }
		  else{
			  this.setStyle("strokeColor=black;fillColor=pink;fontColor=black");
		  }
		  String texte = " Nom           : " + person.lastName() +
			  			 "\n Prénom      : " + person.firstName();
		  
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
		  
		  mxGeometry geo = new mxGeometry(x, y, 120, 70);
		  this.setGeometry(geo);
		  geo.setRelative(false);
		  this.setParent(null);
		  this.setId(null);
		  this.setVertex(true);		  
	  }       
}
