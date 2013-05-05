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
import javax.swing.WindowConstants;

import org.joda.time.DateTime;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import fr.esiea.ancestry.domain.Couple;
import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Woman;


// NB : never use setSize(), use setPreferedSize() instead

public class TreeView extends JFrame {

	private static final long serialVersionUID = 1882182391251137556L;

	public JPanel tree; 
	
	public static void main(String[] args) {
		TreeView treeView = new TreeView();
		treeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		treeView.getContentPane().add(treeView.createUI());
		treeView.setSize(1000, 1000);
		treeView.setLocationRelativeTo(null);
		
		int x,y;
		x=345;
		y=100;
		Man p = (Man) new Man.Builder("Jesus", "Crise").Build("M");
		p.setBirthDate(null);
		p.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
		

		Woman p2 = (Woman) new Woman.Builder("jinette", "Triquite").Build("F");
		p2.setBirthDate(null);
		p2.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
		

		Man p3 = (Man) new Man.Builder("Joharno", "DelaMort").Build("M");
		p3.setBirthDate(new DateTime(2003, 05, 04, 0, 0));
		p3.setDeathDate(new DateTime(2012, 8, 10, 0, 0));
		

		Man p4 = (Man) new Man.Builder("P�re", "No�l").Build("M");
		p4.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
		p4.setDeathDate(new DateTime(2012, 10, 10, 0, 0));

		Woman p5 = (Woman) new Woman.Builder("woucha", "zig").Build("F");
		p5.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
		p5.setDeathDate(new DateTime(2012, 10, 10, 0, 0));

		Man p6 = (Man) new Man.Builder("P�re", "No�l").Build("M");
		p6.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
		p6.setDeathDate(new DateTime(2012, 10, 10, 0, 0));
		
		Man p7 = (Man) new Man.Builder("P�re", "No�l").Build("M");
		p7.setBirthDate(new DateTime(2010, 05, 04, 0, 0));
		p7.setDeathDate(new DateTime(2012, 10, 10, 0, 0));
		
		Couple test = new Couple();
		test.setFather(p);
		test.addChild(p3);
		test.addChild(p4);
		test.addChild(p5);
		test.addChild(p6);
		//test.addChild(p7);
		
		
		

		PersonCell pere =new PersonCell(test.getFather(),x,y);
		PersonCell mere =new PersonCell(test.getMother(),x+150,y);
		List<PersonCell> childs = new ArrayList<PersonCell>();
		
		int childCount = test.childCount();
		x+=135;
		if(childCount%2==0){
			x-=((childCount/2)*(120+30)-15);
		}else{
			if(childCount==1){
				x-=60;
			}else{
				x-=((((childCount)-1)/2)*(120+30)+60);
			}
		}
		

		for(int i=0;i<childCount;i++){
		childs.add(new PersonCell(test.childrens().get(i),x,y+110));
		
		x+=150;
		}
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		List<Object> child = new ArrayList<Object>();
		List<Object> petitCube = new ArrayList<Object>();
		
		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.addCell(pere);
			Object v2 = graph.addCell(mere);
			
			
			
			Object v3 = graph.insertVertex(parent, null, "", pere.getGeometry().getX()+135, pere.getGeometry().getY()+35, 0, 0, "strokeColor=black;fontColor=black;fillColor=black");
			graph.insertEdge(parent, null, "", v1, v2,"endArrow=none;strokeColor=black");
			graph.insertEdge(parent, null, "", v2, v3,"endArrow=none;strokeColor=black");
			
			int j=0;
			for(int i=0;i<childCount;i++){
				child.add(graph.addCell(childs.get(i)));
				petitCube.add(graph.insertVertex(parent, null, "", childs.get(i).getGeometry().getX()+60, childs.get(i).getGeometry().getY()-20, 0, 0, "strokeColor=black;fontColor=black;fillColor=black"));
				graph.insertEdge(parent, null, "", petitCube.get(j), child.get(i),"strokeColor=black");
				j++;
				
				if(childCount%2==0 && childCount/2==j){
					petitCube.add(graph.insertVertex(parent, null, "", childs.get(i).getGeometry().getX()+135, childs.get(i).getGeometry().getY()-20, 0, 0, "strokeColor=black;fontColor=black;fillColor=black"));
					graph.insertEdge(parent, null, "", v3, petitCube.get(j),"endArrow=none;strokeColor=black");
					j++;
				}else if(childCount%2!=0 && ((childCount-1)/2)+1==j){
					graph.insertEdge(parent, null, "", v3, petitCube.get(j-1),"endArrow=none;strokeColor=black");
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
		
		treeView.tree.add(graphComponent);

		
		treeView.setVisible(true);
		
		
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

