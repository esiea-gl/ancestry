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

import fr.esiea.ancestry.domain.Man;
import fr.esiea.ancestry.domain.Person;


// NB : never use setSize(), use setPreferedSize() instead

public class TreeView extends JFrame {

	private static final long serialVersionUID = 1882182391251137556L;

	public JPanel tree; 
	
	public static void main(String[] args) {
		TreeView treeView = new TreeView();
		treeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		treeView.getContentPane().add(treeView.createUI());
		treeView.setSize(320, 320);
		treeView.setLocationRelativeTo(null);
		
		
		Person p = new Man.Builder("Axel", "Pegue").Build("M");
		treeView.tree.add(new PersonComponent(p));
		
		
		treeView.setVisible(true);
		
		
	}
	
	
	public JComponent createUI() {
		tree = new JPanel();
		tree.setPreferredSize(new Dimension(2000,2000));
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

