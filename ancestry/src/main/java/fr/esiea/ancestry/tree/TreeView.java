package fr.esiea.ancestry.tree;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;



import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.Person;


// NB : never use setSize(), use setPreferedSize() instead

public class TreeView extends JFrame {

	private static final long serialVersionUID = 1882182391251137556L;
	public JPanel tree; 
	
	public TreeView(JFrame frame, Person person) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(this.createUI());
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		
		double x,y;
		x=190;
		y=350;
		
		Couple coupleDePerson = person.getCouple();
		
		
		
		//Ne pas touchez en dessous du trait lol
		//--------------------------------
		
		
		
		//Parents du Père
		PersonCell pereDePere =new PersonCell(coupleDePerson.getFather().getFather(),x,y);
		PersonCell mereDePere =new PersonCell(coupleDePerson.getFather().getMother(),x+150,y);

		//Parents de la Mère
		PersonCell pereDeMere =new PersonCell(coupleDePerson.getMother().getFather(),x+350,y);
		PersonCell mereDeMere =new PersonCell(coupleDePerson.getMother().getMother(),x+500,y);
		
		
		x+=75;
		y+=120;
		
		//Père et Mère
		PersonCell pere =new PersonCell(coupleDePerson.getFather(),x,y);
		PersonCell mere =new PersonCell(coupleDePerson.getMother(),x+350,y);
		
		List<PersonCell> childs = new ArrayList<PersonCell>();
		
		int childCount = coupleDePerson.childCount();
		x+=235;
		if(childCount%2==0){
			x-=((childCount/2)*(120+30)-15);
		}else{
			if(childCount==1){
				x-=60;
			}else{
				x-=((((childCount)-1)/2)*(120+30)+60);
			}
		}
		
		//Enfants
		for(int i=0;i<childCount;i++){
		childs.add(new PersonCell(coupleDePerson.childrens().get(i),x,y+120));
		x+=150;
		}
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		List<Object> child = new ArrayList<Object>();
		List<Object> petitCube = new ArrayList<Object>();
		
		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.addCell(pereDePere);
			Object v2 = graph.addCell(mereDePere);
			
			Object v3 = graph.addCell(pereDeMere);
			Object v4 = graph.addCell(mereDeMere);
			
			Object v5 = graph.addCell(pere);
			Object v6 = graph.addCell(mere);
			
			
			
			//Liaison entre parents du Père
			Object v7 = graph.insertVertex(parent, null, "", pereDePere.getGeometry().getX()+135, pereDePere.getGeometry().getY()+35, 0, 0, "strokeColor=black;fontColor=black;fillColor=black");
			graph.insertEdge(parent, null, "", v1, v7,"endArrow=none;strokeColor=black");
			graph.insertEdge(parent, null, "", v7, v2,"endArrow=none;strokeColor=black");

			//Liaison entre parents de la Mère
			Object v8 = graph.insertVertex(parent, null, "", pereDeMere.getGeometry().getX()+135, pereDeMere.getGeometry().getY()+35, 0, 0, "strokeColor=black;fontColor=black;fillColor=black");
			graph.insertEdge(parent, null, "", v3, v8,"endArrow=none;strokeColor=black");
			graph.insertEdge(parent, null, "", v8, v4,"endArrow=none;strokeColor=black");

			//Liaison entre Père et Mère
			Object v9 = graph.insertVertex(parent, null, "", pere.getGeometry().getX()+235, pere.getGeometry().getY()+35, 0, 0, "strokeColor=black;fontColor=black;fillColor=black");
			graph.insertEdge(parent, null, "", v5, v9,"endArrow=none;strokeColor=black");
			graph.insertEdge(parent, null, "", v9, v6,"endArrow=none;strokeColor=black");
			
			//Liaison entre parents du Père et Père
			graph.insertEdge(parent, null, "", v7, v5,"strokeColor=black");
			
			//Liaison entre parents de la Mère et Mère
			graph.insertEdge(parent, null, "", v8, v6,"strokeColor=black");
			
			int j=0;
			for(int i=0;i<childCount;i++){
				child.add(graph.addCell(childs.get(i)));
				petitCube.add(graph.insertVertex(parent, null, "", childs.get(i).getGeometry().getX()+60, childs.get(i).getGeometry().getY()-20, 0, 0, "strokeColor=black;fontColor=black;fillColor=black"));
				graph.insertEdge(parent, null, "", petitCube.get(j), child.get(i),"strokeColor=black");
				j++;
				
				if(childCount%2==0 && childCount/2==j){
					petitCube.add(graph.insertVertex(parent, null, "", childs.get(i).getGeometry().getX()+135, childs.get(i).getGeometry().getY()-20, 0, 0, "strokeColor=black;fontColor=black;fillColor=black"));
					graph.insertEdge(parent, null, "", v9, petitCube.get(j),"endArrow=none;strokeColor=black");
					j++;
				}else if(childCount%2!=0 && ((childCount-1)/2)+1==j){
					graph.insertEdge(parent, null, "", v9, petitCube.get(j-1),"endArrow=none;strokeColor=black");
				}
			}
			
			
			for(int i=0;i<petitCube.size()-1;i++){
				graph.insertEdge(parent, null, "", petitCube.get(i), petitCube.get(i+1),"endArrow=none;strokeColor=black");
			}
			
			
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		graph.setCellsEditable(false);
		graph.setCellsMovable(false);
		graph.setCellsResizable(false);
		graph.setCellsSelectable(false);
		graph.setEdgeLabelsMovable(false);
		graph.setVertexLabelsMovable(false);
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		//graphComponent.setBorder(null);
		graphComponent.setConnectable(false);
		graphComponent.setPreferredSize(new Dimension(960,960));
		
		this.tree.add(graphComponent);

		
		this.setVisible(true);
		
		
	}
	
	
	public JComponent createUI() {
		tree = new JPanel();
		tree.setPreferredSize(new Dimension(1000,1000));
		tree.setBorder(BorderFactory.createLineBorder(Color.black, 20));
		JScrollPane scroller = new JScrollPane(tree);
		JViewport viewport = scroller.getViewport();
		MouseAdapter mouseAdapter = new DragScrollListener();
		viewport.addMouseMotionListener(mouseAdapter);
		viewport.addMouseListener(mouseAdapter);
		
		scroller.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		scroller.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		
		return scroller;
	}
	
}

