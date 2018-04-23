package org.strieber.jRayTracer.primitives;

import org.strieber.jRayTracer.math.Color;

public class Surface {
	private Color diffuse;
	private Color specular;
	private Color ambient;
	private Color emission;
	
	double shininess;
	double reflectance;
	
	public Color getDiffuse() {
		return diffuse;
	}
	public void setDiffuse(Color diffuse) {
		this.diffuse = diffuse;
	}
	public Color getSpecular() {
		return specular;
	}
	public void setSpecular(Color specular) {
		this.specular = specular;
	}
	public Color getAmbient() {
		return ambient;
	}
	public void setAmbient(Color ambient) {
		this.ambient = ambient;
	}
	public Color getEmission() {
		return emission;
	}
	public void setEmission(Color emission) {
		this.emission = emission;
	}
	public double getShininess() {
		return shininess;
	}
	public void setShininess(double shininess) {
		this.shininess = shininess;
	}
	public double getReflectance() {
		return reflectance;
	}
	public void setReflectance(double reflectance) {
		this.reflectance = reflectance;
	}
}
