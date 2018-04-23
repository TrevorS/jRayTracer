package org.strieber.jRayTracer.tests;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.strieber.jRayTracer.engine.Camera;
import org.strieber.jRayTracer.engine.RayTracer;
import org.strieber.jRayTracer.engine.Scene;
import org.strieber.jRayTracer.engine.SceneBuilder;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.primitives.Surface;

public class TestSceneBuilderGUI extends JPanel {

	Canvas canvas = new Canvas();
	JButton jbtGo = new JButton("Go");
	
	TestSceneBuilderGUI() {
		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);
		add(jbtGo, BorderLayout.SOUTH);
		
		jbtGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setSize(640, 480);
				BufferedImage image = draw();
				File file = new File("test.png");
				try {
					ImageIO.write(image, "png", file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		});
	}
	
	
	public BufferedImage draw() {
		int sceneSizeX = 1920;
		int sceneSizeY = 1080;
		
		SceneBuilder builder = SceneBuilder.getSceneBuilder();
		RayTracer rayTracer = RayTracer.getRayTracer();
		
		BufferedImage image = new BufferedImage(sceneSizeX, sceneSizeY, BufferedImage.TYPE_INT_RGB);
		BufferedImage results;
		
		// setup builder
		builder.cleanBuilder();
		builder.setSize(sceneSizeX, sceneSizeY);
		Color sceneAmbient = new Color(0.2, 0.2, 0.2);
		builder.setAmbientLight(sceneAmbient);
		builder.setBackgroundColor(new Color(0.5, 0.5, 1));
		builder.setSuperSampleWidth(5);
		
		// setup camera
		Point3d eye = new Point3d(0, 0, 2);
		Point3d lookAt = new Point3d(0, 0, 0);
		Vector3d upDirection = new Vector3d(0, 1, 0);
		double screenDist = 1;
		double screenWidth = 1.3;
		Camera camera = new Camera(eye, lookAt, upDirection);
		camera.setScreenDist(screenDist);
		camera.setScreenWidth(screenWidth);
		builder.setCamera(camera);
		
		Color diffuse = new Color(0.78, 0.78, 0.78);
		Color specular = new Color(0.7, 0.7, 0.7);
		Color ambient = new Color(0.1, 0.1, 0.1);
		Color emission = new Color(0, 0, 0);
		
		double shininess = 10;
		double reflectance = .2;
		
		// add sphere
		Point3d sphereCenter = new Point3d(0, 0, 0);
		double sphereRadius = 0.5;
		Surface surface = builder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
		builder.addSphere(sphereCenter, sphereRadius, surface);
		
		// add sphere
		sphereCenter = new Point3d(0, -2, 0);
		sphereRadius = 0.5;
		surface = builder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
		builder.addSphere(sphereCenter, sphereRadius, surface);
		
		// add light
		builder.addDirectedLight(new Vector3d(0.3, -0.7, -1), new Color(0, 0.5, 1));	
		Scene scene = builder.getScene();
		results = rayTracer.draw(image, canvas, scene);
		return results;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("jRayTracer");
		JPanel panel = new TestSceneBuilderGUI();
		frame.add(panel);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
