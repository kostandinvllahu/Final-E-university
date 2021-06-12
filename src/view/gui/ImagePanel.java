package view.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 4540310170222288221L;
	private Image img;

	public ImagePanel(String imgPath) {
		this(new ImageIcon(imgPath).getImage());
	}

	private ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
}