package fr.esiea.ancestry.view;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class jtemp extends JFrame{
	  public jtemp(){             
	    this.setTitle("Fenêtre de Merde!!!");
	    this.setSize(700, 500);
	    this.setLocationRelativeTo(null);               
	  
	    JPanel pan = new CreateTreeView();    
	    this.setContentPane(pan);               
	    this.setVisible(true);
	  }       



public static void main(String[] args) {
	JFrame lk = new jtemp();
	
	}
}