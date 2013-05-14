package fr.esiea.ancestry.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import fr.esiea.ancestry.data.Database;
import fr.esiea.ancestry.domain.Person;
import fr.esiea.ancestry.tree.TreeView;

public class SearchView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1644383792572406626L;

	private Database db = Database.getInstance();
	private JTable table;
	public JScrollPane tableScroller;
	private JTextField queryField;
	
	private JButton searchButton;
	private TableModel model;
	private JFrame frame;

	public SearchView(JFrame frame) {
		super();
		this.frame = frame;
		//setPreferredSize(ViewParams.VIEW_DIMENSION);
		frame.setPreferredSize(new Dimension(725,450));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		table = createTable(db.all());
		tableScroller = new JScrollPane(table);
		add(tableScroller);
		createSearchField();
	}

	
	private void createSearchField() {
		SpringLayout layout = new SpringLayout();
		JPanel searchPanel = new JPanel(layout);
		JLabel label = new JLabel("Recherche:", SwingConstants.LEADING);
		searchButton = new JButton();
		searchButton.setText("Rechercher");
		searchButton.addActionListener(this);
		queryField = new JTextField("");
		queryField.setPreferredSize(new Dimension(100, 20));
		
		
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST,
				searchPanel);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH,
				searchPanel);

		layout.putConstraint(SpringLayout.WEST, queryField, 5,
				SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, queryField, 5,
				SpringLayout.NORTH, searchPanel);
		
		layout.putConstraint(SpringLayout.NORTH, searchButton, 30,
				SpringLayout.NORTH, searchPanel);
		
		layout.putConstraint(SpringLayout.WEST, searchButton, 75,
				SpringLayout.WEST, searchPanel);
	
		searchPanel.add(searchButton);
		searchPanel.add(label);
		label.setLabelFor(queryField);
		searchPanel.add(queryField);

		add(searchPanel);
	}
	
	private JTable createTable(List<Person> persons) {
		model = new TableModel(persons);
		
			      
		final JTable table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         // tu récupèress le nom et le prénom de la case selectionner en tapant sur le model
			         // et tu fais find et tu prend le premier résultat 
			         // ensuite tu passes le relais à treeView
			         // merci pour la syntethisation de voix
			         // fait le, j'en ai marre
			         // merci, ROGER THAT
			         String firstName = (String) table.getModel().getValueAt(row, 0);
			         String lastName = (String) table.getModel().getValueAt(row, 1);
			        
			         List<Person> results = Database.getInstance().find(firstName + " " + lastName);
			         if(results.isEmpty()) return;
			         
			         Person lemecsurquionaclique = results.get(0);
			         // je laisse la main :) lol
						frame.getContentPane().add(new TreeView(frame, lemecsurquionaclique));
			         }
			   }
			});
		
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		return table;
	}
	

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getSource() == searchButton)
		{
			model.Refresh(db.find(queryField.getText()));
		}
	}
	
	private class TableModel extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 2998394209416743611L;
		final String[] columnHeaders = { "Prénom", "Nom" };
		private List<Person> _persons;
		
		public TableModel(List<Person> persons){
			_persons = persons;
		}
		
		public void Refresh(List<Person> persons) {
			_persons = persons;
			this.fireTableDataChanged();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnHeaders[columnIndex];
		}
		
		@Override
		public int getColumnCount() {
			return columnHeaders.length;
		}

		@Override
		public int getRowCount() {
			return _persons.size();
		}

		@Override
		public Object getValueAt(int x, int y) {
			Person p = _persons.get(x);
			return (y == 0)? p.firstName() : p.lastName();
		}

	}
}
