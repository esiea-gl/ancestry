package fr.esiea.ancestry.tree;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxCellTracker;

import fr.esiea.ancestry.domain.Person;

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
	         
	         for (int i = 0;i<listPerson.size();i++){
	        	 if(x>listPersonCellX.get(i) && x<listPersonCellX.get(i)+120 && y>listPersonCellY.get(i) && y<listPersonCellY.get(i)+70){
	        		 Person clickedPerson = listPerson.get(i);
	        		 frame.getContentPane().removeAll();
	        		 frame.getContentPane().add(new TreeView(frame, clickedPerson));
	        		 frame.pack();
	        		 frame.repaint();
	        	 }
	         }
		}
    }

}
