package org.strieber.jRayTracer.primitives;

import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class Ray {
	private Point3d start;
	private Vector3d direction;
	private double magnitude;
	
	public Ray() {
		
	}
	
	public Ray(Point3d start, Vector3d direction, double magnitude) {
		this.start = start;
		this.direction = direction;
		this.setMagnitude(magnitude);
	}
	
	public void setStart(Point3d start) {
		this.start = start;
	}
	public Point3d getStart() {
		return start;
	}
	public void setDirection(Vector3d direction) {
		this.direction = direction;
	}
	public Vector3d getDirection() {
		return direction;
	}
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}
	public double getMagnitude() {
		return magnitude;
	}
	public Point3d getEndPoint() {
		Point3d endPoint = new Point3d();
		endPoint.setX(start.getX() + magnitude * direction.getX());
		endPoint.setY(start.getY() + magnitude * direction.getY());
		endPoint.setZ(start.getZ() + magnitude * direction.getZ());
		return endPoint;
	}
	// returns the length of this vector
	public double length() {
		return Math.sqrt(
				direction.getX() * direction.getX() + 
				direction.getY() * direction.getY() + 
				direction.getZ() * direction.getZ()
				);
	}
	
	public void normalize() {
		double norm = length();
		if (norm == 0) {
			return;
		}
		direction.setX(direction.getX() / norm);
		direction.setY(direction.getY() / norm);
		direction.setZ(direction.getZ() / norm);
	}

	public String toString() {
		return "Ray:\n" + "start: " + start + " direction: " + direction + " magnitude: " + magnitude;
	}
}