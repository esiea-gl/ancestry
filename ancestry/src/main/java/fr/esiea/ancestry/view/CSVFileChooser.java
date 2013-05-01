package fr.esiea.ancestry.view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSVFileChooser extends JFileChooser {

	private static final long serialVersionUID = -4101393517087243866L;
	
	public CSVFileChooser() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"CSV file", "csv", "CSV");
		this.addChoosableFileFilter(filter);
	}
	

}
