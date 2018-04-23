package org.strieber.jRayTracer.engine;

import java.util.ArrayList;

import org.strieber.jRayTracer.lights.DirectedLight;
import org.strieber.jRayTracer.lights.Light;
import org.strieber.jRayTracer.lights.PointLight;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.primitives.Primitive;
import org.strieber.jRayTracer.primitives.Sphere;
import org.strieber.jRayTracer.primitives.Surface;

public class SceneBuilder {
	
	// Singleton class
	private static SceneBuilder sceneBuilder;	
	
	Scene scene = new Scene();
	
	private int superSampleWidth = 1;
	private int sizeX, sizeY = 0;
	
	private Camera camera = new Camera();
	private Color backgroundColor = new Color();
	private Color ambientLight = new Color();
	
	private ArrayList<Primitive> primitiveList = new ArrayList<Primitive>();
	private ArrayList<Light> lightList = new ArrayList<Light>();

	// private Constructor
	private SceneBuilder() {

	}
	
	// provide access to one SceneBuilder
	public synchronized static SceneBuilder getSceneBuilder() {
		if (sceneBuilder == null) {
			sceneBuilder = new SceneBuilder();
		}
		return sceneBuilder;
	}
	// Disable cloning
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	// start over
	public void cleanBuilder() {
		primitiveList = new ArrayList<Primitive>();
		lightList = new ArrayList<Light>();
		backgroundColor = new Color();
		scene = new Scene();
	}
	
	// generate scene
	public void buildScene() {
		scene = new Scene();
		scene.setCamera(camera);
		scene.setPrimitiveList(primitiveList);
		scene.setLightList(lightList);
		scene.setSizeX(sizeX);
		scene.setSizeY(sizeY);
		scene.setBackgroundColor(backgroundColor);
		scene.setAmbientLight(ambientLight);
		scene.setSuperSampleWidth(superSampleWidth);
	}
	
	// return scene to feed into rayTracer
	public Scene getScene() {
		// automatically build the scene first
		buildScene();
		return scene;
	}
	
	// set size of scene
	public void setSize(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	
	// set super sample width
	public void setSuperSampleWidth(int superSampleWidth) {
		this.superSampleWidth = superSampleWidth;
	}
	public int getSuperSampleWidth() {
		return superSampleWidth;
	}
	
	// setup camera
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public Camera getCamera() {
		return camera;
	}
	
	// set scene background color
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	// set scene ambient light color
	public void setAmbientLight(Color ambientLight) {
		this.ambientLight = ambientLight;
	}
	public Color getAmbientLight() {
		return ambientLight;
	}
	
	// manipulate primitiveList
	public void addSphere(Point3d position, double size, Surface surface) {
		primitiveList.add(new Sphere(position, size, surface));
	}
	public void removePrimitive(int element) {
		primitiveList.remove(element);
	}
	public void removeLastPrimitive() {
		primitiveList.remove(primitiveList.size() - 1);
	}
	// take in new primitiveList for GUI
	public void setPrimitiveList(ArrayList<Primitive> primitiveList) {
		this.primitiveList = primitiveList;
	}
	// return primitiveList
	public ArrayList<Primitive> getPrimitiveList() {
		return primitiveList;
	}
	// manipulate lightList
	public void addPointLight(Point3d position, Color color, double[] attenuation) {
		lightList.add(new PointLight(position, color, attenuation));
	}
	public void addPointLight(Point3d position, Color color) {
		lightList.add(new PointLight(position, color));
	}
	public void addDirectedLight(Vector3d direction, Color color) {
		lightList.add(new DirectedLight(direction, color));
	}
	public void removeLight(int element) {
		lightList.remove(element);
	}
	public void removeLastLight() {
		lightList.remove(lightList.size() - 1);
	}
	// take in new lightList for GUI
	public void setLightList(ArrayList<Light> lightList) {
		this.lightList = lightList;
	}
	public ArrayList<Light> getLightList() {
		return lightList;
	}
	
	// generate Surface
	public Surface generateSurface(Color diffuse, Color specular, Color ambient, Color emission,
			double shininess, double reflectance) {
		Surface surface = new Surface();
		surface.setDiffuse(diffuse);
		surface.setSpecular(specular);
		surface.setAmbient(ambient);
		surface.setEmission(emission);
		surface.setShininess(shininess);
		surface.setReflectance(reflectance);
		return surface;
	}
}