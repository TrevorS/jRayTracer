package org.strieber.jRayTracer.lights;

import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public abstract class Light {
	protected Point3d position;
	protected Color color;
	
	public void setPosition(Point3d position) {
		this.position = position;
	}
	public Point3d getPosition() {
		return position;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract Vector3d getVectorToLight(Point3d pointOfIntersection);
	public abstract Color getAmountOfLight(Point3d point);
}
