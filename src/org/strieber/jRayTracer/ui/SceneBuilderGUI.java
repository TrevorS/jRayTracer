package org.strieber.jRayTracer.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.strieber.jRayTracer.engine.Camera;
import org.strieber.jRayTracer.engine.RayTracer;
import org.strieber.jRayTracer.engine.Scene;
import org.strieber.jRayTracer.engine.SceneBuilder;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.primitives.Surface;

public class SceneBuilderGUI extends JApplet {

	// where the RayTraced image will render to
	JRenderPanel jRenderPanel = new JRenderPanel();
	JScrollPane jspRenderPanel = new JScrollPane(jRenderPanel);
	// main panel (where the renderPanel and the buttonPanel go
	JPanel mainPanel = new JPanel();
	// where the buttons and the info text area goes
	JPanel buttonPanel = new JPanel();
	JButton jbtPrimitives = new JButton("Primitives");
	JButton jbtLights = new JButton("Lights");
	JButton jbtScene = new JButton("Scene");
	JButton jbtRender = new JButton("Render");
	JButton jbtInfo = new JButton("Info");
	JButton jbtClear = new JButton("Clear");
	// info box that goes inside the buttonPanel
	JTextArea jtaInfo = new JTextArea();
	JScrollPane jspInfo = new JScrollPane(jtaInfo);
	// the status bar located at the bottom of the program
	JTextField jtfStatus = new JTextField("jRayTracer Status");
	// the file/example/help menuBar
	JMenuBar menuBar = new JMenuBar();
	// menus themselves
	JMenu fileMenu = new JMenu("File");
	JMenu exampleMenu = new JMenu("Examples");
	JMenu helpMenu = new JMenu("Help");
	// menu items for the file menu
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem openMenuItem = new JMenuItem("Open");
	JMenuItem saveMenuItem = new JMenuItem("Save");
	JMenuItem saveAsMenuItem = new JMenuItem("Save As");
	JMenuItem exportMenuItem = new JMenuItem("Export");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	// menu items for the examples menu	
	JMenuItem exampleScene1MenuItem = new JMenuItem("Example Scene 1");
	JMenuItem exampleScene2MenuItem = new JMenuItem("Example Scene 2");
	JMenuItem exampleScene3MenuItem = new JMenuItem("Example Scene 3");
	// menu items for the help menu
	JMenuItem quickHelpMenuItem = new JMenuItem("Quick Help");
	JMenuItem aboutMenuItem = new JMenuItem("About");
	
	// RayTracer Objects
	SceneBuilder sceneBuilder = SceneBuilder.getSceneBuilder();
	RayTracer rayTracer = RayTracer.getRayTracer();
	BufferedImage image;
	
	public SceneBuilderGUI() {
		// setup buttonPanel
		// maybe figure out grid bag
		buttonPanel.setLayout(new GridLayout(0,1));
		buttonPanel.add(jbtPrimitives);
		buttonPanel.add(jbtLights);
		buttonPanel.add(jbtScene);
		buttonPanel.add(jbtRender);
		buttonPanel.add(jbtInfo);
		buttonPanel.add(jbtClear);
		// setup mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(jspRenderPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		// setup menuBar / fileMenu
		menuBar.add(fileMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(exportMenuItem);
		fileMenu.add(exitMenuItem);
		// setup menuBar / exampleMenu
		menuBar.add(exampleMenu);
		exampleMenu.setMnemonic(KeyEvent.VK_E);
		exampleMenu.add(exampleScene1MenuItem);
		exampleMenu.add(exampleScene2MenuItem);
		exampleMenu.add(exampleScene3MenuItem);
		// setup menuBar / helpMenu
		menuBar.add(helpMenu);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.add(quickHelpMenuItem);
		helpMenu.add(aboutMenuItem);
		// put everything in
		add(mainPanel, BorderLayout.CENTER);
		add(jtfStatus, BorderLayout.SOUTH);
		setJMenuBar(menuBar);
		
		jbtPrimitives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JApplet applet = new PrimitiveGUI(sceneBuilder);
				JFrame frame = new JFrame();
				frame.add(applet);
				frame.setSize(600, 500);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		jbtLights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JApplet applet = new LightGUI(sceneBuilder);
				JFrame frame = new JFrame();
				frame.add(applet);
				frame.setSize(800, 300);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		jbtScene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JApplet applet = new SceneGUI(sceneBuilder);
				JFrame frame = new JFrame();
				frame.add(applet);
				frame.setSize(500, 400);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});
		jbtRender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Scene scene = sceneBuilder.getScene();
				int sizeX = scene.getSizeX();
				int sizeY = scene.getSizeY();
				BufferedImage blankImage = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
				Canvas canvas = new Canvas();
				image = rayTracer.draw(blankImage, canvas, scene);
				jRenderPanel.setBufferedImage(image);
				jspRenderPanel.updateUI();
				repaint();
			}
		});
		jbtInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		jbtClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jRenderPanel.clearImage();
				jRenderPanel.repaint();
				jspRenderPanel.updateUI();
				repaint();
			}
		});
		
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		openMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("export.png");
				try {
					ImageIO.write(image, "png", file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exampleScene1MenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sceneSizeX = 1920;
				int sceneSizeY = 1080;
				
				// setup builder
				sceneBuilder.cleanBuilder();
				sceneBuilder.setSize(sceneSizeX, sceneSizeY);
				Color sceneAmbient = new Color(0.2, 0.2, 0.2);
				sceneBuilder.setAmbientLight(sceneAmbient);
				sceneBuilder.setBackgroundColor(new Color(0.5, 0.5, 1));
				sceneBuilder.setSuperSampleWidth(1);
				
				// setup camera
				Point3d eye = new Point3d(0, 0, 2);
				Point3d lookAt = new Point3d(0, 0, 0);
				Vector3d upDirection = new Vector3d(0, 1, 0);
				double screenDist = 1;
				double screenWidth = 1.3;
				Camera camera = new Camera(eye, lookAt, upDirection);
				camera.setScreenDist(screenDist);
				camera.setScreenWidth(screenWidth);
				sceneBuilder.setCamera(camera);
				
				Color diffuse = new Color(0.78, 0.78, 0.78);
				Color specular = new Color(0.7, 0.7, 0.7);
				Color ambient = new Color(0.1, 0.1, 0.1);
				Color emission = new Color(0, 0, 0);
				
				double shininess = 10;
				double reflectance = .2;
				
				// add sphere
				Point3d sphereCenter = new Point3d(0, 0, 0);
				double sphereRadius = 0.5;
				Surface surface = sceneBuilder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
				sceneBuilder.addSphere(sphereCenter, sphereRadius, surface);
				
				// add sphere
				sphereCenter = new Point3d(0, -2, 0);
				sphereRadius = 0.5;
				surface = sceneBuilder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
				sceneBuilder.addSphere(sphereCenter, sphereRadius, surface);
				
				// add light
				sceneBuilder.addDirectedLight(new Vector3d(0.3, -0.7, -1), new Color(0, 0.5, 1));
			}
		});
		exampleScene2MenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sceneSizeX = 1920;
				int sceneSizeY = 1080;
				
				// setup builder
				sceneBuilder.cleanBuilder();
				sceneBuilder.setSize(sceneSizeX, sceneSizeY);
				Color sceneAmbient = new Color(1, 1, 1);
				sceneBuilder.setAmbientLight(sceneAmbient);
				sceneBuilder.setBackgroundColor(new Color(1, 0.5, 0.5));
				sceneBuilder.setSuperSampleWidth(5);
				
				// setup camera
				Point3d eye = new Point3d(0, 0, 5);
				Point3d lookAt = new Point3d(0, 0, 0);
				Vector3d upDirection = new Vector3d(0, 1, 0);
				double screenDist = 1;
				double screenWidth = 1.3;
				Camera camera = new Camera(eye, lookAt, upDirection);
				camera.setScreenDist(screenDist);
				camera.setScreenWidth(screenWidth);
				sceneBuilder.setCamera(camera);
				
				double diffuseRed = 0;
				double diffuseGreen = 0;
				double diffuseBlue = 0;
				
				Color diffuse;
				Color specular;
				Color ambient;
				Color emission;
				
				double shininess = 10;
				double reflectance = .2;
				
				double centerX = 0;
				double centerY = 0;
				double centerZ = 0;
				
				double sRadius = 0.1;
				
				for (int i = 0; i < 10; i++) {
					diffuse = new Color(diffuseRed, diffuseGreen, diffuseBlue);
					specular = new Color(1, 1, 1);
					ambient = new Color(0.1, 0.1, 0.1);
					emission = new Color(0, 0, 0);
					
					diffuseRed += 0.1;
					diffuseGreen += 0.1;
					diffuseBlue += 0.1;
					
					// add sphere
					Point3d sphereCenter = new Point3d(centerX, centerY, centerZ);
					
					centerX += 0.5;
					centerY += 0.2;
					centerZ += 0;
					
					double sphereRadius = sRadius;
					
					sRadius += 0.1;
					
					Surface surface = sceneBuilder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
					sceneBuilder.addSphere(sphereCenter, sphereRadius, surface);
				}
				
				// add light
				sceneBuilder.addDirectedLight(new Vector3d(0.3, -0.7, -1), new Color(0, 0.5, 1));
				sceneBuilder.addDirectedLight(new Vector3d(1, -1, 1), new Color(0, 0, 1));
				sceneBuilder.addPointLight(new Point3d(0, 1, 1), new Color(1, 1, 1));
			}
		});
		exampleScene3MenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		quickHelpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		jRenderPanel.repaint();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("jRayTracer");
		JApplet applet = new SceneBuilderGUI();
		frame.add(applet);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
