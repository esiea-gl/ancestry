package fr.esiea.ancestry.tree;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JViewport;


public class DragScrollListener extends MouseAdapter {
    private final Point cursorPosition = new Point();
    
    @Override public void mouseDragged(MouseEvent e) {
    	JViewport viewport = (JViewport) e.getSource();
    	JComponent component = (JComponent) viewport.getView();
    	Point eventPosition = e.getPoint();
    	Point viewPosition = viewport.getViewPosition();
    	viewPosition.translate(cursorPosition.x - eventPosition.x, cursorPosition.y - eventPosition.y);
    	component.scrollRectToVisible(new Rectangle(viewPosition, viewport.getSize()));
    	cursorPosition.setLocation(eventPosition);
    }
    
    @Override public void mousePressed(MouseEvent e) {
    	cursorPosition.setLocation(e.getPoint());
    }
    
  }
