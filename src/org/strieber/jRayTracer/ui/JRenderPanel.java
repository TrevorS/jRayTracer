package org.strieber.jRayTracer.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JRenderPanel extends JPanel {

	BufferedImage logo;
	BufferedImage image;
	
	JRenderPanel() {
		super();
		setLogo();
	}

	public void setBufferedImage(BufferedImage image) {
		this.image = image;
		int sizeX = image.getWidth();
		int sizeY = image.getHeight();
		this.setPreferredSize(new Dimension(sizeX, sizeY));
		repaint();
	}
	
	public void clearImage() {
		image = null;
	}
	
	public void setLogo() {
		// set logo here
		try {
			logo = ImageIO.read(new File("logo.png"));
			int sizeX = logo.getWidth();
			int sizeY = logo.getHeight();
			this.setPreferredSize(new Dimension(sizeX, sizeY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		if (image != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, null, 0, 0);
		}
		else if (logo != null) {
			Graphics2D g2 = (Graphics2D) g;
			int x = (this.getWidth() - logo.getWidth(null)) / 2;
			int y = (this.getHeight() - logo.getHeight(null)) / 2;
			g2.drawImage(logo, null, x, y);
		}
		else {
			// draw nothing
		}
	}
}