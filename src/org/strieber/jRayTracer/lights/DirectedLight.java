package org.strieber.jRayTracer.lights;


import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.math.Point3d;

public class DirectedLight extends Light {

	private Vector3d direction;
	private Vector3d oppositeDirection;
	
	public DirectedLight(Vector3d direction, Color color) {
		super();
		this.position = new Point3d(Double.POSITIVE_INFINITY, 
				Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		this.direction = direction;
		direction.normalize();
		this.color = color;
		oppositeDirection = direction.minus();
	}
	
	public void setDirection(Vector3d direction) {
		this.direction = direction;
		direction.normalize();
		oppositeDirection = direction.minus();
	}
	public Vector3d getDirection() {
		return direction;
	}
	
	@Override
	public Vector3d getVectorToLight(Point3d pointOfIntersection) {
		return oppositeDirection;
	}
	
	@Override
	public Color getAmountOfLight(Point3d point) {
		return color;
	}
	
	public String toString() {
		return "Directed Light / direction: " + direction + " color: " + color;
	}
}