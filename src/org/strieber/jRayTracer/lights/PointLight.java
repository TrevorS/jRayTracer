package org.strieber.jRayTracer.lights;

import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class PointLight extends Light {

	double[] attenuation = {1, 0, 0};
	
	public PointLight() {
		
	}
	
	public PointLight(Point3d position, Color color, double[] attenuation) {
		this.position = position;
		this.color = color;
		this.attenuation = attenuation;
	}
	
	public PointLight(Point3d position, Color color) {
		this.position = position;
		this.color = color;
	}

	public Vector3d getVectorToLight(Point3d pointOfIntersection) {
		Vector3d vector = position.subtract(pointOfIntersection).convertToVector3d();
		vector.normalize();
		return vector;
	}

	public Color getAmountOfLight(Point3d point) {
		double d = point.subtract(position).convertToVector3d().length();
		double totalAttenuation = 1 / (attenuation[2] * d * d + attenuation[1] * d + attenuation[0]);
		
		Color result = new Color();
		result.red += color.red * totalAttenuation;
		result.green += color.green * totalAttenuation;
		result.blue += color.blue * totalAttenuation;
		return result;
	}
	
	public String toString() {
		return "Point Light / position: " + position + " color: " + color;
	}
}