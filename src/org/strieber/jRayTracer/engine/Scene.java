package org.strieber.jRayTracer.engine;

import java.util.ArrayList;

import org.strieber.jRayTracer.lights.Light;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.primitives.Primitive;

public class Scene {
	
	private Camera camera = new Camera();
	
	private ArrayList<Primitive> primitiveList = new ArrayList<Primitive>();
	private ArrayList<Light> lightList = new ArrayList<Light>();
	
	private Color backgroundColor = new Color();
	private Color ambientLight = new Color();
	
	private int sizeX, sizeY;
	private int superSampleWidth = 1;

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setSuperSampleWidth(int superSampleWidth) {
		this.superSampleWidth = superSampleWidth;
	}
	public int getSuperSampleWidth() {
		return superSampleWidth;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeX() {
		return sizeX;
	}
	public void setPrimitiveList(ArrayList<Primitive> primitiveList) {
		this.primitiveList = primitiveList;
	}
	public ArrayList<Primitive> getPrimitiveList() {
		return primitiveList;
	}
	public void setLightList(ArrayList<Light> lightList) {
		this.lightList = lightList;
	}
	public ArrayList<Light> getLightList() {
		return lightList;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setAmbientLight(Color ambientLight) {
		this.ambientLight = ambientLight;
	}
	public Color getAmbientLight() {
		return ambientLight;
	}
}
