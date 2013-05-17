package fr.esiea.ancestry.tree;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxCellTracker;

import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.Woman;
import fr.esiea.ancestry.view.ModificationTreeView;

public class CellTracker extends mxCellTracker{
private JFrame frame= null;
private List<Double> listPersonCellX = null;
private List<Double> listPersonCellY = null;
private List<Person> listPerson= null;

	public CellTracker(JFrame frame, mxGraphComponent arg0, List<Double> listPersonCellX, List<Double> listPersonCellY, List<Person> listPerson, Color arg1) {
		super(arg0, arg1);
		this.frame = frame;
		this.listPersonCellX = listPersonCellX;
		this.listPersonCellY = listPersonCellY;
		this.listPerson = listPerson;
	}
	
	public void mouseClicked(MouseEvent e)
    {		
		int x = e.getX();
		int y = e.getY();
		if (e.getClickCount() == 2) {
	         if(e.getButton()==1){
	        	 for (int i = 0;i<listPerson.size();i++){
	        		 if(x>listPersonCellX.get(i) && x<listPersonCellX.get(i)+120 && y>listPersonCellY.get(i) && y<listPersonCellY.get(i)+70){
	        			 Person clickedPerson = listPerson.get(i);
	        			 if(clickedPerson == Man.EMPTY || clickedPerson == Woman.EMPTY){
	        				 if(i%2==0){
	        					 clickedPerson = listPerson.get(i+1);
	        				 }else{
	        					 clickedPerson = listPerson.get(i-1);
	        				 }
	        			 }
	        			 frame.getContentPane().removeAll();
	        			 frame.getContentPane().add(new TreeView(frame, clickedPerson));
	        			 frame.pack();
	        			 frame.repaint();
	        		 }
	        	 }
	         }
	         if(e.getButton()==3){
	        	 for (int i = 0;i<listPerson.size();i++){
	        		 if(x>listPersonCellX.get(i) && x<listPersonCellX.get(i)+120 && y>listPersonCellY.get(i) && y<listPersonCellY.get(i)+70){
	        			 Person clickedPerson = listPerson.get(i);
	        			 Couple couplePlickedPerson = null;
	        			 if(clickedPerson == Man.EMPTY || clickedPerson == Woman.EMPTY){
	        				 if(i%2==0){
	        					 couplePlickedPerson = listPerson.get(i+1).getCouple();
	        				 }else{
	        					 couplePlickedPerson = listPerson.get(i-1).getCouple();
	        				 }
	        			 }else{
	        				 couplePlickedPerson = clickedPerson.getCouple();
	        			 }
	        			 frame.getContentPane().removeAll();
	        			 frame.getContentPane().add(new ModificationTreeView(frame, clickedPerson,couplePlickedPerson));
	        			 frame.pack();
	        			 frame.repaint();
	        		 }
	        	 }
	         }
		}
		
    }

}
