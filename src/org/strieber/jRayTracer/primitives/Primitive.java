package org.strieber.jRayTracer.primitives;

import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public abstract class Primitive {
	protected Surface surface = new Surface();
	
	protected Point3d position;
	
	abstract public double intersect(Ray ray);
	
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	public Surface getSurface() {
		return surface;
	}
	
	public void setPosition(Point3d position) {
		this.position = position;
	}
	public Point3d getPosition() {
		return position;
	}
	
	public abstract Vector3d getNormal(Point3d point);
	
	public Color getColorAt(Point3d point) {
		return surface.getDiffuse();
	}
}