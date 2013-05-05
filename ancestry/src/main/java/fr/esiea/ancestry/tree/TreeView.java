package fr.esiea.ancestry.tree;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

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

import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.domain.Woman;


// NB : never use setSize(), use setPreferedSize() instead

public class TreeView extends JFrame {

	private static final long serialVersionUID = 1882182391251137556L;

	public JPanel tree; 
	
	public static void main(String[] args) {
		TreeView treeView = new TreeView();
		treeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		treeView.getContentPane().add(treeView.createUI());
		treeView.setSize(700, 500);
		treeView.setLocationRelativeTo(null);
		
		int x,y;
		x=100;
		y=100;
		Person p = new Man.Builder("Jesus", "Crise").Build("M");
		p.setBirthDate(null);
		p.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
		PersonCell tp =new PersonCell(p,x,y);
		

		Person p2 = new Man.Builder("Jesus", "Crise").Build("F");
		p2.setBirthDate(null);
		p2.setDeathDate(new DateTime(2013, 05, 01, 0, 0));
		PersonCell tp2 =new PersonCell(p2,x+150,y);
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.addCell(tp);
			Object v2 = graph.addCell(tp2);

			Object v3 = graph.insertVertex(parent, null, "", x+133, y+33, 4, 4, "strokeColor=black;fontColor=black;fillColor=black");
			graph.insertEdge(parent, null, "", v1, v2,"endArrow=none;strokeColor=black");
			graph.insertEdge(parent, null, "", v2, v3,"endArrow=none;strokeColor=black");
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
		graphComponent.setBorder(null);
		graphComponent.setConnectable(false);

		treeView.tree.add(graphComponent);

		
		treeView.setVisible(true);
		
		
	}
	
	
	public JComponent createUI() {
		tree = new JPanel();
		tree.setPreferredSize(new Dimension(1000,1000));
		tree.setBorder(BorderFactory.createLineBorder(Color.GREEN, 20));
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

