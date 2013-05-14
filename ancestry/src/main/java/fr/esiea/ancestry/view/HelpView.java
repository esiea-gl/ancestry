package fr.esiea.ancestry.view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpView extends JPanel {

	private static final long serialVersionUID = -7260839769084332654L;
	private JFrame frame;

	public HelpView(JFrame frame) {
		//setPreferredSize(ViewParams.VIEW_DIMENSION);
		frame.setPreferredSize(new Dimension(700,550));
		this.frame = frame;
		

		BufferedImage myPicture;
		try {
			File image = new File("./src/resources/helpMenuBackground.jpg");
			myPicture = ImageIO.read(image);
			JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
			add(picLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
