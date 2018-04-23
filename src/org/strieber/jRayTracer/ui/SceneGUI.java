package org.strieber.jRayTracer.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.strieber.jRayTracer.engine.Camera;
import org.strieber.jRayTracer.engine.SceneBuilder;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class SceneGUI extends JApplet {

	private static SceneBuilder sceneBuilder;
	
	// GUI 
	private JButton jbtSave = new JButton("Save");
	private JButton jbtClear = new JButton("Clear");
	
	private JLabel jlblSizeX = new JLabel("Size X:");
	private JLabel jlblSizeY = new JLabel("Size Y:");
	private JLabel jlblSampleWidth = new JLabel("Sample Width:");
	
	private JLabel jlblCameraEyeX = new JLabel("Camera Eye X:");
	private JLabel jlblCameraEyeY = new JLabel("Camera Eye Y:");
	private JLabel jlblCameraEyeZ = new JLabel("Camera Eye Z:");
	
	private JLabel jlblCameraLookAtX = new JLabel("Camera Look At X:");
	private JLabel jlblCameraLookAtY = new JLabel("Camera Look At Y:");
	private JLabel jlblCameraLookAtZ = new JLabel("Camera Look At Z:");
	
	private JLabel jlblCameraUpX = new JLabel("Camera Up X:");
	private JLabel jlblCameraUpY = new JLabel("Camera Up Y:");
	private JLabel jlblCameraUpZ = new JLabel("Camera Up Z:");
	
	private JLabel jlblScreenDist = new JLabel("Screen Distance:");
	private JLabel jlblScreenWidth = new JLabel("Screen Width:");
	
	private JLabel jlblBackgroundR = new JLabel("Background Red:");
	private JLabel jlblBackgroundG = new JLabel("Background Green:");
	private JLabel jlblBackgroundB = new JLabel("Background Blue:");
	
	private JLabel jlblAmbientR = new JLabel("Ambient Red:");
	private JLabel jlblAmbientG = new JLabel("Ambient Green:");
	private JLabel jlblAmbientB = new JLabel("Ambient Blue:");
	
	private JTextField jtfSizeX = new JTextField(10);
	private JTextField jtfSizeY = new JTextField(10);
	private JTextField jtfSampleWidth = new JTextField(10);
	
	private JTextField jtfCameraEyeX = new JTextField(10);
	private JTextField jtfCameraEyeY = new JTextField(10);
	private JTextField jtfCameraEyeZ = new JTextField(10);
	
	private JTextField jtfCameraLookAtX = new JTextField(10);
	private JTextField jtfCameraLookAtY = new JTextField(10);
	private JTextField jtfCameraLookAtZ = new JTextField(10);
	
	private JTextField jtfCameraUpX = new JTextField(10);
	private JTextField jtfCameraUpY = new JTextField(10);
	private JTextField jtfCameraUpZ = new JTextField(10);
	
	private JTextField jtfScreenDist = new JTextField(10);
	private JTextField jtfScreenWidth = new JTextField(10);
	
	private JTextField jtfBackgroundR = new JTextField(10);
	private JTextField jtfBackgroundG = new JTextField(10);
	private JTextField jtfBackgroundB = new JTextField(10);
	
	private JTextField jtfAmbientR = new JTextField(10);
	private JTextField jtfAmbientG = new JTextField(10);
	private JTextField jtfAmbientB = new JTextField(10);
	
	public SceneGUI(SceneBuilder sceneBuilder) {
		setLayout(new GridLayout(0, 2));
		SceneGUI.sceneBuilder = sceneBuilder;
		
		setLayout(new GridLayout(0, 2));
		add(jlblSizeX);
		add(jtfSizeX);
		add(jlblSizeY);
		add(jtfSizeY);
		add(jlblSampleWidth);
		add(jtfSampleWidth);
		
		add(jlblCameraEyeX);
		add(jtfCameraEyeX);
		add(jlblCameraEyeY);
		add(jtfCameraEyeY);
		add(jlblCameraEyeZ);
		add(jtfCameraEyeZ);
		
		add(jlblCameraLookAtX);
		add(jtfCameraLookAtX);
		add(jlblCameraLookAtY);
		add(jtfCameraLookAtY);
		add(jlblCameraLookAtZ);
		add(jtfCameraLookAtZ);
		
		add(jlblCameraUpX);
		add(jtfCameraUpX);
		add(jlblCameraUpY);
		add(jtfCameraUpY);
		add(jlblCameraUpZ);
		add(jtfCameraUpZ);
		
		add(jlblScreenDist);
		add(jtfScreenDist);
		add(jlblScreenWidth);
		add(jtfScreenWidth);
		
		add(jlblBackgroundR);
		add(jtfBackgroundR);
		add(jlblBackgroundG);
		add(jtfBackgroundG);
		add(jlblBackgroundB);
		add(jtfBackgroundB);
		
		add(jlblAmbientR);
		add(jtfAmbientR);
		add(jlblAmbientG);
		add(jtfAmbientG);
		add(jlblAmbientB);
		add(jtfAmbientB);
		
		add(jbtSave);
		add(jbtClear);
		
		setAllTextFields();
		
		jbtSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveSceneSettings();
			}
		});
		
		jbtClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllTextFields();
			}
		});
	}
	
	private void saveSceneSettings() {
		int sizeX = Integer.parseInt(jtfSizeX.getText().trim());
		int sizeY = Integer.parseInt(jtfSizeY.getText().trim());
		int sampleWidth = Integer.parseInt(jtfSampleWidth.getText().trim());
		
		double cameraEyeX = Double.parseDouble(jtfCameraEyeX.getText().trim());
		double cameraEyeY = Double.parseDouble(jtfCameraEyeY.getText().trim());
		double cameraEyeZ = Double.parseDouble(jtfCameraEyeZ.getText().trim());
		Point3d cameraEye = new Point3d(cameraEyeX, cameraEyeY, cameraEyeZ);
		
		double cameraLookAtX = Double.parseDouble(jtfCameraLookAtX.getText().trim());
		double cameraLookAtY = Double.parseDouble(jtfCameraLookAtY.getText().trim());
		double cameraLookAtZ = Double.parseDouble(jtfCameraLookAtZ.getText().trim());
		Point3d cameraLookAt = new Point3d(cameraLookAtX, cameraLookAtY, cameraLookAtZ);
		
		double cameraUpX = Double.parseDouble(jtfCameraUpX.getText().trim());
		double cameraUpY = Double.parseDouble(jtfCameraUpY.getText().trim());
		double cameraUpZ = Double.parseDouble(jtfCameraUpZ.getText().trim());
		Vector3d cameraUp = new Vector3d(cameraUpX, cameraUpY, cameraUpZ);
		
		double screenDist = Double.parseDouble(jtfScreenDist.getText().trim());
		double screenWidth = Double.parseDouble(jtfScreenWidth.getText().trim());
		
		double backgroundR = Double.parseDouble(jtfBackgroundR.getText().trim());
		double backgroundG = Double.parseDouble(jtfBackgroundG.getText().trim());
		double backgroundB = Double.parseDouble(jtfBackgroundB.getText().trim());
		Color backgroundColor = new Color(backgroundR, backgroundG, backgroundB);
		
		double ambientR = Double.parseDouble(jtfAmbientR.getText().trim());
		double ambientG = Double.parseDouble(jtfAmbientG.getText().trim());
		double ambientB = Double.parseDouble(jtfAmbientB.getText().trim());
		Color ambientColor = new Color(ambientR, ambientG, ambientB);
		
		Camera camera = new Camera(cameraEye, cameraLookAt, cameraUp);
		camera.setScreenDist(screenDist);
		camera.setScreenWidth(screenWidth);
		
		sceneBuilder.cleanBuilder();
		sceneBuilder.setCamera(camera);
		
		sceneBuilder.setSize(sizeX, sizeY);
		sceneBuilder.setSuperSampleWidth(sampleWidth);
		sceneBuilder.setAmbientLight(ambientColor);
		sceneBuilder.setBackgroundColor(backgroundColor);
	}
	
	private void setAllTextFields() {
		jtfSizeX.setText(sceneBuilder.getSizeX() + "");
		jtfSizeY.setText(sceneBuilder.getSizeY() + "");
		jtfSampleWidth.setText(sceneBuilder.getSuperSampleWidth() + "");
		
		Camera camera = sceneBuilder.getCamera();
		Point3d cameraEye = camera.getEye();
		jtfCameraEyeX.setText(cameraEye.getX() + "");
		jtfCameraEyeY.setText(cameraEye.getY() + "");
		jtfCameraEyeZ.setText(cameraEye.getZ() + "");
		
		Point3d cameraLookAt = camera.getLookAt();
		jtfCameraLookAtX.setText(cameraLookAt.getX() + "");
		jtfCameraLookAtY.setText(cameraLookAt.getY() + "");
		jtfCameraLookAtZ.setText(cameraLookAt.getZ() + "");
		
		Vector3d cameraUp = camera.getUpDirection();
		jtfCameraUpX.setText(cameraUp.getX() + "");
		jtfCameraUpY.setText(cameraUp.getY() + "");
		jtfCameraUpZ.setText(cameraUp.getZ() + "");
		
		jtfScreenDist.setText(camera.getScreenDist() + "");
		jtfScreenWidth.setText(camera.getScreenWidth() + "");
		
		Color backgroundColor = sceneBuilder.getBackgroundColor();
		jtfBackgroundR.setText(backgroundColor.red + "");
		jtfBackgroundG.setText(backgroundColor.green + "");
		jtfBackgroundB.setText(backgroundColor.blue + "");
		
		Color ambientColor = sceneBuilder.getAmbientLight();
		jtfAmbientR.setText(ambientColor.red + "");
		jtfAmbientG.setText(ambientColor.green + "");
		jtfAmbientB.setText(ambientColor.blue + "");
	}
		
	private void clearAllTextFields() {
		jtfSizeX.setText("");
		jtfSizeY.setText("");
		jtfSampleWidth.setText("");
		
		jtfCameraEyeX.setText("");
		jtfCameraEyeY.setText("");
		jtfCameraEyeZ.setText("");
		
		jtfCameraLookAtX.setText("");
		jtfCameraLookAtY.setText("");
		jtfCameraLookAtZ.setText("");
		
		jtfCameraUpX.setText("");
		jtfCameraUpY.setText("");
		jtfCameraUpZ.setText("");
		
		jtfScreenDist.setText("");
		jtfScreenWidth.setText("");
		
		jtfBackgroundR.setText("");
		jtfBackgroundG.setText("");
		jtfBackgroundB.setText("");
		
		jtfAmbientR.setText("");
		jtfAmbientG.setText("");
		jtfAmbientB.setText("");
	}
	
	public static void main(String[] args) {
		sceneBuilder = SceneBuilder.getSceneBuilder();
		
		JFrame frame = new JFrame("TestSceneGUI");
		JApplet SceneGUI = new SceneGUI(sceneBuilder);
		frame.add(SceneGUI);
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}