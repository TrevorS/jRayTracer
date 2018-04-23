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
import org.strieber.jRayTracer.lights.DirectedLight;
import org.strieber.jRayTracer.lights.Light;
import org.strieber.jRayTracer.lights.PointLight;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class LightGUI extends JApplet {

	private SceneBuilder sceneBuilder;
	private ArrayList<Light> lightList;
	// GUI 
	private JList jlLights;
	private JScrollPane jspLights;
	private Light currentLight;
	private int currentSelection;
	
	private JPanel jpnlButtons = new JPanel();
	private JButton jbtAddLight = new JButton("Add Light");
	private JButton jbtRemoveLight = new JButton("Remove Light");
	
	private JLabel jlblLightType = new JLabel("Light Type: ");
	
	private JLabel jlblPositionX = new JLabel("Position X:");
	private JLabel jlblPositionY = new JLabel("Position Y:");
	private JLabel jlblPositionZ = new JLabel("Position Z:");
	
	private JLabel jlblDirectionX = new JLabel("Direction X:");
	private JLabel jlblDirectionY = new JLabel("Direction Y:");
	private JLabel jlblDirectionZ = new JLabel("Direction Z:");
	
	private JLabel jlblColorRed = new JLabel("Color Red:");
	private JLabel jlblColorGreen = new JLabel("Color Green:");
	private JLabel jlblColorBlue = new JLabel("Color Blue:");
	
	private JTextField jtfLightType = new JTextField(10);
	
	private JTextField jtfPositionX = new JTextField(10);
	private JTextField jtfPositionY = new JTextField(10);
	private JTextField jtfPositionZ = new JTextField(10);
	
	private JTextField jtfDirectionX = new JTextField(10);
	private JTextField jtfDirectionY = new JTextField(10);
	private JTextField jtfDirectionZ = new JTextField(10);
	
	private JTextField jtfColorRed = new JTextField(10);
	private JTextField jtfColorGreen = new JTextField(10);
	private JTextField jtfColorBlue = new JTextField(10);
	
	public LightGUI(SceneBuilder sceneBuilder) {
		setLayout(new BorderLayout());
		this.sceneBuilder = sceneBuilder;
		this.lightList = sceneBuilder.getLightList();
		jlLights = new JList(getListModel());
		jlLights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jspLights = new JScrollPane(jlLights);
				
		jpnlButtons.setLayout(new GridLayout(0,2));
		
		jpnlButtons.add(jlblLightType);
		jpnlButtons.add(jtfLightType);
		
		jpnlButtons.add(jlblPositionX);
		jpnlButtons.add(jtfPositionX);
		jpnlButtons.add(jlblPositionY);
		jpnlButtons.add(jtfPositionY);
		jpnlButtons.add(jlblPositionZ);
		jpnlButtons.add(jtfPositionZ);
		
		jpnlButtons.add(jlblDirectionX);
		jpnlButtons.add(jtfDirectionX);
		jpnlButtons.add(jlblDirectionY);
		jpnlButtons.add(jtfDirectionY);
		jpnlButtons.add(jlblDirectionZ);
		jpnlButtons.add(jtfDirectionZ);
		
		jpnlButtons.add(jlblColorRed);
		jpnlButtons.add(jtfColorRed);
		jpnlButtons.add(jlblColorGreen);
		jpnlButtons.add(jtfColorGreen);
		jpnlButtons.add(jlblColorBlue);
		jpnlButtons.add(jtfColorBlue);
	
		jpnlButtons.add(jbtAddLight);
		jpnlButtons.add(jbtRemoveLight);
		
		add(jspLights, BorderLayout.CENTER);
		add(jpnlButtons, BorderLayout.EAST);
		
		jbtAddLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewPrimitive();
				jlLights.setModel(getListModel());
				setNewLightList();
			}
		});
		
		jbtRemoveLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentSelection >= 0) {
					// something selected
					currentLight = null;
					lightList.remove(currentSelection);
					jlLights.setModel(getListModel());
					setNewLightList();
				}
				else {
					// nothing selected
					clearAllTextFields();
				}
			}
		});
		
		jlLights.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				currentSelection = jlLights.getSelectedIndex();
				if (currentSelection >= 0) {
					// something selected
					currentLight = lightList.get(currentSelection);
					clearAllTextFields();
					setAllTextFields();
				}
				else {
					// nothing selected
					clearAllTextFields();
				}
			}
		});
	}
	
	private String[] getLightNames() {
		String[] lightNames = new String[lightList.size()];
		for (int i = 0; i < lightList.size(); i++) {
			lightNames[i] = lightList.get(i).toString();
		}
		return lightNames;
	}
	
	private DefaultListModel getListModel() {
		DefaultListModel model = new DefaultListModel();
		String[] lightNames = getLightNames();
		for (int i = 0; i < lightNames.length; i++) {
			model.addElement(lightNames[i]);
		}
		return model;
	}
	
	private void setNewLightList() {
		sceneBuilder.setLightList(lightList);
	}
	
	private void addNewPrimitive() {
		double x, y, z;
		
		double red = Double.parseDouble(jtfColorRed.getText().trim());
		double green = Double.parseDouble(jtfColorGreen.getText().trim());
		double blue = Double.parseDouble(jtfColorBlue.getText().trim());
		Color color = new Color(red, green, blue);
		
		String lightType = jtfLightType.getText().trim();
		
		if (lightType.equals("Point Light")) {
			
			if (jtfPositionX.getText().trim().equals("Infinity") || 
					jtfPositionX.getText().trim().equals("")) {
				x = Double.POSITIVE_INFINITY;
			}
			else {
				x = Double.parseDouble(jtfPositionX.getText().trim());
			}
			if (jtfPositionY.getText().trim().equals("Infinity") || 
					jtfPositionY.getText().trim().equals("")) {
				y = Double.POSITIVE_INFINITY;
			}
			else {
				y = Double.parseDouble(jtfPositionX.getText().trim());
			}
			if (jtfPositionZ.getText().trim().equals("Infinity") || 
					jtfPositionZ.getText().trim().equals("")) {
				z = Double.POSITIVE_INFINITY;
			}
			else {
				z = Double.parseDouble(jtfPositionX.getText().trim());
			}
			
			Point3d position = new Point3d(x, y, z);
			lightList.add(new PointLight(position, color));
		}
		else if (lightType.equals("Directed Light")) {
			x = Double.parseDouble(jtfDirectionX.getText().trim());
			y = Double.parseDouble(jtfDirectionY.getText().trim());
			z = Double.parseDouble(jtfDirectionZ.getText().trim());
			
			Vector3d direction = new Vector3d(x, y, z);	
			lightList.add(new DirectedLight(direction, color));
		}
	}
	
	private void setAllTextFields() {
		if (currentLight instanceof PointLight) {
			jtfLightType.setText("Point Light");
			jtfPositionX.setText(currentLight.getPosition().getX() + "");
			jtfPositionY.setText(currentLight.getPosition().getY() + "");
			jtfPositionZ.setText(currentLight.getPosition().getZ() + "");
		}
		else if (currentLight instanceof DirectedLight) {
			DirectedLight tempLight = (DirectedLight) currentLight;
			jtfLightType.setText("Directed Light");
			jtfDirectionX.setText(tempLight.getDirection().getX() + "");
			jtfDirectionY.setText(tempLight.getDirection().getY() + "");
			jtfDirectionZ.setText(tempLight.getDirection().getZ() + "");
		}
		Color currentColor = currentLight.getColor();
		jtfColorRed.setText(currentColor.red + "");
		jtfColorGreen.setText(currentColor.green + "");
		jtfColorBlue.setText(currentColor.blue + "");
	}
		
	private void clearAllTextFields() {
		jtfLightType.setText("");
		
		jtfPositionX.setText("");
		jtfPositionY.setText("");
		jtfPositionZ.setText("");
		
		jtfDirectionX.setText("");
		jtfDirectionY.setText("");
		jtfDirectionZ.setText("");
		
		jtfColorRed.setText("");
		jtfColorGreen.setText("");
		jtfColorBlue.setText("");
	}
	
	public static void main(String[] args) {
		// For testing
		Color color = new Color(0.78, 0.78, 0.78);
		
		// position
		Point3d position = new Point3d(0, 0, 0);
		
		// add directedLight
		Vector3d direction = new Vector3d(0, 0, -2);
		
		SceneBuilder builder = SceneBuilder.getSceneBuilder();
		builder.addDirectedLight(direction, color);
		builder.addPointLight(position, color);
		
		JFrame frame = new JFrame("TestLightGUI");
		JApplet LightGUI = new LightGUI(builder);
		frame.add(LightGUI);
		frame.setSize(800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}