package org.strieber.jRayTracer.primitives;

public class Material {
	// this class is no longer used
	private double reflection;
	private double red, green, blue;
	
	public Material() {
		
	}
	public Material(double red, double green, double blue, double reflection) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.reflection = reflection;
	}
	
	public void setReflection(double reflection) {
		this.reflection = reflection;
	}
	public double getReflection() {
		return reflection;
	}
	public void setRed(double red) {
		this.red = red;
	}
	public double getRed() {
		return red;
	}
	public void setGreen(double green) {
		this.green = green;
	}
	public double getGreen() {
		return green;
	}
	public void setBlue(double blue) {
		this.blue = blue;
	}
	public double getBlue() {
		return blue;
	}
}
