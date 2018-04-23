package org.strieber.jRayTracer.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.strieber.jRayTracer.engine.SceneBuilder;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.primitives.Primitive;
import org.strieber.jRayTracer.primitives.Sphere;
import org.strieber.jRayTracer.primitives.Surface;

public class PrimitiveGUI extends JApplet {

	private SceneBuilder sceneBuilder;
	private ArrayList<Primitive> primitiveList;
	// GUI 
	private JList jlPrimitives;
	private JScrollPane jspPrimitives;
	private Primitive currentPrimitive;
	private Surface currentSurface;
	private int currentSelection;
	
	private JPanel jpnlButtons = new JPanel();
	private JButton jbtAddPrimitive = new JButton("Add Primitive");
	private JButton jbtRemovePrimitive = new JButton("Remove Primitive");
	
	private JLabel jlblPositionX = new JLabel("Position X:");
	private JLabel jlblPositionY = new JLabel("Position Y:");
	private JLabel jlblPositionZ = new JLabel("Position Z:");
	
	private JLabel jlblSize = new JLabel("Size:");
	
	private JLabel jlblDiffuseRed = new JLabel("Diffuse Red:");
	private JLabel jlblDiffuseGreen = new JLabel("Diffuse Green:");
	private JLabel jlblDiffuseBlue = new JLabel("Diffuse Blue:");
	
	private JLabel jlblSpecularRed = new JLabel("Specular Red:");
	private JLabel jlblSpecularGreen = new JLabel("Specular Green:");
	private JLabel jlblSpecularBlue = new JLabel("Specular Blue:");
	
	private JLabel jlblAmbientRed = new JLabel("Ambient Red:");
	private JLabel jlblAmbientGreen = new JLabel("Ambient Green:");
	private JLabel jlblAmbientBlue = new JLabel("Ambient Blue:");
	
	private JLabel jlblEmissionRed = new JLabel("Emission Red:");
	private JLabel jlblEmissionGreen = new JLabel("Emission Green:");
	private JLabel jlblEmissionBlue = new JLabel("Emission Blue:");
	
	private JLabel jlblShininess = new JLabel("Shininess:");
	private JLabel jlblReflectance = new JLabel("Reflectance:");
	
	private JTextField jtfPositionX = new JTextField(10);
	private JTextField jtfPositionY = new JTextField(10);
	private JTextField jtfPositionZ = new JTextField(10);
	private JTextField jtfSize = new JTextField(10);
	
	private JTextField jtfDiffuseRed = new JTextField(10);
	private JTextField jtfDiffuseGreen = new JTextField(10);
	private JTextField jtfDiffuseBlue = new JTextField(10);
	
	private JTextField jtfSpecularRed = new JTextField(10);
	private JTextField jtfSpecularGreen = new JTextField(10);
	private JTextField jtfSpecularBlue = new JTextField(10);
	
	private JTextField jtfAmbientRed = new JTextField(10);
	private JTextField jtfAmbientGreen = new JTextField(10);
	private JTextField jtfAmbientBlue = new JTextField(10);
	
	private JTextField jtfEmissionRed = new JTextField(10);
	private JTextField jtfEmissionGreen = new JTextField(10);
	private JTextField jtfEmissionBlue = new JTextField(10);
	
	private JTextField jtfShininess = new JTextField(10);
	private JTextField jtfReflectance = new JTextField(10);
	
	public PrimitiveGUI(SceneBuilder sceneBuilder) {
		setLayout(new BorderLayout());
		this.sceneBuilder = sceneBuilder;
		this.primitiveList = sceneBuilder.getPrimitiveList();
		jlPrimitives = new JList(getListModel());
		jlPrimitives.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jspPrimitives = new JScrollPane(jlPrimitives);
				
		jpnlButtons.setLayout(new GridLayout(0,2));
		jpnlButtons.add(jlblPositionX);
		jpnlButtons.add(jtfPositionX);
		jpnlButtons.add(jlblPositionY);
		jpnlButtons.add(jtfPositionY);
		jpnlButtons.add(jlblPositionZ);
		jpnlButtons.add(jtfPositionZ);
		
		jpnlButtons.add(jlblSize);
		jpnlButtons.add(jtfSize);
		
		jpnlButtons.add(jlblDiffuseRed);
		jpnlButtons.add(jtfDiffuseRed);
		jpnlButtons.add(jlblDiffuseGreen);
		jpnlButtons.add(jtfDiffuseGreen);
		jpnlButtons.add(jlblDiffuseBlue);
		jpnlButtons.add(jtfDiffuseBlue);
		
		jpnlButtons.add(jlblSpecularRed);
		jpnlButtons.add(jtfSpecularRed);
		jpnlButtons.add(jlblSpecularGreen);
		jpnlButtons.add(jtfSpecularGreen);
		jpnlButtons.add(jlblSpecularBlue);
		jpnlButtons.add(jtfSpecularBlue);
		
		jpnlButtons.add(jlblAmbientRed);
		jpnlButtons.add(jtfAmbientRed);
		jpnlButtons.add(jlblAmbientGreen);
		jpnlButtons.add(jtfAmbientGreen);
		jpnlButtons.add(jlblAmbientBlue);
		jpnlButtons.add(jtfAmbientBlue);
		
		jpnlButtons.add(jlblEmissionRed);
		jpnlButtons.add(jtfEmissionRed);
		jpnlButtons.add(jlblEmissionGreen);
		jpnlButtons.add(jtfEmissionGreen);
		jpnlButtons.add(jlblEmissionBlue);
		jpnlButtons.add(jtfEmissionBlue);
		
		jpnlButtons.add(jlblShininess);
		jpnlButtons.add(jtfShininess);
		jpnlButtons.add(jlblReflectance);
		jpnlButtons.add(jtfReflectance);
		
		jpnlButtons.add(jbtAddPrimitive);
		jpnlButtons.add(jbtRemovePrimitive);
		
		add(jspPrimitives, BorderLayout.CENTER);
		add(jpnlButtons, BorderLayout.EAST);
		
		jbtAddPrimitive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewPrimitive();
				jlPrimitives.setModel(getListModel());
				setNewPrimitiveList();
			}
		});
		
		jbtRemovePrimitive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentSelection >= 0) {
					// something selected
					currentPrimitive = null;
					currentSurface = null;
					primitiveList.remove(currentSelection);
					jlPrimitives.setModel(getListModel());
					setNewPrimitiveList();
				}
				else {
					// nothing selected
					clearAllTextFields();
				}
			}
		});
		
		jlPrimitives.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				currentSelection = jlPrimitives.getSelectedIndex();
				if (currentSelection >= 0) {
					// something selected
					currentPrimitive = primitiveList.get(currentSelection);
					currentSurface = currentPrimitive.getSurface();
					setAllTextFields();
				}
				else {
					// nothing selected
					clearAllTextFields();
				}
			}
		});
	}
	
	private String[] getPrimitiveNames() {
		String[] primitiveNames = new String[primitiveList.size()];
		for (int i = 0; i < primitiveList.size(); i++) {
			primitiveNames[i] = primitiveList.get(i).toString();
		}
		return primitiveNames;
	}
	
	private DefaultListModel getListModel() {
		DefaultListModel model = new DefaultListModel();
		String[] primitiveNames = getPrimitiveNames();
		for (int i = 0; i < primitiveNames.length; i++) {
			model.addElement(primitiveNames[i]);
		}
		return model;
	}
	
	private void setNewPrimitiveList() {
		sceneBuilder.setPrimitiveList(primitiveList);
	}
	
	private void addNewPrimitive() {
		double x = Double.parseDouble(jtfPositionX.getText().trim());
		double y = Double.parseDouble(jtfPositionY.getText().trim());
		double z = Double.parseDouble(jtfPositionZ.getText().trim());
		
		double size = Double.parseDouble(jtfSize.getText().trim());
		Point3d position = new Point3d(x, y, z);
		
		double red = Double.parseDouble(jtfDiffuseRed.getText().trim());
		double green = Double.parseDouble(jtfDiffuseGreen.getText().trim());
		double blue = Double.parseDouble(jtfDiffuseBlue.getText().trim());
		Color diffuse = new Color(red, green, blue);
		
		red = Double.parseDouble(jtfSpecularRed.getText().trim());
		green = Double.parseDouble(jtfSpecularGreen.getText().trim());
		blue = Double.parseDouble(jtfSpecularBlue.getText().trim());
		Color specular = new Color(red, green, blue);
		
		red = Double.parseDouble(jtfAmbientRed.getText().trim());
		green = Double.parseDouble(jtfAmbientGreen.getText().trim());
		blue = Double.parseDouble(jtfAmbientBlue.getText().trim());
		Color ambient = new Color(red, green, blue);
		
		red = Double.parseDouble(jtfEmissionRed.getText().trim());
		green = Double.parseDouble(jtfEmissionGreen.getText().trim());
		blue = Double.parseDouble(jtfEmissionBlue.getText().trim());
		Color emission = new Color(red, green, blue);
		
		double shininess = Double.parseDouble(jtfShininess.getText().trim());
		double reflectance = Double.parseDouble(jtfReflectance.getText().trim());
		
		Surface surface = sceneBuilder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
		primitiveList.add(new Sphere(position, size, surface));
	}
	
	private void setAllTextFields() {
		jtfPositionX.setText(currentPrimitive.getPosition().getX() + "");
		jtfPositionY.setText(currentPrimitive.getPosition().getY() + "");
		jtfPositionZ.setText(currentPrimitive.getPosition().getZ() + "");
		// if its a sphere, set the size (sphere only attribute)
		jtfSize.setText("N/A");
		if (currentPrimitive instanceof Sphere) {
			Sphere currentSphere = (Sphere) currentPrimitive;
			jtfSize.setText(currentSphere.getSize() + "");
		}
		
		Color currentColor = currentSurface.getDiffuse();
		jtfDiffuseRed.setText(currentColor.red + "");
		jtfDiffuseGreen.setText(currentColor.green + "");
		jtfDiffuseBlue.setText(currentColor.blue + "");
		
		currentColor = currentSurface.getSpecular();
		jtfSpecularRed.setText(currentColor.red + "");
		jtfSpecularGreen.setText(currentColor.green + "");
		jtfSpecularBlue.setText(currentColor.blue + "");
		
		currentColor = currentSurface.getAmbient();
		jtfAmbientRed.setText(currentColor.red + "");
		jtfAmbientGreen.setText(currentColor.green + "");
		jtfAmbientBlue.setText(currentColor.blue + "");
		
		currentColor = currentSurface.getEmission();
		jtfEmissionRed.setText(currentColor.red + "");
		jtfEmissionGreen.setText(currentColor.green + "");
		jtfEmissionBlue.setText(currentColor.blue + "");
		
		jtfShininess.setText(currentSurface.getShininess() + "");
		jtfReflectance.setText(currentSurface.getReflectance() + "");
	}
		
	private void clearAllTextFields() {
		jtfPositionX.setText("");
		jtfPositionY.setText("");
		jtfPositionZ.setText("");
		
		jtfSize.setText("");
		
		jtfDiffuseRed.setText("");
		jtfDiffuseGreen.setText("");
		jtfDiffuseBlue.setText("");
		
		jtfSpecularRed.setText("");
		jtfSpecularGreen.setText("");
		jtfSpecularBlue.setText("");
		
		jtfAmbientRed.setText("");
		jtfAmbientGreen.setText("");
		jtfAmbientBlue.setText("");
		
		jtfEmissionRed.setText("");
		jtfEmissionGreen.setText("");
		jtfEmissionBlue.setText("");
		
		jtfShininess.setText("");
		jtfReflectance.setText("");
	}
	
	public static void main(String[] args) {
		// For testing
		Color diffuse = new Color(0.78, 0.78, 0.78);
		Color specular = new Color(0.7, 0.7, 0.7);
		Color ambient = new Color(0.1, 0.1, 0.1);
		Color emission = new Color(0, 0, 0);
		
		double shininess = 10;
		double reflectance = .2;
		
		// add sphere
		Point3d sphereCenter = new Point3d(0, 0, 0);
		double sphereRadius = 0.5;
		
		SceneBuilder builder = SceneBuilder.getSceneBuilder();
		Surface surface = builder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
		builder.addSphere(sphereCenter, sphereRadius, surface);
		
		JFrame frame = new JFrame("TestPrimitiveGUI");
		JApplet PrimitiveGUI = new PrimitiveGUI(builder);
		frame.add(PrimitiveGUI);
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}